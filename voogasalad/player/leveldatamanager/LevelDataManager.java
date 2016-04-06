package player.leveldatamanager;

import java.util.List;

import data.FileReaderToObjects;
import events.Event;
import gameengine.EventManager;
import gameengine.Sprite;
import gameengine.SpriteManager;
import gameengine.Variable;
import gameengine.VariableManager;
import javafx.scene.Node;


public class LevelDataManager {

	SpriteManager spriteManager;
	EventManager eventManager;
	VariableManager variableManager;
	
	public LevelDataManager(String s) {
		// TODO Auto-generated constructor stub
		spriteManager = new SpriteManager();
		eventManager = new EventManager();
		variableManager = new VariableManager();
		readinObjects(s);
	}
	
	/**
	 * Creates the Sprites, Events, and Variables which will be loaded into the managers, which include
	 * the sprite, event, and variable managers
	 * 
	 * @return- A LevelManager with all the objects it needs to contain (sprites, events, variables).
	 * 
	 */
	
	
	private void readinObjects(String levelFileName){
		FileReaderToObjects fileManager = new FileReaderToObjects(levelFileName);
		List<Node> spriteObjects = fileManager.createNodeList();
		List<Event> eventObjects = fileManager.createEventList();
		List<Variable> variableObjects = fileManager.createVariableList();
		initializeManagers(spriteObjects,eventObjects,variableObjects);
	}

	public void initializeManagers(List<Node> spriteObjects, List<Event> eventObjects, List<Variable> variableObjects) {
		// TODO Auto-generated constructor stub
		
		eventManager.addEventList(eventObjects);
		//eventManager = new EventManager(eventObjects);
		//variableManager = new VariableManager(VariableObjects);
	}

}
