package gameengine;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import authoring.model.VoogaText;
import tools.interfaces.VoogaData;

/** Manages Sprite's, Text, and GlobalVariables**/

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
	public EngineObjectManager(Map<Object,Sprite> sprites,  SpriteFactory factory) {
		mySprites = sprites;
		mySpriteFactory = factory;
		//place Sprite's by archetype and id in their correct maps
		for(Object key : mySprites.keySet()){
			organizeSpritesByArchetype(mySprites.get(key));
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
		organizeSpritesByArchetype(newSprite);
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
	private void organizeSpritesByArchetype(Sprite s){
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
}
