package player.leveldatamanager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import authoring.interfaces.Elementable;
import data.LevelDataContainer;
import data.FileReaderToGameObjects;
import events.KeyCause;
import events.VoogaEvent;
import gameengine.Sprite;
import gameengine.SpriteFactory;
import javafx.scene.input.KeyEvent;
import tools.interfaces.VoogaData;

/**
 * A centralized class to contain and access data relevant to a level
 * This includes Sprite's, Text, Global Variables, and Events
 * 
 * 
 * @author Krista
 *
 */
public class LevelData {
	/**Sprite and Text Information**/
	private String myMainCharacterID;
	private Map<String,Elementable> myElements;
	private SpriteFactory mySpriteFactory;
	
	/**Global Variable Information**/
	private Map<String, VoogaData> myGlobalVariables;
	
	/** Event Information**/
	private List<VoogaEvent> myEvents;
	
	//TODO: REFACTOR EXACTLY WHAT GETTER AND SETTER METHODS WE WANT IN HERE
	
	/**
	 * Returns a sprite by id
	 * @param id
	 * @return
	 */
	public Sprite getSprite(String id){
		return (Sprite) myElements.get(id);
	}
	/**
	 * Returns a list of sprite IDs given an archetype
	 * @param archetype
	 * @return
	 */
	public List<String> getSpriteIDs(String archetype){
		List<String> list = new ArrayList<String>();
		for(String id : myElements.keySet()){
			if(myElements.get(id) instanceof Sprite){
				if(((Sprite) myElements.get(id)).getArchetype().equals(archetype)){
					list.add(id);
				}
			}
		}
		return list;
	}
	/**
	 * Adds a sprite given an archetype
	 * @param archetype
	 * @return 
	 */
	public Sprite addSprite(String archetype){
		Elementable newSprite = mySpriteFactory.createSprite(archetype);
		myElements.put(newSprite.getID(),newSprite);
		return (Sprite) newSprite;
	}
	/**
	 * Populates the LevelData with the Data from a level specified by filename
	 * TODO: Handle continuity here
	 * 
	 * @param filename
	 */
	public void refreshLevelData(String levelfilename){
		LevelDataContainer data = new LevelDataContainer();
		FileReaderToGameObjects fileManager = new FileReaderToGameObjects(levelfilename);
		data = fileManager.getDataContainer();
      
		List<Elementable> spriteObjects = data.getElementableList();
		System.out.println("All the sprites here are" + spriteObjects);

		List<VoogaEvent> eventObjects = data.getEventList();
		System.out.println("All the events here are" + eventObjects);

		SpriteFactory factory = data.getSpriteFactory();
		System.out.println("The spriteFactory here is" + factory);

		Map<String,VoogaData> variableObjects = data.getVariableMap();
		System.out.println("All the variables here are" + variableObjects);
	}
	
}
