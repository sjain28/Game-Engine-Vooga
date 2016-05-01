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
import events.AnimationFactory;
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
    private String myNextLevelKey;
    private String myMainCharKey;
    
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
        myMainCharKey = VoogaBundles.defaultglobalvars.getProperty("MainCharacter");
    }
    
    /**
     * Populate myElements with new sprites pertinent to a new level
     * @param levelfilename
     */
    public Map<String, Elementable> populateNewSprites () {
        myFileManager = new FileReaderToGameObjects(myLevelFileName);
        myData = myFileManager.getDataContainer();
        List<Elementable> elementObjects = myData.getElementableList();
        myElements.clear();
        for (Elementable elementable : elementObjects) {
            try {elementable.init();
            } catch (VoogaException e1) {
            	//TODO: remove printstacktrace
            	e1.printStackTrace();
            }
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
	 * Returns ID of the sprite the display is being scrolled on
	 * @return String centerScroll sprite ID
	 */
	public String getMainCharID() {
		Path path = Paths.get(this.myLevelFileName);
		String rawLevelName = path.getFileName().toString().replace(".xml", "");
		return (String) myGlobalVariables.get(rawLevelName + myMainCharKey).getValue();
	}
	
	/**
	 * Returns a newly-populated SpriteFactory
	 * @return SpriteFactory
	 */
	public SpriteFactory getNewSpriteFactory() {
		Map<String, Sprite> archetypeMap = myData.getArchetypeMap();
		return new SpriteFactory(archetypeMap);
	}
	
    public AnimationFactory getAnimationFactory(){
    	return myData.getAnimationFactory();
    }
}
