/**
 * 
 */
package player.leveldatamanager;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import authoring.interfaces.Elementable;
import data.DataContainerOfLists;
import data.FileReaderToGameObjects;
import events.VoogaEvent;
import gameengine.Sprite;
import gameengine.SpriteFactory;
import resources.VoogaBundles;
import tools.VoogaBoolean;
import tools.VoogaException;
import tools.VoogaString;
import tools.interfaces.VoogaData;

/**
 * LevelTransitioner class that processes level transition called by LevelData interface
 * 
 * @author Hunter Lee
 *
 */
public class LevelTransitioner {
	
    private static final String SAVE_PROGRESS = "SaveProgress";
    private DataContainerOfLists myData;
    private FileReaderToGameObjects myFileManager;
    private Map<String, Elementable> myElements;
    private KeyEventContainer myKeyEventContainer;
    private Map<String, VoogaData> myGlobalVariables;
    private ResourceBundle myEventMethods;
    private String myLevelFileName;
    //TODO: Remove if not needed
    private String myTimerKey;
    private String myNextLevelKey;
    private String myCenteredCharKey;
    
    /**
     * Default constructor that stores all game data that needed to be renewed to transition
     * into a new level.
     * @param levelfilename
     * @param elements
     * @param container
     * @param globals
     * @param nextlevelkey
     */
    public LevelTransitioner(String levelfilename, Map<String, Elementable> elements, KeyEventContainer container, 
    						 Map<String, VoogaData> globals, String nextlevelkey) {
    	myData = new DataContainerOfLists();
    	myElements = elements;
    	myKeyEventContainer = container;
    	myGlobalVariables = globals;
        myEventMethods = VoogaBundles.EventMethods;
        myLevelFileName = levelfilename;
        myNextLevelKey = nextlevelkey;
        myCenteredCharKey = VoogaBundles.defaultglobalvars.getProperty("MainCharacter");
        myTimerKey = VoogaBundles.defaultglobalvars.getProperty("Time");
    }
	
    /**
     * Populate myElements with new sprites pertinent to a new level
     * TODO: Create a scrolling sprite
     * @param levelfilename
     */
    public Map<String, Elementable> populateNewSprites () {
        //DataContainerOfLists data = new DataContainerOfLists();
        myFileManager = new FileReaderToGameObjects(myLevelFileName);
        myData = myFileManager.getDataContainer();
        //refresh elements objects
        List<Elementable> elementObjects = myData.getElementableList();
        // clear all the instance variables
        myElements.clear();
        for (Elementable elementable : elementObjects) {
            try {elementable.init();}
            catch (VoogaException e1) {e1.printStackTrace();}
            myElements.put(elementable.getId(), elementable);
        }
        return myElements;
    }

    /**
     * Returns a newly-populated myKeyEventContainer (Events, KeyEvents and Inputs)
     * @return KeyEventContainer
     */
    public KeyEventContainer populateNewEvents() {
        myKeyEventContainer.clearAll();
        List<VoogaEvent> eventObjects = myData.getEventList();
        for (VoogaEvent event : eventObjects) {
            myKeyEventContainer.addEventAndPopulateKeyCombos(event, myEventMethods);
        }
        return myKeyEventContainer;
    }
    
    /**
     * Returns a newly-populated myGlobalVariables
     * @return Map<String, VoogaData>
     */
    public Map<String, VoogaData> populateNewGlobals() {
        myGlobalVariables = myData.getVariableMap();
        myGlobalVariables.put(myNextLevelKey, new VoogaString(""));
        myGlobalVariables.put(SAVE_PROGRESS, new VoogaBoolean(false));
        return myGlobalVariables;
    }

    /**
     * Returns a newly-populated SpriteFactory
     * @return SpriteFactory
     */
    public SpriteFactory getNewSpriteFactory() {
        Map<String, Sprite> archetypeMap = myData.getArchetypeMap();
        return new SpriteFactory(archetypeMap);
    }
    
    /**
     * Returns ID of the sprite the display is being scrolled on
     * @return String centerScroll sprite ID
     */
    public String getCenteredCharID() {
        Path p = Paths.get(this.myLevelFileName);
        String rawLevelName = p.getFileName().toString().replace(".xml", "");
        return (String) myGlobalVariables.get(rawLevelName + myCenteredCharKey).getValue();
    }
    
    //TODO: Implement
    public String getScrollSpriteID() {
    	return "";
    }
    
    //TODO: Implement
    public String getMainSpriteID() {
    	return "";
    }

    //TODO: implement
    public Sprite createScrollSprite() {
    	return null;
    }
}
