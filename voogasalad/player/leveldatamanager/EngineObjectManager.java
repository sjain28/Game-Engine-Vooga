package player.leveldatamanager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import authoring.interfaces.Elementable;
import authoring.model.VoogaText;
import gameengine.Sprite;
import gameengine.SpriteFactory;
import javafx.scene.Node;
import tools.interfaces.VoogaData;

/** Manages Sprite's, Text, and GlobalVariables**/
//TODO: This class is very similar to the ElementManager on the front end
//Potentially compose the ElementManager w this to reduce redundancy.

public class EngineObjectManager {
	
	/**Elements Info**/
	private Map<String,Elementable> myElements;				  //Maps IDs to Sprite's
	
	private SpriteFactory mySpriteFactory;

	/**Global Variable info**/
	private Map<String, VoogaData> myGlobalVariables;

	/**
	 * Takes in a map of Id's to sprite's and a sprite factory
	 * organizes the sprite's in terms of archetype as well
	 * @param sprites
	 * @param factory
	 */
	public EngineObjectManager(List<Elementable> elements, Map<String,VoogaData> data, SpriteFactory factory) {
		myElements = new HashMap<String,Elementable>();
		for(Elementable el : elements){
			myElements.put(el.getID(), el);
		}
		
		myGlobalVariables = new HashMap<String, VoogaData>(data);
		
		//TODO: Once constructor is figured out, intialize all objects here.
		mySpriteFactory = factory;
	}
	
	
	
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
	public Elementable addSprite(String archetype){
		Elementable newSprite = mySpriteFactory.createSprite(archetype);
		myElements.put(newSprite.getID(),newSprite);
		return newSprite;
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
	public VoogaText getText(Object id){
		return (VoogaText) myElements.get(id);
	}
	
	/**
	 * put all objects into a generic list of display-able objects
	 * to be accessed by the GameRunner after every update cycle.
	 * 
	 * TODO: Make VoogaText and Sprite extend the same thing so they can 
	 * be stored in the same map in the future and so that they 
	 * don't need to be transfered to the same list every time they 
	 * are updated.
	 * @return
	 */
	public List<Node> getAllDisplayableNodes(){
		List<Node> displayablenodes = new ArrayList<Node>();
		
		for(Object key : myElements.keySet()){
			displayablenodes.add(myElements.get(key).getNodeObject());
		}
		
		return displayablenodes;
		
	}

//	/**
//	 * Allows one to get a list of Sprite's by their archetypes
//	 * @param myArchetype
//	 * @return
//	 */
//	public List<Sprite> getSpritesByArchetype(String myArchetype) {
//		List<Sprite> archSprites = new ArrayList<Sprite>();
//		for(String id : myElements.keySet()){
//			Elementable el = myElements.get(id);
//			if(el instanceof Sprite){
//				if(((Sprite) el).getArchetype().equals(myArchetype)){
//					archSprites.add((Sprite) el);
//				}
//			}
//		}
//		return archSprites;
//	}
}
