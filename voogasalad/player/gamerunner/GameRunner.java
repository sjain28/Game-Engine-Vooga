package player.gamerunner;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

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
import player.leveldatamanager.DisplayScroller;
import player.leveldatamanager.ElementUpdater;
import player.leveldatamanager.EventManager;
import player.leveldatamanager.IDisplayScroller;
import player.leveldatamanager.ILevelData;
import player.leveldatamanager.LevelData;
import resources.VoogaBundles;
import stats.database.VoogaDataBase;
import stats.interaction.CurrentSessionStats;
import tools.VoogaAlert;
import tools.VoogaException;
import videos.ScreenProcessor;

/**
 * GameRunner runs the game; uses composition to contain LevelData and GameDisplay
 * 
 * @author Hunter, Krista, Michael, Josh
 */
public class GameRunner implements IGameRunner {
	public static final double FRAME_RATE = 60;
	private static final double MILLISECOND_DELAY = 1000 / FRAME_RATE;
	private static final double SPEEDCONTROL = 10;
	private static final String GAMES_PATH = "games/";
	private static final String LEVELS_PATH = "levels/";
	private static final String XML_EXTENSION_SUFFIX = ".xml";
	private static final String NULL_STRING = "";
	private static final String GAME_WIDTH = "GameWidth";
	private static final String GAME_HEIGHT = "GameHeight";
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
	private IDisplayScroller myScroller;
	private CurrentSessionStats myStats;
	private String myCurrentGameString;
	private String myCurrentLevelString;
	private boolean playSessionActive;
	private int myCurrentStep;
	private double myLevelReached = 0;

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
		myTimeline = new Timeline();
		KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step());
		myTimeline.setCycleCount(Animation.INDEFINITE);
		myTimeline.getKeyFrames().add(frame);
		myStats = new CurrentSessionStats();
		myLevelMap = new HashMap<>();
	}

	/**
	 * Reads a text file and creates a map of levels
	 */
	private void createLevelMap(String xmlList) throws IOException, VoogaException {
		myLevelMapCreator = new LevelMapCreator(xmlList);
		myLevelMap = myLevelMapCreator.getLevelMap();
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
		myEventManager.update(myLevelData, myGameDisplay.getMyKeyPresses(), myGameDisplay.getMyKeyReleases());
		myGameDisplay.clearKeyEvents();
		myScroller.increaseScrollingSpeed(myScroller.getScrollingSprite());
	}
	/**
	 * Checks and updates all LevelData GlobalVariables
	 */
	private void checkAndUpdateGlobalVariables() {
		myLevelData.updatedGlobalTimer(myCurrentStep * (1 / FRAME_RATE) / FRAME_RATE);
		if (myLevelData.getSaveNow()) {
			myStats.saveGameProgress(myLevelMapCreator.getGameFilePath());
			myLevelData.saveProgress(myCurrentGameString);
		}
		if (!myLevelData.getNextLevelName().equals(NULL_STRING)) {
			playLevel(myLevelData.getNextLevelName());
			if (myLevelMap.get(myLevelData.getNextLevelName())==LevelType.ENDPOINT) {
				myTimeline.stop();
				return;
			}
		}
	}
	/**
	 * Initializes myLevelList and plays the game
	 */
	public void playGame(String gameXmlList) {
		myStats.startPlaySession();
		playSessionActive = true;
		myCurrentGameString = gameXmlList;
		String latestLevelReached = NULL_STRING;
		if (myStats.getCurrentStatCell().checkProgress() != null) {
			latestLevelReached = myStats.getCurrentStatCell().checkProgress();
		} try {
			Preferences preferences = (Preferences) Deserializer.deserialize(1, GAMES_PATH + gameXmlList + SLASH + gameXmlList + XML_EXTENSION_SUFFIX).get(0);
			myGameDisplay.setSceneDimensions(Double.parseDouble(preferences.getWidth()), Double.parseDouble(preferences.getHeight()));
			VoogaBundles.preferences.setProperty(GAME_WIDTH, preferences.getWidth());
			VoogaBundles.preferences.setProperty(GAME_HEIGHT, preferences.getHeight());
			createLevelMap(gameXmlList);
		} catch (Exception e) {
			VoogaAlert alert = new VoogaAlert("Level list initialization failed. Try opening in author and re-saving.");
			alert.showAndWait();
		}
		if (latestLevelReached.equals(NULL_STRING)){
			for (Entry<String, LevelType> entry : myLevelMap.entrySet()) {
				if (entry.getValue().equals(LevelType.ENTRYPOINT)) {
					latestLevelReached = entry.getKey();
				}
			}
		}
		playLevel(latestLevelReached);
		myGameDisplay.display();
		run();
	}
	/**
	 * Play a level, called by playGame
	 */
	private void playLevel(String fileName) {
		myLevelReached++;
		myCurrentLevelString = fileName;
		myLevelData.refreshLevelData(myLevelMapCreator.getGameFilePath() + LEVELS_PATH + fileName + XML_EXTENSION_SUFFIX);
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
		myLevelMap.put(levelName, LevelType.ENTRYPOINT);
		myLevelData.refreshLevelData(levelName);
		addScrolling();
		myGameDisplay.setSceneDimensions(Double.parseDouble(VoogaBundles.preferences.getProperty(GAME_WIDTH)), 
				Double.parseDouble(VoogaBundles.preferences.getProperty(GAME_HEIGHT)));
		myGameDisplay.displayTestMode();
		run();
	}
	/**
	 * Adds scrolling functionality
	 */
	private void addScrolling() {
		myScroller = new DisplayScroller(myGameDisplay);
		Sprite scrollingSprite = myScroller.createScrollingSprite(myLevelData.getGlobalVariables(), 
				myCurrentLevelString, myLevelData.getMainSprite());
		myLevelData.getElements().put(scrollingSprite.getId(), scrollingSprite);
		myScroller.scroll(myLevelData.getGlobalVariables(), myCurrentLevelString, scrollingSprite);
	}
	/**
	 * Returns the game display in use
	 * @return
	 */
	public IGameDisplay getGameDisplay() {
		return myGameDisplay;
	}
	/**
	 * Speeds up the timeline
	 */
	@Override
	public void speedUp() {
		myTimeline.setRate(myTimeline.getRate() + SPEEDCONTROL);
	}
	/**
	 * Slows down the timeline
	 */
	@Override
	public void speedDown() {
		if (myTimeline.getRate() - SPEEDCONTROL > 0) {
			myTimeline.setRate(myTimeline.getRate() - SPEEDCONTROL);
		}
	}
	/**
	 * Exits the game
	 */
	@Override
	public void exit() {
		myTimeline.stop();
		myGameDisplay.exit();
	}
	/**
	 * Takes a snap shot of the screen
	 */
	@Override
	public void takeSnapShot() {
		Scene myScene = myGameDisplay.getMyScene();
		String currentLevel = myCurrentLevelString;
		String formattedDate = new SimpleDateFormat(VoogaBundles.imageProperties.getString("timeStamp")).format(new Date());
		String fileName = currentLevel + formattedDate;
		myScreenProcessor.createSceneScreenshotPNG(myScene, fileName);
	}

	@Override
	public Timeline getTimeline() {
		return myTimeline;
	}
	/**
	 * Replays a level
	 */
	@Override
	public void replayLevel() {
		myLevelData.setNextLevelName(myCurrentLevelString);
	}
	/**
	 * Finishes a play session if the play session was started
	 */
	@Override
	public void finishPlaySession() {
		if (playSessionActive) {
			myStats.endCurrentPlaySession(((Double) myLevelData.getGlobalVar("Score").getValue()), myLevelReached);
			VoogaDataBase.getInstance().save();
		}
	}
	/**
	 * Empty methods from the Menueable interface
	 */
	@Override
	public CompleteAuthoringModelable getManager() { 
		return null; 
	}

	@Override
	public void addScene() {}

	@Override
	public void addScene(CompleteAuthoringModelable manager) {}

	@Override
	public void saveAll() {}
}