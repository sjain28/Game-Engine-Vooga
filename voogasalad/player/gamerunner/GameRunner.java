package player.gamerunner;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import authoring.interfaces.model.CompleteAuthoringModelable;
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
 * Uses composition to contain interface instances of LevelDataManager and GameDisplay
 * 
 * @author Hunter, Michael, Josh
 */
public class GameRunner implements IGameRunner {
	//TODO: was changed to 1 for debugging purposes
    //private static final double INIT_SPEED = 1;
    private static final double INIT_SPEED = 60;
    private static final double MILLISECOND_DELAY = 1000 / INIT_SPEED;
    private static final double SPEEDCONTROL = 10;
    private static final String LEVELS_PATH = "levels/";
    private static final String XML_EXTENSION_SUFFIX = ".xml";
    private IPhysicsEngine myPhysicsEngine;
    private ILevelData myLevelData;
	private IGameDisplay myGameDisplay;
    private SpriteManager mySpriteManager;
    private EventManager myEventManager;
	private List<String> myLevelList;
	private LevelListCreator myLevelListCreator;
	private Timeline myTimeline;
    private String myCurrentLevelString;
    // TODO: Test
	private int myCurrentStep;

	/**
	 * Default constructor
	 */
	public GameRunner() {
		myGameDisplay = new StandardDisplay(getSelf());
		myPhysicsEngine = new StandardPhysics();
		mySpriteManager = new SpriteManager();
		myEventManager = new EventManager();
		myLevelData = new LevelData(myPhysicsEngine);
		myTimeline = new Timeline();
		KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step());
		myTimeline.setCycleCount(Animation.INDEFINITE);
		myTimeline.getKeyFrames().add(frame);		
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
		double secondspassed = myCurrentStep * (1 / INIT_SPEED) / 60;
		myLevelData.updatedGlobalTimer(secondspassed);
		//check if we need to transition to a different level
		if (!myLevelData.getNextLevelName().equals("")) {
			playLevel(myLevelList.get(myLevelList.indexOf(myLevelData.getNextLevelName())));
		}
		mySpriteManager.update(myLevelData, myPhysicsEngine);
		myGameDisplay.readAndPopulate(myLevelData.getDisplayableNodes());
		myEventManager.update(myLevelData, myGameDisplay.getMyKeyPresses(), myGameDisplay.getMyKeyReleases());
		myGameDisplay.clearKeyEvents();	
	}
	/**
	 * 	Initializes myLevelList and plays the game
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
	 */
	private void playLevel(String fileName){
		myCurrentLevelString = fileName;
		myLevelData.refreshLevelData(myLevelListCreator.getGameFilePath() + LEVELS_PATH + fileName + XML_EXTENSION_SUFFIX);
		myGameDisplay.readAndPopulate(myLevelData.getDisplayableNodes());
	}
	/**
	 * Plays a single level called by authoring for testing purposes
	 */
	@Override
	public void testLevel(String levelName) {
		myLevelList = Arrays.asList(levelName);
		myLevelData.refreshLevelData(levelName);
		myGameDisplay.displayTestMode();
		run();
	}

	public IGameDisplay getGameDisplay() {
		return myGameDisplay;
	}

	@Override
	public void stop() {
		myTimeline.stop();
	}

	@Override
	public void start() {
		myTimeline.play();
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
	public void mute() {
	}
	@Override
	public void replayLevel() {
		myLevelData.setNextLevelName(myCurrentLevelString);
	}

    @Override
    public CompleteAuthoringModelable getManager () {
        return null;
    }

    @Override
    public void saveAll () {        
    }

	@Override
	public void playNextLevel() {
		stop();
		myLevelData.setNextLevelName("Lvl2");
		myTimeline.play();
	}
	
    @Override
    public void addScene () {        
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
		myLevelData.saveProgress(myLevelListCreator.getGameFilePath(), playerName);
	}
	
	@Override
	public IGameRunner getSelf() {
		return this;
	}
}