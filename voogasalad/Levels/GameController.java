package Levels;

import java.util.Iterator;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

import GameEngine.Variable;
import GameEngine.FileReadingManager;
import GameEngine.Sprite;
import Player.LevelPlayer;
import events.Event;

public class GameController {

	private LevelPlayer levelPlayer;
	private Queue<String> levelQueue;
	
	public GameController(File xmlList) throws FileNotFoundException, IOException {
		// TODO Auto-generated constructor stub
		levelPlayer = new LevelPlayer();
		levelQueue = createLevels(xmlList);
	}
	
	
	/**
	 * initFileManager creates a fileManager that contains an ArrayList of Sprites, Events, and Variables.
	 */
	private LevelManager initFileManager(String fileName){
		XStream xstream = new XStream(new StaxDriver());
		FileReadingManager fileManager = new FileReadingManager(xstream, fileName);
		LevelManager levelManager = createLevelObjects(fileManager);
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
	
	private LevelManager createLevelObjects(FileReadingManager fileManager){
		List<Sprite> spriteObjects = fileManager.createSpriteList();
		List<Event> eventObjects = fileManager.createEventList();
		List<Variable> variableObjects = fileManager.createVariableList();
		LevelManager levelManager = new LevelManager(spriteObjects, eventObjects, variableObjects);
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
		levelPlayer.play(initFileManager(s));
	}
	
	/**
	 * playLevel updates the frames of the GamePlayer.
	 */
	
	public void update(){
		levelPlayer.update();
	}

}
