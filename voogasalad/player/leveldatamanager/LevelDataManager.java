package player.leveldatamanager;

import java.util.List;
import java.util.Map;
import authoring.interfaces.Elementable;
import data.FileReaderToGameObjects;
import events.VoogaEvent;
import javafx.scene.Node;
import tools.interfaces.VoogaData;


public class LevelDataManager {

    private DisplayScroller displayScroller;
    private EngineObjectManager myObjectManager;
    private EventManager myEventManager;
    private int screenSizeDim_1 = 3;
    private int screenSizeDim_2 = 35;

    public LevelDataManager (String levelFileName) {
        displayScroller = new DisplayScroller(screenSizeDim_1, screenSizeDim_2);
        readinObjects(levelFileName);
    }

    public void update () {
        myEventManager.update();
    }

    public List<Object> extractUpdatedObjects () {
        return myObjectManager.getAllDisplayableObjects();
    }

    public List<Node> getDisplayableObjects () {
        return displayScroller.centerScroll(myObjectManager.getAllDisplayableObjects(), 35);
    }

    private void readinObjects (String levelFileName) {
        FileReaderToGameObjects fileManager = new FileReaderToGameObjects(levelFileName);
        List<Elementable> spriteObjects = fileManager.createNodeList();
        System.out.println(spriteObjects);
        List<VoogaEvent> eventObjects = fileManager.createEventList();
        System.out.println(eventObjects);
        Map<String,VoogaData> variableObjects = fileManager.createVariableMap();
        initializeManagers(spriteObjects, eventObjects, variableObjects);
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

    private void initializeManagers (List<Elementable> spriteObjects,
                                     List<VoogaEvent> eventObjects,
                                     Map<String,VoogaData> variableObjects) {

    }

}
