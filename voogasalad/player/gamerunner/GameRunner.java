package Player.gamerunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Consumer;

import player.gamedisplay.IGameDisplay;
import player.gamedisplay.StandardDisplay;
import player.leveldatamanager.LevelDataManager;

public class GameRunner implements IGameRunner{
	
	private LevelDataManager currentLevelDataManager;
	private IGameDisplay myDisplay;
	private Queue<String> levelQueue;
	private final Consumer<Float> updater = null;
	private final Runnable renderer = null;
	private final Consumer<Float> interpolater = null; 
	private final Consumer<Integer> fps_reporter = null; 
	GameLoop myGameLoop = new FixedStepLoopWithInterpolation(updater, renderer, interpolater, fps_reporter);

	
	public GameRunner(File xmlList) throws FileNotFoundException, IOException {
		// TODO Auto-generated constructor stub
		myDisplay = new StandardDisplay();
		levelQueue = createLevels(xmlList);
		playGame();
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
		playGame plays each level of the game, as long as the game has not been won yet. If the game has been won 
		already, the next level of the game will be played. playGame iterates through the queue of levels
		that is created when the GameController is initialized
	 */
	public void playGame(){
		Iterator<String> iterator = levelQueue.iterator();
		while(iterator.hasNext()){
			// if (!l.isWon)
			String nextLevel = iterator.next();
			playLevel(nextLevel);
		}
	}
	
	/**
	 * playLevel plays a single level. This method can be called on its own if the user wants flexibility in testing
	 * only a single level.
	 */
	
	public void playLevel(String s){
		currentLevelDataManager = new LevelDataManager(s);
		myDisplay.display();
	}
	

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void start() {
		// TODO Auto-generated method stub
		
	}


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
	
	@Override
	public void getFrameRate() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void setFrameRate(double frameRate) {
		// TODO Auto-generated method stub
		
	}
}
