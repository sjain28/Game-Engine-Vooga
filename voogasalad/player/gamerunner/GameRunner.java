package player.gamerunner;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import authoring.interfaces.model.CompleteAuthoringModelable;
import data.Deserializer;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import physics.IPhysicsEngine;
import physics.StandardPhysics;
import player.gamedisplay.IGameDisplay;
import player.gamedisplay.StandardDisplay;
import player.leveldatamanager.EventManager;
import player.leveldatamanager.ILevelData;
import player.leveldatamanager.LevelData;
import player.leveldatamanager.SpriteManager;
import tools.VoogaAlert;
import tools.VoogaException;

/**
 * GameRunner class that runs the game player
 * Uses composition to contain interface instances of LevelDataManager
 * and GameDisplay
 * 
 * Runs the Timeline and manages levels and determines which level should 
 * be played in the game player and displayed in GameDisplay
 * 
 * @author Hunter, Michael, Josh
 *
 */
public class GameRunner implements IGameRunner {

    private static final double INIT_SPEED = 60;
    private static final double MILLISECOND_DELAY = 1000 / INIT_SPEED;
    private static final double SPEEDCONTROL = 10;
    
    private static final String LEVELS_PATH = "levels/";
    private static final String GAMES_PATH_PREFIX = "games/";
    private static final String XML_EXTENSION_SUFFIX = ".xml";
    
    private IPhysicsEngine myPhysicsEngine;
    private ILevelData myLevelData;
	private IGameDisplay myGameDisplay;
	
    private SpriteManager mySpriteManager;
    private EventManager myEventManager;
    
	private List<String> myLevelList;
	private Timeline myTimeline;
    
    private String myGameFilePath;
    private String myCurrentLevelString;
    
    // TODO: Test
	private int myCurrentStep;

	/**
	 * Default constructor
	 * 
	 * @param xmlList
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public GameRunner() {
		myGameDisplay = new StandardDisplay(getSelf());
		myPhysicsEngine = new StandardPhysics();

		mySpriteManager = new SpriteManager();
		myEventManager = new EventManager();
		myLevelData = new LevelData(myPhysicsEngine);
		
		myTimeline = new Timeline();
		KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY),
				e -> step());
		myTimeline.setCycleCount(Animation.INDEFINITE);
		myTimeline.getKeyFrames().add(frame);


		myGameFilePath = GAMES_PATH_PREFIX;
	}
	
	/**
	 * createLevelList reads a text file and creates a list of levels
	 * 
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @throws VoogaException 
	 */
	private void createLevelList(String xmlList) 
			throws FileNotFoundException, IOException, VoogaException {
		myGameFilePath = GAMES_PATH_PREFIX + xmlList + "/";
		String XMLwithListOfLevels = myGameFilePath + xmlList + ".xml";
		myLevelList = (List<String>) Deserializer.deserialize(1, XMLwithListOfLevels).get(0);
	}
	
	/**
	 * Public method in the IGameRunner interface that runs the game
	 * in the AnimationTimer timeline
	 * 
	 */
	public void run() {
		myCurrentStep = 0;
		getTimeline().setRate(INIT_SPEED);
		getTimeline().play();
	}

	/**
	 * Stub function that calls methods that need to be called in order
	 * for the animation to proceed
	 * 
	 * Update sprites, get the list of Nodes, and displays
	 * 
	 */
	private void step() {	
		myCurrentStep++;
		double secondspassed = myCurrentStep*(1/INIT_SPEED)/60;

		myLevelData.updatedGlobalTimer(secondspassed);

		//check if we need to transition to a different level
		if (!myLevelData.getNextLevelName().equals("")) {
			playLevel(myLevelList.get(myLevelList.indexOf(myLevelData.getNextLevelName())));
		}

		mySpriteManager.update(myLevelData.getAllSprites(), myPhysicsEngine);
		myEventManager.update(myLevelData, myGameDisplay.getKeyEvents());
		myGameDisplay.read(myLevelData.getDisplayableNodes());
		myGameDisplay.populateGameScreen();
		myGameDisplay.clearKeyEvents();	
	}
	
	/**
	 * 	Initializes myLevelList and plays the game
	 * 
	 */
	public void playGame(String gameXmlList) {
		try {
			createLevelList(gameXmlList);
		} catch (Exception e) {
			new VoogaAlert("Level List Initialization failed");			
		}
		myGameDisplay.display();
		playLevel(myLevelList.get(0));
		run();
	}
	
	/**
	 * Play a level, called by playGame
	 * @param fileName
	 * 
	 */
	private void playLevel(String fileName){
		myCurrentLevelString = fileName;
		String levelNameWithPath = myGameFilePath + LEVELS_PATH + fileName + XML_EXTENSION_SUFFIX; 
		myLevelData.refreshLevelData(levelNameWithPath);
		myGameDisplay.read(myLevelData.getDisplayableNodes());
	}
	
	/**
	 * Plays a single level called by authoring for testing purposes
	 * 
	 */
	@Override
	public void testLevel(String levelName) {	
		myLevelList = new ArrayList<>();
		myLevelList.add(levelName);
		myLevelData.refreshLevelData(levelName);
		myGameDisplay.displayTestMode();
		run();		
	}
	
	/**
	 * @return the CurrentLevelData
	 */
	public ILevelData getCurrentLevelData() {
		return myLevelData;
	}

	/**
	 * @return the myGameDisplay
	 */
	public IGameDisplay getGameDisplay() {
		return myGameDisplay;
	}

	/**
	 * @return the List of levels to be played.
	 */
	public List<String> getLevelList() {
		return myLevelList;
	}

	/**
	 * @return the myTimeline
	 */
	public Timeline getTimeline() {
		return myTimeline;
	}

	/**
	 * Stops the timeline (AnimationTimer)
	 * 
	 */
	@Override
	public void stop() {
		getTimeline().stop();
	}

	/**
	 * Starts the timeline (AnimationTimer)
	 * 
	 */
	@Override
	public void start() {
		getTimeline().play();
	}

	/**
	 * Returns an interface of this class
	 * Java's covariant return types
	 * 
	 */
	@Override
	public IGameRunner getSelf() {
		return this;
	}
	
	@Override
	public void speedUp() {
        getTimeline().stop();
        getTimeline().setRate(getTimeline().getRate() + SPEEDCONTROL);
        getTimeline().play();
	}

	@Override
	public void speedDown() {
        getTimeline().stop();
        double newRate = getTimeline().getRate() - SPEEDCONTROL;
        if (newRate > 0) {
            getTimeline().setRate(newRate);
        }
        getTimeline().play();
	}

	@Override
	public void mute() {
	}

	@Override
	public void replayLevel() {
		myLevelData.setNextLevelName(myCurrentLevelString);
	}
	
	@Override
	public void replayGame() {
	}

    @Override
    public CompleteAuthoringModelable getManager () {
        return null;
    }

    @Override
    public void addScene () {        
    }

    @Override
    public void saveAll () {        
    }

	
	public String getLevelNameString() {
		return this.myCurrentLevelString;
	}

	@Override
	public void playNextLevel() {
		stop();
		int tempIndex = myLevelList.indexOf(myCurrentLevelString) + 1;
		String nextLevel = myLevelList.get(tempIndex);
		myLevelData.setNextLevelName(nextLevel);
		getTimeline().play();
	}
	
    @Override
    public void addScene (CompleteAuthoringModelable manager) {        
    }
    
	@Override
	public void exit() {
		myTimeline.stop();
		myGameDisplay.exit();
	}

	@Override
	public void saveGameProgress(String playerName) {
		myLevelData.saveProgress(myGameFilePath,playerName);
	}

}