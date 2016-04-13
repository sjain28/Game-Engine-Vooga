package player.gamerunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import javafx.animation.AnimationTimer;
import javafx.scene.Node;
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

	private ILevelDataManager myCurrentLevelDataManager; //This has EventManager
	private IGameDisplay myGameDisplay; //This HAS key events
	private Queue<String> levelQueue;
	private AnimationTimer myTimeline;


	//	private final Consumer<Float> updater = null; // secondsElapsed -> game.step(secondsElapsed, veloctyIter, positonIter)//
	//	private final Runnable renderer = null; // () -> whatever calls position updates //
	//	private final Consumer<Float> interpolater = null; //alpha -> interpolatatePositions(), null for no interpolation //
	//	private final Consumer<Integer> fps_reporter = null; //fps -> Text label for fps display, null for no label //
	//  GameLoop myGameLoop = new FixedStepLoopWithInterpolation();


	/**
	 * Default constructor
	 * 
	 * @param xmlList
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public GameRunner(File xmlList) throws FileNotFoundException, IOException {
		myGameDisplay = new StandardDisplay(getSelf());
		levelQueue = createLevels(xmlList);
		//playGame();
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
	 * createLevels takes in a text file and out of that file creates a Queue of levels.
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	private Queue<String> createLevels(File xmlList) throws FileNotFoundException, IOException{
		Queue<String> levelQueue = new LinkedList<String>();
		try (BufferedReader br = new BufferedReader(new FileReader(xmlList))) {
			String line;
			while ((line = br.readLine()) != null) {
				//TODO: process the line.
				levelQueue.add(line);
			}
		}
		return levelQueue;
	}

	/**
	 * Public method in the IGameRunner interface that runs the game
	 * in the AnimationTimer timeline
	 * 
	 */
	public void run() {
		myTimeline = new AnimationTimer() {
			@Override
			public void handle(long l) {
				step();
			}
		};
		myTimeline.start();
	}

	/**
	 * Stub function that calls methods that need to be called in order
	 * for the animation to proceed
	 * 
	 * Update sprites, get the list of Nodes, and displays
	 * 
	 */
	private void step() {
		myCurrentLevelDataManager.update();		 
		myGameDisplay.read(myCurrentLevelDataManager.getDisplayableObjects());

		myGameDisplay.populateGameScreen();
		//		myGameDisplay.display();
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
		Iterator<String> iterator = levelQueue.iterator();
		while(iterator.hasNext()){
			// if (!l.isWon)
			String nextLevel = iterator.next();
			System.out.println(nextLevel);
			playLevel(nextLevel);
		}
	}

	/**
	 * playLevel plays a single level. This method can be called on its own if the user wants flexibility in testing
	 * only a single level.
	 */
	@Override
	public void playLevel(String fileName){
		System.out.println("What is the file name in this play Level Method?" + fileName);
		myCurrentLevelDataManager = new LevelDataManager(getSelf(), fileName);
		myCurrentLevelDataManager.update();		 
		myGameDisplay.read(myCurrentLevelDataManager.getDisplayableObjects());
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
		return myCurrentLevelDataManager;
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
	public Queue<String> getLevelQueue() {
		return levelQueue;
	}

	/**
	 * @return the myTimeline
	 */
	public AnimationTimer getTimeline() {
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
		getTimeline().start();
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
	@Override
	public List<?> getKeyEvents() {
		return getGameDisplay().getKeyEvents();
	}

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

	}

	@Override
	public void speedDown() {
		// TODO Auto-generated method stub

	}

	@Override
	public void mute() {
		// TODO Auto-generated method stub

	}
}
