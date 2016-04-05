/*
 * The GameEngine holds all of the managers and contains the initialize and update methods for the game. The game player
 * will have an instance of the GameEngine that allows it to update the game.
 */
package Player.runner;

import java.util.Iterator;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


import GameEngine.FileReadingManager;
import GameEngine.Sprite;
import GameEngine.Variable;
import Player.gui.GameDisplay;
import Player.gui.GameDisplayer;
import Player.leveldatamanager.LevelDataManager;
import events.Event;

public class GameRunner {

	private GameDisplay GameDisplay;
	private Queue<String> levelQueue;
	
	public GameRunner(File xmlList) throws FileNotFoundException, IOException {
		// TODO Auto-generated constructor stub
		GameDisplay = new GameDisplayer();
		levelQueue = createLevels(xmlList);
	}
	
	
	/**
	 * initFileManager creates a fileManager that contains an ArrayList of Sprites, Events, and Variables.
	 */
	private LevelDataManager initFileManager(String fileName){
		FileReadingManager fileManager = new FileReadingManager(fileName);
		LevelDataManager levelManager = createLevelObjects(fileManager);
		return levelManager;	
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
	 * Creates the Sprites, Events, and Variables which will be loaded into the LevelManager, which contains
	 * the sprite, event, and variable managers
	 * 
	 * @return- A LevelManager with all the objects it needs to contain (sprites, events, variables).
	 * 
	 */
	
	private LevelDataManager createLevelObjects(FileReadingManager fileManager){
		List<Sprite> spriteObjects = fileManager.createSpriteList();
		List<Event> eventObjects = fileManager.createEventList();
		List<Variable> variableObjects = fileManager.createVariableList();
		LevelDataManager levelManager = new LevelDataManager(spriteObjects, eventObjects, variableObjects);
		return levelManager;
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
		GameDisplay.display(initFileManager(s));
	}
	
	/**
	 * playLevel updates the frames of the GamePlayer.
	 */
	
	public void update(){
		GameDisplay.update();
	}

}
