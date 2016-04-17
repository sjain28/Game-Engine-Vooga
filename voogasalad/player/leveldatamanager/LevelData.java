package player.leveldatamanager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import authoring.interfaces.Elementable;
import authoring.model.VoogaFrontEndText;
import data.DataContainerOfLists;
import data.FileReaderToGameObjects;
import events.KeyCause;
import events.VoogaEvent;
import gameengine.Sprite;
import gameengine.SpriteFactory;
import javafx.scene.Node;
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
	 * @return Sprite
	 */
	public Sprite getSprite(String id){
		return (Sprite) myElements.get(id);
	}
	/**
	 * Returns Main Character Sprite
	 * @param id
	 * @return Sprite
	 */
	public Sprite getMainCharacter(){
		return (Sprite) myElements.get(myMainCharacterID);
	}
	public List<Sprite> getAllSprites(){
		List<Sprite> sprites = new ArrayList<Sprite>();
		for(String id : myElements.keySet()){
			if(myElements.get(id) instanceof Sprite){
				sprites.add((Sprite) myElements.get(id));
			}
		}
		return sprites;
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
	 * Removes sprite given an id
	 * @param id
	 */
	public void removeSprite(Object id){
		myElements.remove(id);
	}

	/**
	 * Returns a Global Variable (VoogaData) as specified
	 * by it's variable name
	 * 
	 * @param variable
	 * @return
	 */
	public VoogaData getGlobalVar(String variable){
		return myGlobalVariables.get(variable);
	}

	/**
	 * Returns a VoogaText by id
	 * @param id
	 * @return
	 */
	public VoogaFrontEndText getText(Object id){
		return (VoogaFrontEndText) myElements.get(id);
	}

	/**
	 * put all objects into a generic list of display-able objects
	 * to be accessed by the GameRunner after every update cycle.
	 * 
	 * @return
	 */
	public List<Node> getDisplayableNodes(){
		List<Node> displayablenodes = new ArrayList<Node>();

		for(Object key : myElements.keySet()){
			displayablenodes.add(myElements.get(key).getNodeObject());
		}

		return displayablenodes;

	}
	/**
	 * Populates the LevelData with the Data from a level specified by filename
	 * TODO: Handle continuity here
	 * TODO: Make sure to bind all Sprite images here when they are sent over
	 * 
	 * @param filename
	 */
	public void refreshLevelData(String levelfilename){
		DataContainerOfLists data = new DataContainerOfLists();
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
