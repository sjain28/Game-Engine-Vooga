package player.gamerunner;

import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

import org.codehaus.groovy.runtime.ArrayUtil;

import authoring.gui.cartography.LevelType;
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
import tools.VoogaString;
import videos.ScreenProcessor;

/**
 * GameRunner runs the game; uses composition to contain LevelData and
 * GameDisplay
 * 
 * @author Hunter, Michael, Josh
 */
public class GameRunner implements IGameRunner {
    public static final double FRAME_RATE = 60;
    private static final double SEC_PER_MIN = 60;
    private static final double MILLISECOND_DELAY = 1000 / FRAME_RATE;
    private static final double SPEEDCONTROL = 10;
    private static final String GAMES_PATH = "games/";
    private static final String LEVELS_PATH = "levels/";
    private static final String XML_EXTENSION_SUFFIX = ".xml";
    private static final String NULL_STRING = "";
    private static final String SLASH = "/";
    private IPhysicsEngine myPhysicsEngine;
    private ILevelData myLevelData;
	private IGameDisplay myGameDisplay;
	private ScreenProcessor myScreenProcessor;
	private ElementUpdater myElementUpdater;
	private EventManager myEventManager;
	private Map<String,LevelType> myLevelMap;
	private LevelMapCreator myLevelMapCreator;
	private Timeline myTimeline;
	private boolean playSessionActive;
	private String myCurrentGameString;
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
		//videoScreenshots = new ArrayList<BufferedImage>();
		myTimeline = new Timeline();
		myLevelReached = 0;
		KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY),
				e -> step());
		myTimeline.setCycleCount(Animation.INDEFINITE);
		myTimeline.getKeyFrames().add(frame);
		myStats = new CurrentSessionStats();
	}

	/**
	 * createLevelList reads a text file and creates a list of levels
	 */
	private void createLevelMap(String xmlList) throws FileNotFoundException,
			IOException, VoogaException {
		myLevelMapCreator = new LevelMapCreator(xmlList);
		//myLevelMap = myLevelListCreator.getLevelMap();
		myLevelMap = myLevelMapCreator.getLevelMap();
		System.out.println("What is the Map here " + myLevelMap);
	}

	/**
	 * Public method in the IGameRunner interface that runs the game
	 */
	public void run() {
		myCurrentStep = 0;
		myTimeline.setRate(FRAME_RATE);
		myTimeline.play();
	}

	/**
	 * Stub function that calls methods that need to be called in order
	 */
	private void step() {
		myCurrentStep++;

		checkAndUpdateGlobalVariables();
		myElementUpdater.update(myLevelData);
		myGameDisplay.readAndPopulate(myLevelData.getDisplayableNodes());
		myEventManager.update(myLevelData, myGameDisplay.getMyKeyPresses(),
				myGameDisplay.getMyKeyReleases());
		//myScreenProcessor.updateVideo(videoScreenshots);
		myGameDisplay.clearKeyEvents();
		myScroller.increaseScrollingSpeed(myScroller.getScrollingSprite());
	}

	/**
	 * Checks and updates all LevelData GlobalVariables
	 */

	private void checkAndUpdateGlobalVariables() {
		//update global timer
		myLevelData.updatedGlobalTimer(myCurrentStep * (1 / FRAME_RATE) / SEC_PER_MIN);
		//save progress if at checkpoint
    	if (myLevelData.getSaveNow()) {
    		myStats.saveGameProgress(myLevelMapCreator.getGameFilePath());
    		myLevelData.saveProgress(myCurrentGameString);}
    	
		//check if a level transition effect has been triggered
		if (!myLevelData.getNextLevelName().equals(NULL_STRING)) {
//			playLevel(myLevelList.get(myLevelList.indexOf(myLevelData.getNextLevelName())));
			playLevel(myLevelData.getNextLevelName());      // TRY THIS!!
		}
		
		//transition to leaderboard screen if the last level has been won. ADD AFTER MAP IMPLEMENTATION
		if (myLevelMap.get(myLevelData.getNextLevelName())==LevelType.ENDPOINT) {
			// show win screen
		}
		
	}

	/**
	 * Initializes myLevelList and plays the game
	 */
	public void playGame(String gameXmlList) {
		// start new game playing session
		myStats.startPlaySession();
		playSessionActive = true;
		myCurrentGameString = gameXmlList;
		String latestLevelReached=NULL_STRING;
		//get the last level reached if it exists
		PlaySession playsesh = myStats.getCurrentStatCell().getLatestPlaySession();
		System.out.println("HI THIS IS LOL " + myStats.getCurrentStatCell());
		if (myStats.getCurrentStatCell().checkProgress() != null) {
			latestLevelReached = myStats.getCurrentStatCell().checkProgress();
		}
		try {
			Preferences preferences = (Preferences) Deserializer.deserialize(1, GAMES_PATH + gameXmlList + SLASH + gameXmlList + XML_EXTENSION_SUFFIX).get(0);
			double width = Double.parseDouble(preferences.getWidth());
			double height = Double.parseDouble(preferences.getHeight());
			myGameDisplay.setSceneDimensions(width, height);
			createLevelMap(gameXmlList);
		} catch (Exception e) {
			new VoogaAlert(
					"Level list initialization failed. Try opening in author and re-saving.");
		}
		
		if (latestLevelReached.equals(NULL_STRING)){
	        for (Entry<String, LevelType> entry : myLevelMap.entrySet()) {
	            if (entry.getValue().equals(LevelType.ENTRYPOINT)) {
	                latestLevelReached = entry.getKey();
	            }
	        }
		}
		
		System.out.println("My level is here is called " + latestLevelReached);
		
		//if the 
		myGameDisplay.display();
		playLevel(latestLevelReached);
		run();
	}

	/**
	 * Play a level, called by playGame
	 */
	private void playLevel(String fileName) {
		myLevelReached++;
		myCurrentLevelString = fileName;
		myLevelData.refreshLevelData(myLevelMapCreator.getGameFilePath()
				+ LEVELS_PATH + fileName + XML_EXTENSION_SUFFIX);
		addScrolling();
		myGameDisplay.readAndPopulate(myLevelData.getDisplayableNodes());
	}

	/**
	 * Plays a single level called by authoring for testing purposes
	 */
	@Override
	public void testLevel(String levelName) {
		myCurrentLevelString = levelName.substring(levelName.replace('\\', '/')
				.lastIndexOf('/') + 1, levelName.indexOf(XML_EXTENSION_SUFFIX));
//		myLevelList = Arrays.asList(levelName);
		myLevelMap.put(levelName, LevelType.ENTRYPOINT);
		myLevelData.refreshLevelData(levelName);
		addScrolling();
		myGameDisplay.setSceneDimensions(
				Double.parseDouble(VoogaBundles.preferences
						.getProperty("GameWidth")), Double
						.parseDouble(VoogaBundles.preferences
								.getProperty("GameHeight")));
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
		//System.out.println("This is scrollingSprite: " + scrollingSprite);
		myLevelData.getElements().put(scrollingSprite.getId(), scrollingSprite);
		myScroller.scroll(myLevelData.getGlobalVariables(),
				myCurrentLevelString, scrollingSprite);
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
		promptForSave();
	}

	@Override
	public CompleteAuthoringModelable getManager() {
		return null;
	}

	// PUT THIS CODE HERE ONLY FOR TESTING PURPOSES!!!!!!!!
	private void promptForSave() {
		StatCell statinfo = myStats.getCurrentStatCell();
		statinfo.getLatestPlaySession().endSession();
		VoogaDataBase.getInstance().printDataBase();
		VoogaDataBase.getInstance().save();
	}
	// //Should EVENTUALLY BE TAKEN OUT!!!
	// @Override
	// public void playNextLevel() {
	// myTimeline.stop();
	// myLevelData.setNextLevelName("Lvl2");
	// myTimeline.play();
	// }
	@Override
	public void exit() {
		myTimeline.stop();
		myGameDisplay.exit();
	}

	@Override
	public void takeSnapShot() {
//TODO where to save files?
		Scene myScene = myGameDisplay.getMyScene();
		String currentLevel = myCurrentLevelString;

		String formattedDate = new SimpleDateFormat(
				VoogaBundles.imageProperties.getString("timeStamp"))
				.format(new Date());
		String fileName = currentLevel + formattedDate;

		myScreenProcessor.createSceneScreenshotPNG(myScene, fileName);
	}
