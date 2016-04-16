package player.gamerunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.util.Duration;
import player.gamedisplay.IGameDisplay;
import player.gamedisplay.StandardDisplay;
import player.leveldatamanager.ILevelDataManager;
import player.leveldatamanager.LevelDataManager;

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
public class GameRunner implements IGameRunner{

    private static final int FRAMES_PER_SECOND = 1;
    private static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    private static final int SPEEDCONTROL = 10;
    private static final int INIT_SPEED = 61;
    
    private String gameLocation = "games/";
	private ILevelDataManager myLevelDataManager; //This has EventManager
	private IGameDisplay myGameDisplay; //This HAS key events
	private List<String> levelList;
//	private Queue<String> levelQueue;
	private Timeline myTimeline;

	/**
	 * Default constructor
	 * 
	 * @param xmlList
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public GameRunner(File xmlList) throws FileNotFoundException, IOException {
		myGameDisplay = new StandardDisplay(getSelf());
		levelList = createLevelList(xmlList);
		myTimeline = new Timeline();
		KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY),
				e -> step());
		myTimeline.setCycleCount(Animation.INDEFINITE);
		myTimeline.getKeyFrames().add(frame);
	}

	/**
	 * Overloaded constructor with String parameter
	 * 
	 * @param fileString
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public GameRunner(String fileString) throws FileNotFoundException, IOException {
		this(new File(fileString));
	}

	
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
	private List<String> createLevelList(File xmlList) throws FileNotFoundException, IOException{
		List <String> levelList = new ArrayList<String>();
		try (BufferedReader br = new BufferedReader(new FileReader(xmlList))) {
			String line;
			while ((line = br.readLine()) != null) {
				//TODO: process the line.
				levelList.add(line);
			}
		}
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
		//take care of setting and resetting key events
		//System.out.println("Getting key events from standard display in game runner: "+myGameDisplay.getKeyEvents());
		myLevelDataManager.setKeyEvents(myGameDisplay.getKeyEvents());
		myGameDisplay.clearKeyEvents();
		
		//update all logic in the backend, updating game objects w/ Causes and Events
		myLevelDataManager.update();		 
		
		//send these updated Nodes to the GameDisplay
		myGameDisplay.read(myLevelDataManager.getDisplayableObjects());

		//repopulate the game screen
		myGameDisplay.populateGameScreen();
	}

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
	public void playGame(){
		Iterator<String> iterator = levelList.iterator();
		while(iterator.hasNext()){
			// if (myLevelDataManager.nextLevel > 0)
			String nextLevel = iterator.next();
			playLevel(nextLevel);
			// playLevel(level.nextLevel);
		}
	}

	/**
	 * playLevel plays a single level. This method can be called on its own if the user wants flexibility in testing
	 * only a single level.
	 */
	@Override
	public void playLevel(String fileName){
		myLevelDataManager = new LevelDataManager(getSelf(), fileName);
		myLevelDataManager.update();		 
		myGameDisplay.read(myLevelDataManager.getDisplayableObjects());
		myGameDisplay.display();
		run();
	}

	/**
	 * Checks if the current level has been won
	 * If the level has been won, advance to the next level
	 * 
	 */
	public void wonLevel(){
		//		if(myCurrentLevel == myLevels.size()-1){
		//			//tell the display to display whatever they display when you've won a game
		//		}
		//		else{
		//			//tell the display to display whatever they display when you've won a level 
		//			//and must proceed to the next.
		//			myCurrentLevel++;
		//		}
	}

	/**
	 * Checks if the current level has been lost
	 * If the level has been lost, go to the gameover screen
	 * 
	 */
	public void lostLevel(){
		//tell the display to display restart game button and a you lost the game sign

		//		myCurrentLevel = 1;
	}

	/**
	 * @return the myCurrentLevelDataManager
	 */
	public ILevelDataManager getCurrentLevelDataManager() {
		return myLevelDataManager;
	}

	/**
	 * @return the myGameDisplay
	 */
	public IGameDisplay getGameDisplay() {
		return myGameDisplay;
	}

	/**
	 * @return the levelQueue
	 */
//	public Queue<String> getLevelQueue() {
//		return levelQueue;
//	}
	
	/**
	 * @return the List of levels to be played.
	 */
	public List<String> getLevelList() {
		return levelList;
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
//		getTimeline().start();
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

	/**
	 * Returns KeyEvents to be passed into LevelDataManager
	 * 
	 * KeyEvents (Collections--List<KeyEvent>) are passed to LevelDataManager
	 * through GameRunner
	 * 
	 */
//	@Override
//	public List<?> getKeyEvents() {
//		System.out.println("Getting key events from GameDisplay in GameRunner: "+getGameDisplay().getKeyEvents().size());
//		return getGameDisplay().getKeyEvents();
//	}

	/**
	 * Clears KeyEvents collections after applying events
	 * (cause and effects) to sprites
	 * 
	 */
	@Override
	public void clearKeyEvents() {

	}

	@Override
	public void speedUp() {
		// TODO Auto-generated method stub
//		if (myDelay >= 0) {
//			myDelay = myDelay - 100;
//			if (myDelay < 0) {
//				myDelay = 1;
//			}
//		}
		
        getTimeline().stop();
        getTimeline().setRate(getTimeline().getRate() + SPEEDCONTROL);
        getTimeline().play();

	}

	@Override
	public void speedDown() {
//		// TODO Auto-generated method stub
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
		
	}

	@Override
	public void advanceToNextLevel() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void replayGame() {
		// TODO Auto-generated method stub
		
	}
}
