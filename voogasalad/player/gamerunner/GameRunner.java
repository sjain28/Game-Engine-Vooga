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
import player.gamedisplay.IGameDisplay;
import player.gamedisplay.StandardDisplay;
import player.leveldatamanager.ILevelDataManager;
import player.leveldatamanager.LevelDataManager;

public class GameRunner implements IGameRunner{

	private ILevelDataManager myCurrentLevelDataManager;
	private IGameDisplay myGameDisplay;
	private Queue<String> levelQueue;
	/*
	private final Consumer<Float> updater = null; // secondsElapsed -> game.step(secondsElapsed, veloctyIter, positonIter)//
	private final Runnable renderer = null; // () -> whatever calls position updates //
	private final Consumer<Float> interpolater = null; //alpha -> interpolatatePositions(), null for no interpolation //
	private final Consumer<Integer> fps_reporter = null; //fps -> Text label for fps display, null for no label //
	 */
	//GameLoop myGameLoop = new FixedStepLoopWithInterpolation();
	private AnimationTimer myTimeline;

	public GameRunner(File xmlList) throws FileNotFoundException, IOException {
		// TODO Auto-generated constructor stub
		myGameDisplay = new StandardDisplay();
		levelQueue = createLevels(xmlList);
		//System.out.println(levelQueue);

		//TODO fix the instantiation of AnimationTimer(); 
		//timer = new AnimationTimer();

		//playGame();
		//playGame();
	}

	public GameRunner(String fileString) throws FileNotFoundException, IOException {
		// TODO Auto-generated constructor stub
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
				// process the line.
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
		myGameDisplay.display();
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

	public void playLevel(String fileName){
		System.out.println("What is the file name in this play Level Method?" + fileName);
		myCurrentLevelDataManager = new LevelDataManager(fileName);
		//call the time line here and pass step into time line
		step();
	}


	/**
	 * Stops the AnimationTimer
	 * 
	 */
	/*
	@Override
	public void stop() {
		// TODO Auto-generated method stub
		getGameLoop().stop();
	}
	 */
	/**
	 * Starts the AnimationTimer
	 * 
	 */
	/*
	@Override
	public void start() {
		// TODO Auto-generated method stub
		getGameLoop().start();
	}
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
		// TODO Auto-generated method stub
		getTimeline().stop();
	}

	/**
	 * Starts the timeline (AnimationTimer)
	 * 
	 */
	@Override
	public void start() {
		// TODO Auto-generated method stub
		getTimeline().start();
	}
	
	/**
	 * Returns an interface of this class
	 * Java's covariant return types
	 * 
	 */
	@Override
	public IGameRunner getSelf() {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public Collections getKeyEvents() {
		// TODO Auto-generated method stub
		return (Collections) getGameDisplay().getKeyEvents();
	}

	/**
	 * @return the updater
	 */
	/*
	public Consumer<Float> getUpdater() {
		return updater;
	}
	 */
	/**
	 * @return the renderer
	 */
	/*
	public Runnable getRenderer() {
		return renderer;
	}
	 */
	/**
	 * @return the interpolater
	 */
	/*
	public Consumer<Float> getInterpolater() {
		return interpolater;
	}
	 */
	/**
	 * @return the fps_reporter
	 */
	/*
	public Consumer<Integer> getFps_reporter() {
		return fps_reporter;
	}
	 */
	/**
	 * @return the myGameLoop
	 */
	/*
	public GameLoop getGameLoop() {
		return myGameLoop;
	}
	 */
}
