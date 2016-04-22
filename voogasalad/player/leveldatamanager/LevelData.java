package player.leveldatamanager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import authoring.interfaces.Elementable;
import authoring.model.VoogaFrontEndText;
import data.DataContainerOfLists;
import data.FileReaderToGameObjects;
import events.Cause;
import events.KeyCause;
import events.VoogaEvent;
import gameengine.Sprite;
import gameengine.SpriteFactory;
import javafx.scene.Node;
import physics.IPhysicsEngine;
import tools.VoogaException;
import tools.VoogaString;
import tools.interfaces.VoogaData;


/**
 * A centralized class to contain and access data relevant to a level
 * This includes Sprite's, Text, Global Variables, and Events
 * 
 * 
 * @author Krista
 *
 */ 
public class LevelData implements ILevelData {

	private static final int SCREENSIZE = 600;

	private IPhysicsEngine myPhysics;

	/**Sprite and Text Information**/
	private String myMainCharID;
	private List<String> myContinuousSpriteIDs;
	private Map<String,Elementable> myElements;
	private SpriteFactory mySpriteFactory;
	
	/**Global Variable Information**/
	private Map<String, VoogaData> myGlobalVariables;
	
	/** Event Information**/
	private List<VoogaEvent> myEvents;
	private List<List<String>> myKeyCombos;
	private Map<List<String>, KeyCause> myKeyCauses;
	
	/**Important Static Variables**/
	private static final String LEVEL_INDEX = "LevelIndex";
	private static final String CONTINIOUS_CHAR = "MainCharacterID";
	
	private IDisplayScroller myScroller;

	public LevelData(IPhysicsEngine physicsengine) {
		myPhysics = physicsengine;
		myContinuousSpriteIDs = new ArrayList<String>();
		myScroller = new DisplayScroller(SCREENSIZE, SCREENSIZE);
		myElements = new HashMap<String, Elementable>();		
		myGlobalVariables = new HashMap<String, VoogaData>();
		myEvents = new ArrayList<VoogaEvent>();
		myKeyCombos = new ArrayList<List<String>>();
		myKeyCauses = new HashMap<List<String>, KeyCause>();	
	}


