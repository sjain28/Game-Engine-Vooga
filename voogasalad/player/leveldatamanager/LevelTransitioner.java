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

    private Map<String, Elementable> myElements;
    private KeyEventContainer myKeyEventContainer;
    private SpriteFactory mySpriteFactory;
    private Map<String, VoogaData> myGlobalVariables;


    private ResourceBundle myEventMethods;
    private String myTimerKey;
    private String myNextLevelKey;
    private String myCenteredCharKey;
    
    
    public LevelTransitioner(Map<String, Elementable> elements, KeyEventContainer container,
    						 SpriteFactory spritefactory, Map<String, VoogaData> globals) {
    	
    	myElements = elements;
    	myKeyEventContainer = container;
    	mySpriteFactory = spritefactory;
    	myGlobalVariables = globals;
        myEventMethods = VoogaBundles.EventMethods;
        myNextLevelKey = VoogaBundles.defaultglobalvars.getProperty("NextLevelIndex");
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
    public void refreshLevelData (String levelfilename, Map<String, Elementable> elements,
    		KeyEventContainer eventcontainer) {
        DataContainerOfLists data = new DataContainerOfLists();
        FileReaderToGameObjects fileManager = new FileReaderToGameObjects(levelfilename);
        data = fileManager.getDataContainer();

        //refresh elements objects
        List<Elementable> elementObjects = data.getElementableList();

        // clear all the instance variables
        elements.clear();
        eventcontainer.clearAll();

        //refresh event objects
        List<VoogaEvent> eventObjects = data.getEventList();
        for (VoogaEvent event : eventObjects) {
            eventcontainer.addEventAndPopulateKeyCombos(event, myEventMethods);
        }

        //refresh sprite factory
        Map<String, Sprite> archetypeMap = data.getArchetypeMap();
        mySpriteFactory = new SpriteFactory(archetypeMap);

        //refresh global variables
        myGlobalVariables = data.getVariableMap();
        myGlobalVariables.put(myNextLevelKey, new VoogaString(""));
        myGlobalVariables.put(SAVE_PROGRESS, new VoogaBoolean(false));
        
        // add elements to map
        for (Elementable elementable : elementObjects) {
            try {elementable.init();}
            catch (VoogaException e1) {e1.printStackTrace();}
            elements.put(elementable.getId(), elementable);
        }
    }
    
    public String getCenteredCharID() {
        return (String) myGlobalVariables.get(myCenteredCharKey).getValue();
    }
}
