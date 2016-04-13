package player.leveldatamanager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import authoring.interfaces.Elementable;
import data.DataContainerOfLists;
import data.FileReaderToGameObjects;
import events.VoogaEvent;
import gameengine.SpriteFactory;
import javafx.scene.Node;
import javafx.scene.input.KeyEvent;
import player.gamerunner.IGameRunner;
import tools.interfaces.VoogaData;


public class LevelDataManager implements ILevelDataManager {

	private IGameRunner myGameRunner;
	private DisplayScroller displayScroller;
	private ObjectManager myObjectManager;
	private EventManager myEventManager;
	private int screenSizeDim_1 = 3;
	private int screenSizeDim_2 = 35;
	private List<KeyEvent> myKeyEvents;

	/**
	 * Default constructor
	 * 
	 * @param levelFileName
	 */
	public LevelDataManager(String levelFileName) {
		displayScroller = new DisplayScroller(screenSizeDim_1, screenSizeDim_2);
		myKeyEvents = new ArrayList<>();
		readinObjects(levelFileName);
		bindImagesofSprites();
	}

	/**
	 * Binds images to sprites at initialization
	 * Necessary because Images are passed as transients
	 * 
	 * TODO: Refactor this
	 * 
	 */
	private void bindImagesofSprites() {
		myObjectManager.getAllDisplayableNodes();
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
	 * Read in the file to reconstruct objects created in the authoring
	 * environment
	 * 
	 * @param levelFileName
	 */
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
=======
	}

	/**
	 * A stub method called at every iteration to receive KeyEvents from
	 * GameDisplay and updates Objects (Sprites) and applies Events
	 * (cause and effects)
	 * 
	 */
	@Override
	public void update() {
		//setKeyEvents(getGameRunner().getKeyEvents());
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
		return displayScroller.centerScroll(myObjectManager.getAllDisplayableNodes(),myObjectManager.getMainCharXPos());
	}

	/**
>>>>>>> master
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
//	public List<?> getKeyEvents() {
//		return myKeyEvents;
//	}

	/**
	 * @param myKeyEvents the myKeyEvents to set
	 */
	public void setKeyEvents(List<?> myKeyEvents) {
		myEventManager.setKeyStrokes(myKeyEvents);
	}

//	@Override
//	public void resetKeyEvents(List<KeyEvent> keyEvents) {
//		myEventManager.
//	}

}