	/**
	 * Returns a sprite by id
	 * @param id
	 * @return Sprite
	 */
	public Sprite getSpriteByID(String id){
		return (Sprite) myElements.get(id);
	}
	/**
	 * returns all Sprite's 
	 * 
	 * @return
	 */
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
	public List<Sprite> getSpritesByArch(String archetype){
		List<Sprite> list = new ArrayList<>();
		for(String id : myElements.keySet()){
			if(myElements.get(id) instanceof Sprite){
				if(((Sprite) myElements.get(id)).getArchetype().equals(archetype)){
					list.add((Sprite)myElements.get(id));
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
	@Override
	public Sprite addSprite(String archetype){
		Elementable newSprite = mySpriteFactory.createSprite(archetype);
		myElements.put(newSprite.getId(),newSprite);
		return (Sprite) newSprite;
	}
	/**
	 * Removes sprite given an id
	 * 
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
		// IF THE MAIN CHARACTER HASN'T BEEN SET TODO: IF THIS IS HARDCODED, CHANGE
		if (myContinuousSpriteIDs.size()==0){
			return myScroller.centerScroll(displayablenodes, 5);
		}
		//centers on first main character in list TODO: If passed something different, change this
		Sprite centeredCharacter = getSpriteByID(myContinuousSpriteIDs.get(0));
		return myScroller.centerScroll(displayablenodes,centeredCharacter.getPosition().getX());
	}
	/**
	 * Returns unmodifiable list of key combos
	 * 
	 * @return
	 */
	public List<List<String>> getKeyCombos(){
		return Collections.unmodifiableList(myKeyCombos);
	}
	/**
	 * Returns unmodifiable map of key causes
	 * 
	 * @return
	 */
	public Map<List<String>, KeyCause> getKeyCauses(){
		return Collections.unmodifiableMap(myKeyCauses);
	}
	/**
	 * Returns unmodifiable list of key events
	 * 
	 * @return
	 */
	public List<VoogaEvent> getEvents(){
		return Collections.unmodifiableList(myEvents);
	}
	/**
	 * 
	 * @param voogaEvent
	 */
	public void addEventAndPopulateKeyCombos(VoogaEvent voogaEvent){

		myEvents.add(voogaEvent);
		for(Cause c: voogaEvent.getCauses()){
			if(c instanceof KeyCause){
				KeyCause keyc = (KeyCause) c;
				myKeyCauses.put(keyc.getKeys(), keyc); 
				myKeyCombos.add(keyc.getKeys()); 
				myKeyCombos.sort((List<String> a, List<String> b) -> -a.size() - b.size());
			}
		}
	}
	/**
	 * Populates the LevelData with the Data from a level specified by filename
	 * TODO: Handle continuity here
	 * TODO: Make sure to bind all Sprite images here when they are sent over
	 * 
	 * @param filename
	 */
	//TODO: REFACTOR THIS TO MAKE IT SHORTER
	public void refreshLevelData(String levelfilename){
		DataContainerOfLists data = new DataContainerOfLists();
		FileReaderToGameObjects fileManager = new FileReaderToGameObjects(levelfilename);
		data = fileManager.getDataContainer();
      
		List<Elementable> elementObjects = data.getElementableList();
		System.out.println("All the sprites here are" + elementObjects);

		processContinuousSpritesAndPopulateElementables(elementObjects);		
		
		List<VoogaEvent> eventObjects = data.getEventList();
		System.out.println("All the events here are" + eventObjects);

		for(VoogaEvent e : eventObjects){
			addEventAndPopulateKeyCombos(e);
		}
		
		Map<String,Sprite> archetypeMap = data.getArchetypeMap();
		System.out.println("All the events here are" + eventObjects);
		
		mySpriteFactory = new SpriteFactory(archetypeMap);

		System.out.println("The spriteFactory here is" + mySpriteFactory);

		myGlobalVariables = data.getVariableMap();
		System.out.println("All the variables here are" + myGlobalVariables);
		System.out.println("global variables contians main char id "+myGlobalVariables.containsKey(CONTINIOUS_CHAR));
		
		//TODO: HARD CODED RN, SETTING THE MAIN CHAR TO BE THE FIRST CONTINUOUS SPRITE
		myMainCharID = myContinuousSpriteIDs.get(0);
		
		System.out.println("putting");
		myGlobalVariables.put("LevelIndex", new VoogaString(""));
		
	}

	//TODO: ALSO REFACTOR THIS SO IT IS SHORTER
	private void processContinuousSpritesAndPopulateElementables(List<Elementable> elementObjects) {
		//must save past maincharacter info
		//if main character list is not equal to zero
		//save the main character sprites for later 
		Map<String,Sprite> previousContinuousSprites = new HashMap<String,Sprite>();
		if(!myContinuousSpriteIDs.isEmpty()){
			for(String id : myContinuousSpriteIDs){
				previousContinuousSprites.put(getSpriteByID(id).getName(),getSpriteByID(id));
			}
		}
		
		//clear whats in the myElements Map.
		myElements.clear();
		
		
		//add elements to map 
		for(Elementable el : elementObjects){
			myElements.put(el.getId(), el);
			//if an element is a sprite and a main character, add its id to the main char list
			if(el instanceof Sprite){
				try {
					((Sprite) el).init();
				} catch (VoogaException e) {
					e.printStackTrace();
				}
				if(((Sprite) el).isContinuous()){
					//add in the new continuous sprite ids
					myContinuousSpriteIDs.add(el.getId());
					if(previousContinuousSprites.containsKey(el.getName())){
						//if they are of the same name, set the new sprite to have all the same variables
						//as the old sprite
						//Note: this does not include 
						((Sprite) el).setProperties(previousContinuousSprites.get(el.getName()).getParameterMap());
					}
				}
			}
		}
	}


	public String getNextLevelName() {
		//HARDCODED FOR NOW!!!!
		return ((String) (((VoogaString) myGlobalVariables.get(LEVEL_INDEX)).getValue()));
	}
	public void setNextLevelName(String levelName) {
		myGlobalVariables.put(LEVEL_INDEX, new VoogaString(levelName));
	}

	@Override
	public IPhysicsEngine getPhysicsEngine() {
		return myPhysics;
	}
}
