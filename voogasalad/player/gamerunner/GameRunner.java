package player.gamerunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Queue;

import authoring.interfaces.model.CompleteAuthoringModelable;
import data.Deserializer;
import data.Serializer;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Node;
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

    private static final int FRAMES_PER_SECOND = 60;
    private static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    private static final int SPEEDCONTROL = 10;
    private static final int INIT_SPEED = 61;
    private IPhysicsEngine myPhysicsEngine;
    private ILevelData myLevelData;
    private SpriteManager mySpriteManager;
    private EventManager myEventManager;
    private String levelsPath = "levels/";
    private String gameLocation = "games/";
    private String myCurrentLevelString;
	private IGameDisplay myGameDisplay; //This HAS key events
	private List<String> myLevelList;
	private Timeline myTimeline;
    
    
    
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
		myPhysicsEngine = new StandardPhysics(FRAMES_PER_SECOND);
		myLevelData = new LevelData(myPhysicsEngine);
		myTimeline = new Timeline();
		KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY),
				e -> step());
		myTimeline.setCycleCount(Animation.INDEFINITE);
		myTimeline.getKeyFrames().add(frame);
	}

//	/**
//	 * Overloaded constructor with String parameter
//	 * 
//	 * @param fileString
//	 * @throws FileNotFoundException
//	 * @throws IOException
//	 */
//	public GameRunner(String fileString) throws FileNotFoundException, IOException {
//		this(new File(fileString));
//	}
//	
//	/**
//	 * Creating a game from a VoogaGame
//	 * 
//	 * @param Voogagame
//	 */
//	public GameRunner(VoogaGame game){
//		myGameDisplay = new StandardDisplay(getSelf());
//		myLevelList = game.getGameLevels();
//		myTimeline = new Timeline();
//		KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY),
//				e -> step());
//		myTimeline.setCycleCount(Animation.INDEFINITE);
//		myTimeline.getKeyFrames().add(frame);
//	}

	
	/**
	 * Specify a GameName for which the folder will contain the file with the list of levels.
	 * 
	 * @param fileString
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	
//	public GameRunner(String gameFolderString) throws FileNotFoundException, IOException {
//		String myGameLocation = gameFolderString + "/";
//		File myFile = new File(gameLocation + myGameLocation + gameFolderString );
//		levelList = createLevelList(xmlList);
//		myTimeline = new Timeline();
//		KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY),
//			e -> step());
//		myTimeline.setCycleCount(Animation.INDEFINITE);
//		myTimeline.getKeyFrames().add(frame);
//	}
	
	/**
	 * createLevels takes in a text file and out of that file creates a Queue of levels.
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
//	private Queue<String> createLevels(File xmlList) throws FileNotFoundException, IOException{
//		Queue<String> levelQueue = new LinkedList<String>();
//		try (BufferedReader br = new BufferedReader(new FileReader(xmlList))) {
//			String line;
//			while ((line = br.readLine()) != null) {
//				//TODO: process the line.
//				levelQueue.add(line);
//			}
//		}
//		return levelQueue;
//	}
	
	/**
	 * createLevels takes in a text file and out of that file creates a Queue of levels.
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
//	@Deprecated
//	private List<String> createLevelList(File xmlList) throws FileNotFoundException, IOException{
//		List <String> levelList = new ArrayList<String>();
//		try (BufferedReader br = new BufferedReader(new FileReader(xmlList))) {
//			String line;
//			while ((line = br.readLine()) != null) {
//				//TODO: process the line.
//				levelList.add(line);
//			}
//		}
//		return levelList;
//	}
	

	/**
	 * createLevels takes in a text file and out of that file creates a Queue of levels.
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @throws VoogaException 
	 */
	private List<String> createLevelList(String xmlList) throws FileNotFoundException, IOException, VoogaException{
		//Go into the path for this file
		List<String> levelList = new ArrayList<>();
		gameLocation = gameLocation + xmlList + "/";
		String resourcePath = gameLocation + xmlList + ".xml";
		levelList = (List<String>) Deserializer.deserialize(1, resourcePath).get(0);
		System.out.println("The resource path here is called" + resourcePath); 
		System.out.println("Levellist is " + levelList);
	//	VoogaGame currentGame = new VoogaGame(levelList); //Eventually be able to create a game.
		return levelList;
	}
	
	/**
	 * Public method in the IGameRunner interface that runs the game
	 * in the AnimationTimer timeline
	 * 
	 */
	public void run() {
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
		//check level transition
		
		if (!myLevelData.getNextLevelName().equals("")) {
//			Optional<String> correctLevel = myLevelList
//					.stream()
//		            .filter(a -> a.equals(myLevelData.getNextLevelName()))
//		            .findFirst();
			System.out.println("The next level that's trying to play is " + myLevelData.getNextLevelName());
			System.out.println("What is myCurrentLevelString here? " + myCurrentLevelString);
			playLevel(myLevelList.get(myLevelList.indexOf(myLevelData.getNextLevelName())), false);
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

//	/**
//	 * Returns nextLevelBit (0: No level change, other numbers indicate the level to 
//	 * transition to)
//	 * 
//	 * @return
//	 */
//	private String getNextLevelName() {
//		return myLevelData.getNextLevelName();
//	}
	
	/**
	 * This makes GameDisplay read in Nodes to display on its screen
	 * nodesToDisplay a list of Nodes filtered by DisplayScroller and
	 * is typed: List<Node>
	 * 
	 */
	@Deprecated
	@Override
	public void read(Collections nodesToDisplay) {
		getGameDisplay().read((List<Node>) nodesToDisplay);
	}

	/**
		playGame plays each level of the game, as long as the game has not been won yet. If the game has been won 
		already, the next level of the game will be played. playGame iterates through the queue of levels
		that is created when the GameController is initialized
	 */
	public void playGame(String xmlList) {
		
		try {
			myLevelList = createLevelList(xmlList);
		} catch (Exception e) {
			VoogaAlert myAlert = new VoogaAlert("Level List Initialization failed");			
		}

		//false because playGame is not in the debugging mode (plays the entire game)
//		System.out.println("My list of levels here is " + myLevelList);
		//Play just the first level.
		playLevel(myLevelList.get(0), false);
		run();
		myGameDisplay.display();
		
//		int nextLevelIndex = 0;
//		if (myLevelData.getLevelNumber() != 0){
//			nextLevelIndex = myLevelData.getLevelNumber();
//		}
//		
////		if (myLevelData.getLevelNumber() > 0){
////			String nextLevel = myLevelList.get(nextLevelIndex);
////			playLevel("levels/" +nextLevel);
////			System.out.println("Did I advance");
////		}
	}

	/**
	 * playLevel plays a single level. This method can be called on its own if the user wants flexibility in testing
	 * only a single level.
	 */
	@Override
	public void playLevel(String fileName, boolean debugMode){
//		System.out.println("A new level has been started. This level here is " + myCurrentLevelString);
		myCurrentLevelString = fileName;
		String fileNameWithPath = this.gameLocation + levelsPath + fileName; 
//		String fileNameWithPath =fileName;
		
//		System.out.println("The filenamewithpath here is" + fileNameWithPath);
		//If debugMode = true, we are only playing one level
		if (debugMode) {
			myLevelList = new ArrayList<>();
			myLevelList.add(fileName);
			run();
			myGameDisplay.display();
		}
		
		System.out.println("The play level method is playing here at " + fileNameWithPath);
		
//		System.out.println(fileName);
		//Set the levelNumber to 0 because we are not transitioning anymore
		myLevelData.refreshLevelData(fileNameWithPath);
		myGameDisplay.read(myLevelData.getDisplayableNodes());
//		myGameDisplay.display();
//		run();
		
//		myCurrentLevelDataManager = new LevelDataManager(getSelf(), fileName);
//		myCurrentLevelDataManager.update();		 
//		myGameDisplay.read(myCurrentLevelDataManager.getDisplayableObjects());
//		myGameDisplay.display();
//		run();
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
	public CompleteAuthoringModelable getManager() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addScene() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveAll() {
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
}