/**
 * 
 */
package player.leveldatamanager;

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
    private String myTimerKey;
    private String myNextLevelKey;
    private String myCenteredCharKey;
    
    
    public LevelTransitioner(Map<String, Elementable> elements, KeyEventContainer container, 
    						 Map<String, VoogaData> globals, String nextlevelkey) {
    	myData = new DataContainerOfLists();
    	myElements = elements;
    	myKeyEventContainer = container;
    	myGlobalVariables = globals;
        myEventMethods = VoogaBundles.EventMethods;
        myNextLevelKey = nextlevelkey;
        myCenteredCharKey = VoogaBundles.defaultglobalvars.getProperty("MainCharacter");
        myTimerKey = VoogaBundles.defaultglobalvars.getProperty("Time");
    }
	
    /**
     * refreshes LevelData with the data from a specified level
     * also restarts timer in global variable
     * and sets level path
     * 
     * TODO: Break this up. and add the scrolling sprite
     * 
     * @param levelfilename
     */
    public void repopulateData (String levelfilename) {
        //DataContainerOfLists data = new DataContainerOfLists();
        myFileManager = new FileReaderToGameObjects(levelfilename);
        myData = myFileManager.getDataContainer();

        //refresh elements objects
        List<Elementable> elementObjects = myData.getElementableList();

        // clear all the instance variables
        myElements.clear();
        myKeyEventContainer.clearAll();

        populateNewEvents();
        populateNewGlobals();
                
        for (Elementable elementable : elementObjects) {
            try {elementable.init();}
            catch (VoogaException e1) {e1.printStackTrace();}
            myElements.put(elementable.getId(), elementable);
        }
    }

    private void populateNewEvents() {
        List<VoogaEvent> eventObjects = myData.getEventList();
        for (VoogaEvent event : eventObjects) {
            myKeyEventContainer.addEventAndPopulateKeyCombos(event, myEventMethods);
        }
    }
    
    private void populateNewGlobals() {
        myGlobalVariables = myData.getVariableMap();
        myGlobalVariables.put(myNextLevelKey, new VoogaString(""));
        myGlobalVariables.put(SAVE_PROGRESS, new VoogaBoolean(false));
    }

    public SpriteFactory getNewSpriteFactory() {
        Map<String, Sprite> archetypeMap = myData.getArchetypeMap();
        return new SpriteFactory(archetypeMap);
    }
    
    public String getCenteredCharID() {
        return (String) myGlobalVariables.get(myCenteredCharKey).getValue();
    }

}
