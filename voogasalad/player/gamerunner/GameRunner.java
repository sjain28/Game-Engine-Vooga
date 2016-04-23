package player.gamerunner;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    private IPhysicsEngine myPhysicsEngine;
    private ILevelData myLevelData;
    private SpriteManager mySpriteManager;
    private EventManager myEventManager;
    private String levelsPath = "levels/";
    private String gamesPath;
    private String gamesPrefix = "games/";
    private String myCurrentLevelString;
	private IGameDisplay myGameDisplay;
	private List<String> myLevelList;
	private int myCurrentStep;
	private static Timeline myTimeline;

	/**
	 * Default constructor
	 * 
	 * @param xmlList
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public GameRunner() {
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
	}
	/**
	 * createLevels takes in a text file and out of that file creates a Queue of levels.
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @throws VoogaException 
	 */
	private List<String> createLevelList(String xmlList) throws FileNotFoundException, IOException, VoogaException{
		//Go into the path for this file
		List<String> levelList = new ArrayList<>();
		gamesPath = gamesPrefix + xmlList + "/";
		String resourcePath = gamesPath + xmlList + ".xml";
		levelList = (List<String>) Deserializer.deserialize(1, resourcePath).get(0);
	//	VoogaGame currentGame = new VoogaGame(levelList); //Eventually be able to create a game.
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
//		System.out.println("step");
		
//		System.out.println("The game path here is" + this.gamesPath);
		myCurrentStep++;
		double secondspassed = myCurrentStep*(1/INIT_SPEED)/60;
//		System.out.println(myCurrentStep);
//		System.out.println(secondspassed);
		myLevelData.updatedGlobalTimer(secondspassed);
		
//		System.out.println("Current time in seconds: "+ myLevelData.getGlobalVar("Time"));
		//check if the pane still exists: for debugging purposes
		if(!myGameDisplay.stageIsShowing()){stop();}
		//check if a new level has been triggered or not
		if (!myLevelData.getNextLevelName().equals("")) {
			Optional<String> correctLevel = myLevelList
					.stream()
		            .filter(a -> a.equals(myLevelData.getNextLevelName()))
		            .findFirst();
//			myLevelList.get(myLevelList.indexOf(myLevelData.getNextLevelName()));
//			System.out.println("The next level that's trying to play is " + myLevelData.getNextLevelName());
//			System.out.println("What is myCurrentLevelString here? " + myCurrentLevelString);
//			System.out.println("The current correct level is " + correctLevel.get());
//			playLevel(myLevelList.get(myLevelList.indexOf(myLevelData.getNextLevelName())));
			playLevel(correctLevel.get());
		}
//		System.out.println("The current level is " + myCurrentLevelString);
		//update all Sprite's with physics engine 
		mySpriteManager.update(myLevelData.getAllSprites(),myPhysicsEngine);
		
		//update all Sprite's with Cause and Effect logic
		myEventManager.update(myLevelData, myGameDisplay.getKeyEvents());
		
		//send these updated Nodes to the GameDisplay
		myGameDisplay.read(myLevelData.getDisplayableNodes());
//		System.out.println("The list of displayable nodes here is " + myLevelData.getDisplayableNodes());

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
		System.out.println("gameXMllist here " + gameXmlList);
		try {
			myLevelList = createLevelList(gameXmlList);
		} catch (Exception e) {
			VoogaAlert myAlert = new VoogaAlert("Level List Initialization failed");			
		}
		playLevel(myLevelList.get(0));
		run();
		myGameDisplay.display();
	}

	
	//USES ABSOLUTE PATH!!!
	public void testLevel(String fileName){
		
		myLevelList = new ArrayList<>();
		myLevelList.add(fileName);
		run();
		myGameDisplay.display();
		
//		System.out.println("The play level method is playing here at " + fileNameWithPath);
		
//		System.out.println("The test file here is " + fileName);
		
		//Set the levelNumber to 0 because we are not transitioning anymore
//		System.out.println("Refreshing leveldata");
		myLevelData.refreshLevelData(fileName);
		myGameDisplay.read(myLevelData.getDisplayableNodes());
	}
	
	@Override
	
	/**
	 * playLevel plays a single level. USES RELATIVE PATH. NO LONGER CAN SUPPORT JUST TESTING A SINGLE LEVEL.
	 */
	
	public void playLevel(String fileName){
		//System.out.println("A new level has been started. This level here is " + myCurrentLevelString);
		myCurrentLevelString = fileName;
//		System.out.println("the game path here in play level is " + this.gamesPath);
		String fileNameWithPath = this.gamesPath + levelsPath + fileName; 
//		System.out.println("The filenamewithpath here is " + fileNameWithPath);
		//If debugMode = true, we are only playing one level
//		if (debugMode) {
//			myLevelList = new ArrayList<>();
//			myLevelList.add(fileName);
//			run();
//			myGameDisplay.display();
//		}
		
//		System.out.println("The play level method is playing here at " + fileNameWithPath);
		
		//Set the levelNumber to 0 because we are not transitioning anymore
		myLevelData.refreshLevelData(fileNameWithPath);
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
//		myDelay = myDelay - 100;
        getTimeline().stop();
        double newRate = getTimeline().getRate() - SPEEDCONTROL;
        if (newRate > 0) {
            getTimeline().setRate(newRate);
        }
        getTimeline().play();
	}

	@Override
	public void mute() {
		// TODO Auto-generated method stub

	}

	@Override
	public void replayLevel() {
		// TODO Auto-generated method stub
		myLevelData.setNextLevelName(myCurrentLevelString);
	}
	
	@Override
	public void replayGame() {}
		// TODO Auto-generated method stub

    @Override
    public CompleteAuthoringModelable getManager () {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void addScene () {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void saveAll () {
        // TODO Auto-generated method stub
        
    }

	
	public String getLevelNameString() {
		return this.myCurrentLevelString;
	}

	@Override
	public void playNextLevel() {
		stop();
		System.out.println("The timeline stopped here");
		// TODO Auto-generated method stub
		int tempIndex = myLevelList.indexOf(myCurrentLevelString) + 1;
		String nextLevel = myLevelList.get(tempIndex);
		myLevelData.setNextLevelName(nextLevel);
		getTimeline().play();
	}
    @Override
    public void addScene (CompleteAuthoringModelable manager) {
        // TODO Auto-generated method stub
        
    }
	@Override
	public void exit() {
		// TODO Auto-generated method stub
		myTimeline.stop();
		//myTimeline = null;
		myGameDisplay.exit();
	}


	@Override
	public void saveGameProgress(String playerName) {
		System.out.println("What is gamespath in the save game progress method " + gamesPath);
		myLevelData.saveProgress(gamesPath,playerName);
	}
}