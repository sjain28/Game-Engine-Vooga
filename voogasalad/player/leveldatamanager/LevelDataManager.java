package player.leveldatamanager;

import java.util.List;

import data.FileReaderToObjects;
import events.VoogaEvent;
import gameengine.Variable;
import javafx.scene.Node;


public class LevelDataManager {
	
	private EngineObjectManager myObjectManager;
	private EventManager myEventManager;
	
    public LevelDataManager(String levelFileName) {
    	readinObjects(levelFileName);
    }
	
	public void update(){
		myEventManager.update();
	}
	
	public List<Object> extractUpdatedObjects(){
		return myObjectManager.getAllDisplayableObjects();
	}
	
	private void readinObjects(String levelFileName){
		FileReaderToObjects fileManager = new FileReaderToObjects(levelFileName);
		List<Node> spriteObjects = fileManager.createNodeList();
		System.out.println(spriteObjects);
		List<VoogaEvent> eventObjects = fileManager.createEventList();
		System.out.println(eventObjects);
		List<Variable> variableObjects = fileManager.createVariableList();
		initializeManagers(spriteObjects,eventObjects,variableObjects);
	}
	/**
	 * Creates the Sprites, Events, and Variables which will be loaded into the managers, which include
	 * the sprite, event, and variable managers
	 * 
	 * @return- A LevelManager with all the objects it needs to contain (sprites, events, variables).
	 * 
	 */
	
	private void initializeManagers(List<Node> spriteObjects, List<VoogaEvent> eventObjects, List<Variable>variableObjects){

	}

	
}
	

	
	



