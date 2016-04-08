package Player.leveldatamanager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import authoring.model.VoogaText;
import gameengine.Sprite;
import gameengine.SpriteFactory;
import tools.interfaces.VoogaData;

/** Manages Sprite's, Text, and GlobalVariables**/
//TODO: This class is very similar to the ElementManager on the front end
//Potentially compose the ElementManager w this to reduce redundancy.

public class EngineObjectManager {
	
	/**Sprite Info**/
	private Map<String, List<Object>> mySpriteCategories; //Maps archetype name to Sprite IDs
	private Map<Object,Sprite> mySprites;				  //Maps IDs to Sprite's
	private SpriteFactory mySpriteFactory;
	
	/**Text info**/
	private Map<Object,VoogaText> myText;				  //Maps IDs to Text

	/**Global Variable info**/
	private Map<String, VoogaData> myGlobalVariables;

	/**
	 * Takes in a map of Id's to sprite's and a sprite factory
	 * organizes the sprite's in terms of archetype as well
	 * @param sprites
	 * @param factory
	 */
	public EngineObjectManager(List<Sprite> sprites, List<VoogaText> text, SpriteFactory factory) {
		//mySprites = sprites;
		
		//TODO: Once constructor is figured out, intialize all objects here.
		
		mySpriteFactory = factory;
		//place Sprite's by archetype and id in their correct maps
		for(Object key : mySprites.keySet()){
			organizeSpriteByArchetype(mySprites.get(key));
		}
	}
	
	/**
	 * Returns a sprite by id
	 * @param id
	 * @return
	 */
	public Sprite getSprite(Object id){
		return mySprites.get(id);
	}
	
	/**
	 * Returns a list of sprite IDs given an archetype
	 * @param archetype
	 * @return
	 */
	public List<Object> getSpriteIDs(String archetype){
		return mySpriteCategories.get(archetype);
	}
	
	/**
	 * Adds a sprite given an archetype
	 * @param archetype
	 * @return
	 */
	public Sprite addSprite(String archetype){
		Sprite newSprite = mySpriteFactory.createSprite(archetype);
		mySprites.put(newSprite.getID(),newSprite);
		organizeSpriteByArchetype(newSprite);
		return newSprite;
	}
	
	/**
	 * Removes sprite given an id
	 * @param id
	 */
	public void removeSprite(Object id){
		mySpriteCategories.get(mySprites.get(id).getArchetype()).remove(id);
		mySprites.remove(id);
	}
	
	/**
	 * organizes all the Sprite's by archetype as well
	 * @param s
	 */
	private void organizeSpriteByArchetype(Sprite s){
		String archetype = s.getArchetype();
		if(!mySpriteCategories.containsKey(archetype)){
			List<Object> ids = new ArrayList<Object>();
			mySpriteCategories.put(archetype, ids);
		}
		mySpriteCategories.get(archetype).add(s.getID());
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
		return myText.get(id);
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
	public List<Object> getAllDisplayableObjects(){
		List<Object> displayableobjects = new ArrayList<Object>();
		
		for(Object key : mySprites.keySet()){
			displayableobjects.add(mySprites.get(key));
		}
		
		return null;
		
	}
}
