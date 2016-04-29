package player.gamerunner;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import authoring.interfaces.model.CompleteAuthoringModelable;
import authoring.model.Preferences;
import data.Deserializer;
import gameengine.Sprite;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.util.Duration;
import physics.IPhysicsEngine;
import physics.StandardPhysics;
import player.gamedisplay.IGameDisplay;
import player.gamedisplay.StandardDisplay;
import player.leveldatamanager.EventManager;
import player.leveldatamanager.IDisplayScroller;
import player.leveldatamanager.ILevelData;
import player.leveldatamanager.LevelData;
import resources.VoogaBundles;
import stats.database.PlaySession;
import stats.database.StatCell;
import stats.database.VoogaDataBase;
import stats.interaction.CurrentSessionStats;
import player.leveldatamanager.DisplayScroller;
import player.leveldatamanager.ElementUpdater;
import tools.VoogaAlert;
import tools.VoogaException;
import tools.VoogaNumber;
import tools.VoogaString;
import videos.ScreenProcessor;

/**
 * GameRunner runs the game; uses composition to contain LevelData and GameDisplay
 * 
 * @author Hunter, Michael, Josh
 */
public class GameRunner implements IGameRunner {
    private static final double INIT_SPEED = 60;
    private static final double SEC_PER_MIN = 60;
    private static final double MILLISECOND_DELAY = 1000 / INIT_SPEED;
    private static final double SPEEDCONTROL = 10;
    private static final String LEVELS_PATH = "levels/";
    private static final String XML_EXTENSION_SUFFIX = ".xml";
    private static final String NULL_STRING = "";
    private IPhysicsEngine myPhysicsEngine;
    private ILevelData myLevelData;
	private IGameDisplay myGameDisplay;
	private ScreenProcessor myScreenProcessor;
    private ElementUpdater myElementUpdater;
    private EventManager myEventManager;
	private List<String> myLevelList;
	private LevelListCreator myLevelListCreator;
	private Timeline myTimeline;
    private String myCurrentLevelString;
    private IDisplayScroller myScroller;
	private int myCurrentStep;
	private CurrentSessionStats myStats;
	private double myLevelReached;
	/**
	 * Default constructor
	 */
	public GameRunner() {
		myGameDisplay = new StandardDisplay(this);
		myPhysicsEngine = new StandardPhysics();
		myElementUpdater = new ElementUpdater();
		myEventManager = new EventManager();
		myScreenProcessor = new ScreenProcessor();
		myLevelData = new LevelData(myPhysicsEngine);
		myScroller = new DisplayScroller(myGameDisplay);
		myTimeline = new Timeline();
		myLevelReached = 0;
		KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step());
		myTimeline.setCycleCount(Animation.INDEFINITE);
		myTimeline.getKeyFrames().add(frame);
		myStats = new CurrentSessionStats();
	}
	/**
	 * createLevelList reads a text file and creates a list of levels
	 */
	private void createLevelList(String xmlList) throws FileNotFoundException, IOException, VoogaException {
		myLevelListCreator = new LevelListCreator(xmlList);
		myLevelList = myLevelListCreator.getLevelList();
	}
	/**
	 * Public method in the IGameRunner interface that runs the game
	 */
	public void run() {
		myCurrentStep = 0;
		myTimeline.setRate(INIT_SPEED);
		myTimeline.play();
	}
	/**
	 * Stub function that calls methods that need to be called in order
	 */
	private void step() {	
		myCurrentStep++;
		double secondspassed = myCurrentStep * (1 / INIT_SPEED) / SEC_PER_MIN;
		myLevelData.updatedGlobalTimer(secondspassed);
		//check if we need to transition to a different level
    	if (myLevelData.getSaveNow()) {
    		//Change to the right player.
    		saveGameProgress();
    	}
		if (!myLevelData.getNextLevelName().equals(NULL_STRING)) {
			playLevel(myLevelList.get(myLevelList.indexOf(myLevelData.getNextLevelName())));
		}
		checkAndUpdateGlobalVariables();
		myElementUpdater.update(myLevelData);
		myGameDisplay.readAndPopulate(myLevelData.getDisplayableNodes());
		myEventManager.update(myLevelData, myGameDisplay.getMyKeyPresses(), myGameDisplay.getMyKeyReleases());
		myGameDisplay.clearKeyEvents();
		myScroller.increaseScrollingSpeed(myScroller.getScrollingSprite());
	}
	/**
	 * Checks and updates all LevelData GlobalVariables
	 */
	private void checkAndUpdateGlobalVariables() {
		myLevelData.updatedGlobalTimer(myCurrentStep * (1 / INIT_SPEED) / SEC_PER_MIN);
		if (!myLevelData.getNextLevelName().equals("")) {
			playLevel(myLevelList.get(myLevelList.indexOf(myLevelData.getNextLevelName())));
		}
	}
	/**
	 * 	Initializes myLevelList and plays the game
	 */
	public void playGame(String gameXmlList) {
		//start new game playing session
		myStats.startPlaySession();
				
		//get the last level reached if it exists
		PlaySession playsesh = myStats.getCurrentStatCell().getLatestPlaySession();
		String latestLevelReached = playsesh.getProperty(PlaySession.LEVEL_REACHED).toString();
		
		try {
			Preferences preferences = (Preferences) Deserializer.deserialize(1, "games/" + gameXmlList + "/" + gameXmlList + ".xml").get(0);
			double width = Double.parseDouble(preferences.getWidth());
			double height = Double.parseDouble(preferences.getHeight());
			myGameDisplay.setSceneDimensions(width, height);
			createLevelList(gameXmlList);
		} catch (Exception e) {
			new VoogaAlert("Level list initialization failed. Try opening in author and re-saving.");			
		}
		//if the 
		if (latestLevelReached.equals("")) {latestLevelReached = myLevelList.get(0);}
		
		myGameDisplay.display();
		playLevel(latestLevelReached);
		run();
	}
	
	/**
	 * Play a level, called by playGame
	 */
	private void playLevel(String fileName){
		myLevelReached++;
		myCurrentLevelString = fileName;
		myLevelData.refreshLevelData(myLevelListCreator.getGameFilePath() + LEVELS_PATH + fileName + XML_EXTENSION_SUFFIX);
		addScrolling();
		myGameDisplay.readAndPopulate(myLevelData.getDisplayableNodes());
	}
	/**
	 * Plays a single level called by authoring for testing purposes
	 */
	@Override
	public void testLevel(String levelName) {
		myCurrentLevelString = levelName.substring(levelName.replace('\\', '/').lastIndexOf('/') + 1, levelName.indexOf(XML_EXTENSION_SUFFIX));
		myLevelList = Arrays.asList(levelName);
		myLevelData.refreshLevelData(levelName);
		addScrolling();
		myGameDisplay.setSceneDimensions(Double.parseDouble(VoogaBundles.preferences.getProperty("GameWidth")), 
										 Double.parseDouble(VoogaBundles.preferences.getProperty("GameHeight")));
		myGameDisplay.displayTestMode();
		run();
	}

	@Override
	public void replayLevel() {
		myLevelData.setNextLevelName(myCurrentLevelString);
	}

	private void addScrolling() {
		Sprite scrollingSprite = myScroller.createScrollingSprite(myLevelData.getGlobalVariables(), 
				myCurrentLevelString, myLevelData.getMainSprite());
		System.out.println("This is scrollingSprite: " + scrollingSprite);
		myLevelData.getElements().put(scrollingSprite.getId(), scrollingSprite);
		myScroller.scroll(myLevelData.getGlobalVariables(), myCurrentLevelString, 
				myLevelData.getSpriteByID(scrollingSprite.getId()));
	}

	public IGameDisplay getGameDisplay() {
		return myGameDisplay;
	}

	@Override
	public void speedUp() {
        myTimeline.setRate(myTimeline.getRate() + SPEEDCONTROL);
	}
	@Override
	public void speedDown() {
        if (myTimeline.getRate() - SPEEDCONTROL > 0) {
        	myTimeline.setRate(myTimeline.getRate() - SPEEDCONTROL);
        }
	}

    @Override
    public CompleteAuthoringModelable getManager () {
        return null;
    }

	@Override
	public void playNextLevel() {
		myTimeline.stop();
		myLevelData.setNextLevelName("Lvl2");
		myTimeline.play();
	}

	@Override
	public void exit() {
		myTimeline.stop();
		myGameDisplay.exit();
	}
	
    @Override
    public void saveAll () {        
    }
    
	@Override
	public void saveGameProgress() {
		String myCurrentGame = VoogaBundles.preferences.getProperty("GameName");
		String myCurrentUser = VoogaBundles.preferences.getProperty("UserName");
		myLevelData.saveProgress(myLevelListCreator.getGameFilePath(), myCurrentUser,myCurrentGame);
	}
	
    @Override
    public void addScene () {        
    }

    @Override
    public void addScene (CompleteAuthoringModelable manager) {        
    }
    
	@Override 
	public void takeSnapShot() {
		//TODO call xuggleFileCreator to properly take snapshot and store as new file.
		Scene myScene = myGameDisplay.getMyScene();
		String fileName = myCurrentLevelString;
		myScreenProcessor.createSceneScreenshotPNG(myScene, fileName);
	}
	
	@Override
	public Timeline getTimeline() {
		return myTimeline;
	}
}