/*
	@Override
//TODO where to save?
	public void startVideoCapture() {
		videoScreenshots.clear();
	}

	@Override
	public void endVideoCapture() {
		String outputFileName = VoogaBundles.imageProperties
				.getString("saveLocation")
				+ myCurrentLevelString
				+ VoogaBundles.imageProperties.getString("timeStamp")
				+ VoogaBundles.imageProperties.getString("dotmp4");
	
		myScreenProcessor.encodeScreenshots(outputFileName, videoScreenshots, FRAME_RATE);
		videoScreenshots.clear();
	}
*/
	@Override
	public Timeline getTimeline() {
		return myTimeline;
	}

	@Override
	public void finishPlaySession() {
//		System.out.println((Double) myLevelData.getGlobalVar("Score").getValue());
//		System.out.println(myLevelReached);
		//showLeaderBoard()
		if(playSessionActive){
			myStats.endCurrentPlaySession(((Double) myLevelData.getGlobalVar("Score").getValue()), myLevelReached);
			VoogaDataBase.getInstance().save();
		}
	}

	@Override
	public void addScene() {
		// TODO Auto-generated method stub

	}

	@Override
	public void addScene(CompleteAuthoringModelable manager) {
		// TODO Auto-generated method stub

	}

	@Override
	public void saveAll() {
		// TODO Auto-generated method stub

	}
}