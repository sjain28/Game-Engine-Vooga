package player.leveldatamanager;

import java.util.List;
import java.util.Map;
import authoring.interfaces.Elementable;
import data.DataContainerOfLists;
import data.FileReaderToGameObjects;
import events.VoogaEvent;
import gameengine.SpriteFactory;
import javafx.scene.Node;
import tools.interfaces.VoogaData;


public class LevelDataManager implements ILevelDataManager {

    private DisplayScroller displayScroller;
    private EngineObjectManager myObjectManager;
    private EventManager myEventManager;
    private int screenSizeDim_1 = 3;
    private int screenSizeDim_2 = 35;

    public LevelDataManager (String levelFileName) {
        displayScroller = new DisplayScroller(screenSizeDim_1, screenSizeDim_2);
        readinObjects(levelFileName);
    }

    @Override
    public void update() {
    	myObjectManager.update();
        myEventManager.update();
    }

    @Override
    public List<Node> getDisplayableObjects () {
        return displayScroller.centerScroll(myObjectManager.getAllDisplayableNodes(),myObjectManager.getMainCharXPos());
    }
    

    private void readinObjects (String levelFileName) {
    	DataContainerOfLists data = new DataContainerOfLists();
    	try{
        FileReaderToGameObjects fileManager = new FileReaderToGameObjects(levelFileName);
        data = fileManager.getDataContainer();

    	}
    	catch(RuntimeException e){
    		System.out.println("Making the game objects did not work properly");
    		e.printStackTrace();
    	}        
        System.out.println("Here is my data object:" + data);
        
        List<Elementable> spriteObjects = data.getElementableList();
        System.out.println("All the sprites here are" + spriteObjects);
        
        List<VoogaEvent> eventObjects = data.getEventList();
        System.out.println("All the events here are" + eventObjects);
        
        SpriteFactory factory = data.getSpriteFactory();
        System.out.println("The spriteFactory here is" + factory);
        
        Map<String,VoogaData> variableObjects = data.getVariableMap();
        System.out.println("All the variables here are" + variableObjects);
        
        initializeManagers(spriteObjects, eventObjects, variableObjects,factory);
        
        
    }

    /**
     * Creates the Sprites, Events, and Variables which will be loaded into the managers, which
     * include
     * the sprite, event, and variable managers
     * 
     * @return- A LevelManager with all the objects it needs to contain (sprites, events,
     *          variables).
     * 
     */

    private void initializeManagers (List<Elementable> elementObjects,
                                     List<VoogaEvent> eventObjects,
                                     Map<String,VoogaData> variableObjects,
                                     SpriteFactory factory) {
    	myObjectManager = new EngineObjectManager(elementObjects, variableObjects, factory);
    	myEventManager = new EventManager(myObjectManager, eventObjects);
    }

}
