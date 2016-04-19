package player.leveldatamanager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import authoring.interfaces.Elementable;
import authoring.model.VoogaFrontEndText;
import gameengine.Sprite;
import gameengine.SpriteFactory;
import javafx.scene.Node;
import javafx.scene.input.KeyEvent;
import physics.IPhysicsEngine;
import physics.StandardPhysics;
import tools.interfaces.VoogaData;

/**
 * ObjectManager belongs to LevelDataManager as a component
 * Manages sprites, texts, and global variables
 *
 */
public class ObjectManager {

	// Maps IDs to Sprites (Elementables)
	private Map<String,Elementable> myElements;
	private SpriteFactory mySpriteFactory;
	private Map<String, VoogaData> myGlobalVariables;
	private List<KeyEvent> keyEvents;
	private IPhysicsEngine myPhysics = new StandardPhysics();

	/**
	 * Takes in a map of Id's to sprite's and a sprite factory
	 * organizes the sprite's in terms of archetype as well
	 * @param sprites
	 * @param factory
	 */
	public ObjectManager(List<Elementable> elements, Map<String,VoogaData> data, SpriteFactory factory) {
		myElements = new HashMap<String,Elementable>();
		for(Elementable el : elements){
			myElements.put(el.getID(), el);
		}

		myGlobalVariables = new HashMap<String, VoogaData>(data);
		keyEvents = new ArrayList<KeyEvent>();
		//TODO: Once constructor is figured out, intialize all objects here.
		mySpriteFactory = factory;
	}

	/**
	 * This method updates each sprite's Position
	 * before Events (causes and effects) are applied
	 * 
	 */
	public void update() {
		for(String s: myElements.keySet()){
			Elementable e = myElements.get(s);
			applyGravity(e);
			e.update();
		}
	}
	
	/**
	 * Using gravity field of each sprite, updates sprites' velocity
	 * 
	 */
	private void applyGravity(Elementable e) {
		if (e instanceof Sprite) {
		    double gravityMagnitude = (double) ((Sprite) e).getProperty("gravity").getValue();
		    
			getPhysics().gravity((Sprite) e, gravityMagnitude);
		}
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
	public VoogaFrontEndText getText(Object id){
		return (VoogaFrontEndText) myElements.get(id);
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

	/**
	 * Returns X coordinate of the main character
	 * 
	 * @return
	 */
	public double getMainCharXPos() {
		//HARD CODED TO JUST RETURN THE XPOS OF THE FIRST SPRITE
		//TODO: CHANGE THIS SOON
		for(String el : myElements.keySet()){
			return getSprite(el).getPosition().getX();
		}
		return 1.0;
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
	/**
	 * Sets KeyEvents
	 * 
	 * @param myKeyEvents
	 */
	public void setKeyEvents(List<KeyEvent> myKeyEvents){
		keyEvents = myKeyEvents;
	}

	/**
	 * Returns KeyEvents
	 * 
	 * @return
	 */
	public List<KeyEvent> getKeyEvents(){
		return keyEvents;
	}

	/**
	 * @return the myPhysics
	 */
	public IPhysicsEngine getPhysics() {
		return myPhysics;
	}
}
