package player.leveldatamanager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.HashMap;
import authoring.interfaces.Elementable;
import authoring.model.VoogaFrontEndText;
import data.DataContainerOfLists;
import data.FileReaderToGameObjects;
import data.FileWriterFromGameObjects;
import events.Cause;
import events.KeyCause;
import events.VoogaEvent;
import gameengine.Sprite;
import gameengine.SpriteFactory;
import javafx.scene.Node;
import physics.IPhysicsEngine;
import resources.VoogaBundles;
import tools.VoogaException;
import tools.VoogaNumber;
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
	private static final String UNDERSCORE = "_";
	private static final String XML_SUFFIX = ".xml";
	
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
	private List<List<String>> keyPressedCombos;
	private List<List<String>> keyReleasedCombos;
	private Map<List<String>, KeyCause> myKeyCauses;
	
	/**Important Static Variables**/
	private static final String TIMER = "Time";
	private static final String NEXT_LEVEL_INDEX = "NextLevelIndex";
	private static final String CONTINIOUS_CHAR = "MainCharacterID";
	
	private IDisplayScroller myScroller;
	private ResourceBundle methods = VoogaBundles.EventMethods;
	
	public LevelData(IPhysicsEngine physicsengine) {
		myPhysics = physicsengine;
		myContinuousSpriteIDs = new ArrayList<String>();
		myScroller = new DisplayScroller(SCREENSIZE, SCREENSIZE);
		myElements = new HashMap<String, Elementable>();		
		myGlobalVariables = new HashMap<String, VoogaData>();
		myEvents = new ArrayList<VoogaEvent>();
		keyPressedCombos = new ArrayList<>();
		keyReleasedCombos = new ArrayList<>();
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
		System.out.println("Size of all sprites: "+ sprites.size());
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
	public List<List<String>> getKeyPressCombos(){
		return Collections.unmodifiableList(keyPressedCombos);
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
		for (Cause c: voogaEvent.getCauses()) {
			if (c instanceof KeyCause) {
				KeyCause keyc = (KeyCause) c;
				myKeyCauses.put(keyc.getKeys(), keyc); 
				if (((KeyCause) c).getMyPressed().equals(methods.getString("Press"))) {
					keyPressedCombos.add(keyc.getKeys()); 
				} else {
					keyReleasedCombos.add(keyc.getKeys());
				}
			}
		}
		
		keyReleasedCombos.sort((List<String> a, List<String> b) -> -(a.size() - b.size()));
		keyPressedCombos.sort((List<String> a, List<String> b) -> -(a.size() - b.size()));
	}
	/**
	 * refreshes LevelData with the data from a specified level
	 * also restarts timer in global variable
	 * and sets level path
	 * 
	 * @param levelfilename
	 */
	public void refreshLevelData(String levelfilename){		
		DataContainerOfLists data = new DataContainerOfLists();
		FileReaderToGameObjects fileManager = new FileReaderToGameObjects(levelfilename);
		data = fileManager.getDataContainer();
      
		List<Elementable> spriteObjects = data.getElementableList();
		System.out.println("All the sprites here are" + spriteObjects);


		List<Elementable> elementObjects = data.getElementableList();
		System.out.println("All the sprites here are" + elementObjects);

		//clear all the instance variables
		myElements.clear();
		myEvents.clear();
		myKeyCauses.clear();
		keyPressedCombos.clear();
		
		//add elements to map 
	    for (Elementable el : elementObjects) {
            if (el instanceof Sprite) {

                try {
                    ((Sprite) el).init();
                }
                catch (VoogaException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
            myElements.put(el.getId(), el);
        }
		//TODO: HARDCODED IN, CHECK BACK LATER. SETTING MAIN CHARACTER TO BE FIRST SPRITE IN LIST
		for(Elementable el : elementObjects){
			if(el instanceof Sprite){
				myMainCharID = el.getId();
				break;
			}
		}

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
		//initialize timer to zero here as well as level index
		myGlobalVariables.put(TIMER, new VoogaNumber(0.0));
		System.out.println("Did the timer here happen");
		myGlobalVariables.put(NEXT_LEVEL_INDEX, new VoogaString(""));
	}


	public String getNextLevelName() {
		//HARDCODED FOR NOW!!!!
		return ((String) (((VoogaString) myGlobalVariables.get(NEXT_LEVEL_INDEX)).getValue()));
	}
	public void setNextLevelName(String levelName) {
		myGlobalVariables.put(NEXT_LEVEL_INDEX, new VoogaString(levelName));
	}
	public void updatedGlobalTimer(double time){
		myGlobalVariables.get(TIMER).setValue(new Double(time));
	}	
	
	/*Save progress saves the currently existing data to a data container. Then, everything is saved to the location 
	
	filePath, which is specified in the function, along with the players name"
	*/
	
	public void saveProgress(String filePath,String playerName){
		
		List<Elementable> elementList = new ArrayList<Elementable>(myElements.values());
		DataContainerOfLists dataContainer = new DataContainerOfLists(elementList, myGlobalVariables, myEvents,
                mySpriteFactory.getArchetypeMap());
		String newFileName =  playerName + XML_SUFFIX;
		String finalLocation = filePath + newFileName;
		try{
		FileWriterFromGameObjects.saveGameObjects(dataContainer,finalLocation);
		}
		catch (Exception e){
			e.printStackTrace();
		}
		System.out.println("The file here saved at location " + finalLocation);
	}
	@Override
	public IPhysicsEngine getPhysicsEngine() {
		return myPhysics;
	}

	public List<List<String>> getKeyReleasedCombos() {
		return keyReleasedCombos;
	}
}