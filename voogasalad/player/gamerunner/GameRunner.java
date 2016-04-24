package player.gamerunner;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import authoring.interfaces.model.CompleteAuthoringModelable;
import authoring.model.ElementManager;
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

    private static final double INIT_SPEED = 1;
    private static final double MILLISECOND_DELAY = 1000 / INIT_SPEED;
    private static final double SPEEDCONTROL = 10;
    
    private IPhysicsEngine myPhysicsEngine;
    private ILevelData myLevelData;
    private SpriteManager mySpriteManager;
    private EventManager myEventManager;
    private String levelsPath = "levels/";
    private String gamesPath;
    private String gamesPrefix = "games/";
    private String xmlSuffix = ".xml";
    private String myCurrentLevelString;
	private IGameDisplay myGameDisplay;
	private List<String> myLevelList;
	private int myCurrentStep;
	private Timeline myTimeline;
	
	private boolean DEBUG;


	/**
	 * Default constructor
	 * 
	 * @param xmlList
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public GameRunner(boolean debug) {
		myGameDisplay = new StandardDisplay(getSelf());
		mySpriteManager = new SpriteManager();
		myEventManager = new EventManager();
		myPhysicsEngine = new StandardPhysics();
		myLevelData = new LevelData(myPhysicsEngine);
		myTimeline = new Timeline();
		KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY),
				e -> step());
		myTimeline.setCycleCount(Animation.INDEFINITE);
		myTimeline.getKeyFrames().add(frame);
		gamesPath = gamesPrefix;
		DEBUG = debug;
	}
	
	/**
	 * createLevels takes in a text file and out of that file creates a list of levels
	 * 
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @throws VoogaException 
	 */
	private List<String> createLevelList(String xmlList) throws FileNotFoundException, IOException, VoogaException{
		List<String> levelList = new ArrayList<>();
		gamesPath = gamesPrefix + xmlList + "/";
		String resourcePath = gamesPath + xmlList + ".xml";
		levelList = (List<String>) Deserializer.deserialize(1, resourcePath).get(0);
		return levelList;
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
		
		//check if the pane still exists: for debugging purposes
		if(!myGameDisplay.stageIsShowing()){stop();}
		//check if a new level has been triggered or not
		if (!myLevelData.getNextLevelName().equals("")) {
			Optional<String> correctLevel = myLevelList
					.stream()
		            .filter(a -> a.equals(myLevelData.getNextLevelName()))
		            .findFirst();
			playLevel(correctLevel.get());
		}

		//update all Sprite's with physics engine 
		mySpriteManager.update(myLevelData.getAllSprites(),myPhysicsEngine);
		
		//update all Sprite's with Cause and Effect logic
		myEventManager.update(myLevelData, myGameDisplay.getKeyEvents());
		
		//send these updated Nodes to the GameDisplay
		myGameDisplay.read(myLevelData.getDisplayableNodes());

		//re-populate the game screen
		myGameDisplay.populateGameScreen();
		
		//clear key events from myGameDisplay.
		myGameDisplay.clearKeyEvents();	
	}
	
	/**
		playGame plays each level of the game, as long as the game has not been won yet. If the game has been won 
		already, the next level of the game will be played. playGame iterates through the queue of levels
		that is created when the GameController is initialized
	 */
	public void playGame(String gameXmlList) {
		try {
			myLevelList = createLevelList(gameXmlList);
		} catch (Exception e) {
			new VoogaAlert("Level List Initialization failed");			
		}
		playLevel(myLevelList.get(0));
		run();
		
		// FALSE BECAUSE WE ARE NOT IN DEBUG MODE!!!
		myGameDisplay.display(DEBUG);
	}
	
	@Override
	
	/**
	 * playLevel plays a single level. USES RELATIVE PATH. NO LONGER CAN SUPPORT JUST TESTING A SINGLE LEVEL.
	 */
	
	public void playLevel(String fileName){
		
		myCurrentLevelString = fileName;
		if (DEBUG){
			myLevelList = new ArrayList<>();
			myLevelList.add(fileName);
			run();		
			myLevelData.refreshLevelData(fileName);
			myGameDisplay.display(DEBUG);
		}
		else{
			String fileNameWithPath = this.gamesPath + levelsPath + fileName + xmlSuffix; 
			
			//Set the levelString to 0 because we are not transitioning anymore
			myLevelData.refreshLevelData(fileNameWithPath);
			return;
		}
		myGameDisplay.read(myLevelData.getDisplayableNodes());
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
		System.out.println("What is gamespath in the save game progress method " + gamesPath);
		myLevelData.saveProgress(gamesPath,playerName);
	}
	@Override
	public void testLevel(String levelName) {	
	}
	
	public void openScene(ElementManager unserialize) {
		// TODO Auto-generated method stub
		
	}
}