/*
 * The GameEngine holds all of the managers and contains the initialize and update methods for the game. The game player
 * will have an instance of the GameEngine that allows it to update the game.
 */
package player.runner;

import java.util.Iterator;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

import javafx.animation.Timeline;
import player.gui.IGameDisplay;
import player.leveldatamanager.LevelDataManager;
import player.gui.StandardDisplay;

public class GameRunner {

	private Timeline animationTimer;
	private LevelDataManager currentLevelDataManager;
	private IGameDisplay GameDisplay;
	private Queue<String> levelQueue;
	
	public GameRunner(File xmlList) throws FileNotFoundException, IOException {
		// TODO Auto-generated constructor stub
		GameDisplay = new StandardDisplay();
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
		GameDisplay.display();
	}
	
	
}
