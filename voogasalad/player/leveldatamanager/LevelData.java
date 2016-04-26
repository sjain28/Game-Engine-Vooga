package player.leveldatamanager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import java.util.Set;
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

    /** Sprite and Text Information **/
    private String myCenteredCharId;
    private Map<String, Elementable> myElements;
    private SpriteFactory mySpriteFactory;

    /** Global Variable Information **/
    private Map<String, VoogaData> myGlobalVariables;

    /** Event Information **/
    private List<VoogaEvent> myEvents;
    private List<List<String>> keyPressedCombos;
    private List<List<String>> keyReleasedCombos;
    private Map<List<String>, KeyCause> myKeyPressCauses;
    private Map<List<String>, KeyCause> myKeyReleaseCauses;

    /** Important Static Variables **/
    private String myTimerKey;
    private String myNextLevelKey;
    private String myCenteredCharKey;

    private IDisplayScroller myScroller;
    private ResourceBundle methods;

    public LevelData (IPhysicsEngine physicsengine) {
        methods = VoogaBundles.EventMethods;
        myPhysics = physicsengine;
        myScroller = new DisplayScroller(SCREENSIZE, SCREENSIZE);
        myElements = new HashMap<>();
        myGlobalVariables = new HashMap<>();
        myEvents = new ArrayList<>();
        keyPressedCombos = new ArrayList<>();
        keyReleasedCombos = new ArrayList<>();
        myKeyPressCauses = new HashMap<>();
        myKeyReleaseCauses = new HashMap<>();
        myNextLevelKey = VoogaBundles.defaultglobalvars.getProperty("NextLevelIndex");
        myCenteredCharKey = VoogaBundles.defaultglobalvars.getProperty("MainCharacter");
        myTimerKey = VoogaBundles.defaultglobalvars.getProperty("Time");
    }
    /**
     * Returns a sprite by id
     * 
     * @param id
     * @return Sprite
     */
    public Sprite getSpriteByID (String id) {
        return (Sprite) myElements.get(id);
    }
    /**
     * Remove sprite by id
     * 
     * @param id
     */
    public void removeSpriteByID(String id){
    	myElements.remove(id);
    }
    /**
     * returns all Elementable's
     * 
     * @return
     */
    public Set<Entry<String, Elementable>> getElementables () {
    	//System.out.println(myElements);
    	return myElements.entrySet();
    }
    /**
     * Returns a list of sprite IDs given an archetype
     * 
     * @param archetype
     * @return
     */
    public List<Sprite> getSpritesByArch (String archetype) {
        List<Sprite> list = new ArrayList<>();
        for (String id : myElements.keySet()) {
            if (myElements.get(id) instanceof Sprite) {
                if (((Sprite) myElements.get(id)).getArchetype().equals(archetype)) {
                    list.add((Sprite) myElements.get(id));
                }
            }
        }
        return list;
    }
    /**
     * Adds a sprite given an archetype
     * 
     * @param archetype
     * @return
     */
    public Sprite addSprite (String archetype) {
        Elementable newSprite = mySpriteFactory.createSprite(archetype);
        myElements.put(newSprite.getId(), newSprite);
        return (Sprite) newSprite;
    }
    /**
     * Removes sprite given an id
     * 
     * @param id
     */
    public void removeSprite (String id) {
        myElements.remove(id);
    }
    /**
     * Returns a Global Variable (VoogaData) as specified
     * by it's variable name
     * 
     * @param variable
     * @return
     */
    public VoogaData getGlobalVar (String variable) {
        return myGlobalVariables.get(variable);
    }
    /**
     * Returns a VoogaText by id
     * 
     * @param id
     * @return
     */
    public Sprite getCenteredSprite(){
    	return getSpriteByID(myCenteredCharId);
    }
    /**
     * returns text object
     * 
     * @param id
     * @return
     */
    public VoogaFrontEndText getText (Object id) {
        return (VoogaFrontEndText) myElements.get(id);
    }
    /**
     * put all objects into a generic list of display-able objects
     * to be accessed by the GameRunner after every update cycle.
     * 
     * @return
     */
    public List<Node> getDisplayableNodes () {
        List<Node> displayablenodes = new ArrayList<Node>();
        for (Object key : myElements.keySet()) {
            displayablenodes.add(myElements.get(key).getNodeObject());
        }
        return displayablenodes;
    }
    /**
     * Returns unmodifiable list of key combos
     * 
     * @return
     */
    public List<List<String>> getKeyPressCombos () {
        return Collections.unmodifiableList(keyPressedCombos);
    }
    /**
     * Returns unmodifiable map of key causes
     * 
     * @return
     */
    public Map<List<String>, KeyCause> getKeyPressCauses () {
        return Collections.unmodifiableMap(myKeyPressCauses);
    }
    
    public Map<List<String>, KeyCause> getKeyReleaseCauses () {
        return Collections.unmodifiableMap(myKeyReleaseCauses);
    }
    
    /**
     * Returns unmodifiable list of key events
     * 
     * @return
     */
    public List<VoogaEvent> getEvents () {
        return Collections.unmodifiableList(myEvents);
    }
    /**
     * add a given event and populate the pressed and released key
     * combo's while doing so
     * 
     * @param voogaEvent
     */
    public void addEventAndPopulateKeyCombos (VoogaEvent voogaEvent) {
        myEvents.add(voogaEvent);
        for (Cause c : voogaEvent.getCauses()) {
            if (c instanceof KeyCause) {
                KeyCause keyc = (KeyCause) c;
                if (((KeyCause) c).getMyPressed().equals(methods.getString("Press"))) {
                    keyPressedCombos.add(keyc.getKeys());
                    this.myKeyPressCauses.put(keyc.getKeys(), keyc);
                }
                else { 
                	keyReleasedCombos.add(keyc.getKeys());
                	this.myKeyReleaseCauses.put(keyc.getKeys(), keyc);
                }
            }
        }
        keyReleasedCombos.sort( (List<String> a, List<String> b) -> -(a.size() - b.size()));
        keyPressedCombos.sort( (List<String> a, List<String> b) -> -(a.size() - b.size()));
    }

    /**
     * refreshes LevelData with the data from a specified level
     * also restarts timer in global variable
     * and sets level path
     * 
     * @param levelfilename
     */
    public void refreshLevelData (String levelfilename) {
        DataContainerOfLists data = new DataContainerOfLists();
        FileReaderToGameObjects fileManager = new FileReaderToGameObjects(levelfilename);
        data = fileManager.getDataContainer();

        //refresh elements objects
        List<Elementable> elementObjects = data.getElementableList();

        // clear all the instance variables
        myElements.clear();
        myEvents.clear();
        myKeyPressCauses.clear();
        myKeyReleaseCauses.clear();
        keyPressedCombos.clear();

        //refresh event objects
        List<VoogaEvent> eventObjects = data.getEventList();
        for (VoogaEvent e : eventObjects) {
            addEventAndPopulateKeyCombos(e);
        }

        //refresh sprite factory
        Map<String, Sprite> archetypeMap = data.getArchetypeMap();
        mySpriteFactory = new SpriteFactory(archetypeMap);

        //refresh global variables
        myGlobalVariables = data.getVariableMap();
        myGlobalVariables.put(myNextLevelKey, new VoogaString(""));
        myCenteredCharId = (String) myGlobalVariables.get(myCenteredCharKey).getValue();
        
        // add elements to map
        for (Elementable el : elementObjects) {
            try {el.init();}
            catch (VoogaException e1) {e1.printStackTrace();}
            myElements.put(el.getId(), el);
        }
    }
    /**
     * Fetch the level name
     * 
     * @return
     */
    public String getNextLevelName () {
        return ((String) (((VoogaString) myGlobalVariables.get(myNextLevelKey)).getValue()));
    }
    /**
     * Set the next level name in order to transition levels
     * 
     * @param levelName
     */
    public void setNextLevelName (String levelName) {
        myGlobalVariables.put(myNextLevelKey, new VoogaString(levelName));
    }
    /**
     * Update the global timer double
     * 
     * @param time
     */
    public void updatedGlobalTimer (double time) {
        myGlobalVariables.get(myTimerKey).setValue(new Double(time));
    }
    /**
     * Save progress saves the currently existing data to a data container. Then, everything is
     * saved to the location
     * 
     * filePath, which is specified in the function, along with the players name"
     **/
    public void saveProgress (String filePath, String playerName) {
        List<Elementable> elementList = new ArrayList<Elementable>(myElements.values());
        DataContainerOfLists dataContainer =
                new DataContainerOfLists(elementList, myGlobalVariables, myEvents,
                                         mySpriteFactory.getArchetypeMap());
        String newFileName = playerName + XML_SUFFIX;
        String finalLocation = filePath + newFileName;
        try {
            FileWriterFromGameObjects.saveGameObjects(dataContainer, finalLocation);
        }
        catch (Exception e) {e.printStackTrace();}
        System.out.println("The file here saved at location " + finalLocation);
    }
    /**
     * Returns the game's physics engine
     * 
     * @return
     */
    @Override
    public IPhysicsEngine getPhysicsEngine () {
        return myPhysics;
    }
    /**
     * Returns all key release combos
     * 
     * @return
     */
    public List<List<String>> getKeyReleasedCombos () {
        return keyReleasedCombos;
    }
}
