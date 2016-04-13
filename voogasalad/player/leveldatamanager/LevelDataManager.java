package player.leveldatamanager;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import authoring.interfaces.Elementable;
import data.DataContainerOfLists;
import data.FileReaderToGameObjects;
import events.VoogaEvent;
import gameengine.SpriteFactory;
import javafx.scene.Node;
import player.gamerunner.IGameRunner;
import tools.interfaces.VoogaData;

/**
 * LevelDataManager that comprises GameRunner
 * Reads in data and reconstructs sprite objects and maintains sprites
 * Also converts sprites into displayable Nodes using DisplayScroller
 *
 */
public class LevelDataManager implements ILevelDataManager {
	
    private static final int SCREENSIZE_DIM1 = 3;
    private static final int SCREENSIZE_DIM2 = 35;
	
	private IGameRunner myGameRunner;
    private DisplayScroller myScroller;
    private ObjectManager myObjectManager;
    private EventManager myEventManager;
    private Collections myKeyEvents;

    /**
     * Default constructor
     * 
     * @param levelFileName
     */
    public LevelDataManager(String levelFileName) {
        myScroller = new DisplayScroller(SCREENSIZE_DIM1, SCREENSIZE_DIM2);
        readinObjects(levelFileName);
    }
    
    /**
     * Constructor that takes in a reference to GameRunner LevelDataManager
     * belongs to (composition)
     * 
     */
    public LevelDataManager(IGameRunner gamerunner, String levelFileName) {
    	this(levelFileName);
    	this.myGameRunner = gamerunner;
    }

    /**
     * A stub method called at every iteration to receive KeyEvents from
     * GameDisplay and updates Objects (Sprites) and applies Events
     * (cause and effects)
     * 
     */
    @SuppressWarnings("unchecked")
	@Override
    public void update() {
    	setKeyEvents(getGameRunner().getKeyEvents());
    	myObjectManager.update();
        myEventManager.update();
        //Need to clear list of keyevents
    }

    /**
     * Returns all displayable objects in Node(s)
     * 
     */
    @Override
    public List<Node> getDisplayableObjects () {
        return getScroller().centerScroll(myObjectManager.getAllDisplayableNodes(),35);
    }

    /**
     * Read in the file to reconstruct objects created in the authoring
     * environment
     * 
     * @param levelFileName
     */
    private void readinObjects (String levelFileName) {
        FileReaderToGameObjects fileManager = new FileReaderToGameObjects(levelFileName);
        DataContainerOfLists data = fileManager.getDataContainer();
        
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
     * Creates the Sprites, Events, and Variables which will be loaded into 
     * the managers, which include the sprite, event, and variable managers
     * 
     * @return A LevelManager with all the objects it needs to contain (sprites, events,
     *          variables).
     */

    private void initializeManagers (List<Elementable> elementObjects,
                                     List<VoogaEvent> eventObjects,
                                     Map<String,VoogaData> variableObjects,
                                     SpriteFactory factory) {
    	myObjectManager = new ObjectManager(elementObjects, variableObjects, factory);
    	myObjectManager.setKeyEvents(myKeyEvents);
    	myEventManager = new EventManager(myObjectManager, eventObjects);
    }

	/**
	 * @return the myGameRunner
	 */
	public IGameRunner getGameRunner() {
		return myGameRunner;
	}

	/**
	 * @return the myKeyEvents
	 */
	public Collections getKeyEvents() {
		return myKeyEvents;
	}

	/**
	 * @param myKeyEvents the myKeyEvents to set
	 */
	public void setKeyEvents(Collections myKeyEvents) {
		this.myKeyEvents = myKeyEvents;
	}

	/**
	 * @return the myScroller
	 */
	public DisplayScroller getScroller() {
		return myScroller;
	}

}
