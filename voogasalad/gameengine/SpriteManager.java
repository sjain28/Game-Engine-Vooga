package gameengine;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SpriteManager {

	private Map<String, List<Object>> mySpriteCategories;
	private Map<Object,Sprite> mySprites;
	private SpriteFactory mySpriteFactory;
	
	/**
	 * Takes in a map of Ids to sprites and a sprite factory
	 * organizes the sprites in terms of archetype as well
	 * @param sprites
	 * @param factory
	 */
	public SpriteManager(Map<Object,Sprite> sprites,  SpriteFactory factory) {
		mySprites = sprites;
		mySpriteFactory = factory;
		//place Sprites by archetype and id in their correct maps
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
	 * organizes all the sprites by archtype as well
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
}
