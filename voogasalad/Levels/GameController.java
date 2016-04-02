package Levels;

import java.util.List;
import java.util.Queue;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

import GameEngine.Variable;
import GameEngine.Sprite;
import Player.LevelPlayer;
import events.Event;
import tools.FileManager;

public class GameController {

	private FileManager fileManager;
	private LevelManager levelManager;
	private LevelPlayer levelPlayer;
	private Queue<Level> levelQueue;
	
	public GameController() {
		// TODO Auto-generated constructor stub
		initFileManager();
	}
	
	
	/**
	 * createLevels takes in a text file and out of that file creates a Queue of levels.
	 */
	public void initFileManager(){
		XStream xstream = new XStream(new StaxDriver());
		fileManager = new FileManager(xstream);
	}
	
	/**
	 * createLevels takes in a text file and out of that file creates a Queue of levels.
	 */
	public Queue<Level> createLevels(){
		return null;
	}
	
	/**
	 * Creates the Sprites, Events, and Variables which will be loaded into the LevelManager, which contains
	 * the sprite, event, and variable managers
	 * 
	 * 
	 */
	
	public LevelManager createLevelObjects(){
		List<Sprite> spriteObjects = fileManager.createSpriteList();
		List<Event> eventObjects = fileManager.createEventList();
		List<Variable> variableObjects = fileManager.createVariableList();
		LevelManager levelManager = new LevelManager(spriteObjects, eventObjects, variableObjects);
		return levelManager;
	}
	
	
	/**
		playGame plays each level of the game, as long as the game has not been won yet. If the game has been won 
		already, the next level of the game will be played.
	 */
	public void playGame(){
		for (Level l: levelQueue){
			// if (!l.isWon)
			playLevel(l);
		}
	}
	
	/**
	 * createLevels takes in a text file and out of that file creates a Queue of levels.
	 */
	
	public void playLevel(Level l){
		
	}
	
//	public void update(){
//		levelManager.update();
//	}
	
//	public void 
	
	
	
	
	
	

}
