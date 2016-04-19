package player.leveldatamanager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import authoring.interfaces.Elementable;
import authoring.model.VoogaFrontEndText;
import data.DataContainerOfLists;
import data.FileReaderToGameObjects;
import events.Cause;
import events.Effect;
import events.KeyCause;
import events.VoogaEvent;
import gameengine.Sprite;
import gameengine.SpriteFactory;
import javafx.scene.Node;
import physics.StandardPhysics;
import tools.VoogaNumber;
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
	
	private StandardPhysics myPhysics = new StandardPhysics();
	
	/**Sprite and Text Information**/
	private String myMainCharacterID;
	private Map<String,Elementable> myElements;
	private SpriteFactory mySpriteFactory;
	
	/**Global Variable Information**/
	private Map<String, VoogaData> myGlobalVariables;
	
	/** Event Information**/
	private List<VoogaEvent> myEvents;
	private List<List<String>> myKeyCombos;
	private Map<List<String>, KeyCause> myKeyCauses; //Maps Strings 
	
	//TODO: REFACTOR EXACTLY WHAT GETTER AND SETTER METHODS WE WANT IN HERE
	
	private IDisplayScroller myScroller;
	
	public LevelData() {
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
	 * Returns Main Character Sprite
	 * @param id
	 * @return Sprite
	 */
	public Sprite getMainCharacter(){
		return (Sprite) myElements.get(myMainCharacterID);
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
	public Sprite addSprite(String archetype){
		Elementable newSprite = mySpriteFactory.createSprite(archetype);
		myElements.put(newSprite.getID(),newSprite);
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

		return myScroller.centerScroll(displayablenodes, getMainCharacter().getPosition().getX());

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
	public void refreshLevelData(String levelfilename){
		DataContainerOfLists data = new DataContainerOfLists();
		FileReaderToGameObjects fileManager = new FileReaderToGameObjects(levelfilename);
		data = fileManager.getDataContainer();
      
		List<Elementable> elementObjects = data.getElementableList();
		System.out.println("All the sprites here are" + elementObjects);
		
		//add elements to map 
		for(Elementable el : elementObjects){
			myElements.put(el.getID(), el);
		}
		
		//TODO: HARDCODED IN, CHECK BACK LATER. SETTING MAIN CHARACTER TO BE FIRST SPRITE IN LIST
		for(Elementable el : elementObjects){
			if(el instanceof Sprite){
				myMainCharacterID = el.getID();
				break;
			}
		}
		
		
		List<VoogaEvent> eventObjects = data.getEventList();
		System.out.println("All the events here are" + eventObjects);

		for(VoogaEvent e : eventObjects){
			addEventAndPopulateKeyCombos(e);
		}
		
		mySpriteFactory = data.getSpriteFactory();
		System.out.println("The spriteFactory here is" + mySpriteFactory);

		myGlobalVariables = data.getVariableMap();
		System.out.println("All the variables here are" + myGlobalVariables);
	}
	
	public int getLevelNumber() {
		//HARDCODED FOR NOW!!!!
		return -5;
		//return Integer.parseInt((((VoogaNumber) myGlobalVariables.get("LevelIndex")).getValue().toString()));
	}
	
	public void setLevelNumber(int levelNumber) {
		
		myGlobalVariables.put("LevelIndex", new VoogaNumber((double) levelNumber));
//		return (int) ((((VoogaNumber) myGlobalVariables.get("LevelIndex")).getValue()));
	}


	@Override
	public StandardPhysics getPhysicsEngine() {
		return myPhysics;
	}
}