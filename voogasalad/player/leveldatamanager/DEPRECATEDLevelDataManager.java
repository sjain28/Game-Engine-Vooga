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
import tools.interfaces.VoogaData;

@Deprecated
public class DEPRECATEDLevelDataManager implements ILevelDataManager {

	private static final int SCREENSIZE_DIM1 = 3;
	private static final int SCREENSIZE_DIM2 = 35;
<<<<<<< HEAD
	private int nextLevel;
	private IGameRunner myGameRunner;
=======

>>>>>>> josh_hunter_continuity
	private DisplayScroller displayScroller;
	private OUTDATEDObjectManager myObjectManager;
	private OUTDATEDEventManager myEventManager;
	private GlobalVariableManager myGlobalVariableManager;
	private List<KeyEvent> myKeyEvents;
	
	
	public DEPRECATEDLevelDataManager() {
		nextLevel = -1;
		displayScroller = new DisplayScroller(SCREENSIZE_DIM1, SCREENSIZE_DIM2);
		myKeyEvents = new ArrayList<>();
		// Got rid of these methods because we want to deprecate this constructor;
//		readinObjects(levelFileName);
//		bindImagesofSprites();
	}
	
	/**
	 * Default constructor
	 * 
	 * @param levelFileName
	 */
	@Deprecated
	public DEPRECATEDLevelDataManager(String levelFileName) {
		nextLevel = -1;
		displayScroller = new DisplayScroller(SCREENSIZE_DIM1, SCREENSIZE_DIM2);
		myKeyEvents = new ArrayList<>();
		// Got rid of these methods because we want to deprecate this constructor;
//		readinObjects(levelFileName);
//		bindImagesofSprites();
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

<<<<<<< HEAD
	/**
	 * Constructor that takes in a reference to GameRunner LevelDataManager
	 * belongs to (composition)
	 * 
	 */
	@Deprecated
	public DEPRECATEDLevelDataManager(IGameRunner gamerunner, String levelFileName) {
		this(levelFileName);
		this.myGameRunner = gamerunner;
	}

	public DEPRECATEDLevelDataManager(IGameRunner gamerunner) {
		this();
		this.myGameRunner = gamerunner;
	}
	
	public void initialize(String levelFileName) {
		readinObjects(levelFileName);
		bindImagesofSprites();
	}

	
=======
>>>>>>> josh_hunter_continuity

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
//		System.out.println("All the sprites here are" + spriteObjects);

		List<VoogaEvent> eventObjects = data.getEventList();
//		System.out.println("All the events here are" + eventObjects);

		SpriteFactory factory = data.getSpriteFactory();
//		System.out.println("The spriteFactory here is" + factory);

		Map<String,VoogaData> variableObjects = data.getVariableMap();
//		System.out.println("All the variables here are" + variableObjects);

		initializeManagers(spriteObjects, eventObjects, variableObjects,factory);


	}


	/**
	 * A stub method called at every iteration to receive KeyEvents from
	 * GameDisplay and updates Objects (Sprites) and applies Events
	 * (cause and effects)
	 * 
	 */
	@Override
	public void update() {
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
		myObjectManager = new OUTDATEDObjectManager(elementObjects, variableObjects, factory);
		myObjectManager.setKeyEvents(myKeyEvents);
		myEventManager = new OUTDATEDEventManager(myObjectManager, eventObjects);
		myGlobalVariableManager = new GlobalVariableManager(myObjectManager, variableObjects);
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

	/**
	 * @return the myGlobalVariableManager
	 */
	public GlobalVariableManager getGlobalVariableManager() {
		return myGlobalVariableManager;
	}

//	@Override
//	public void resetKeyEvents(List<KeyEvent> keyEvents) {
//		myEventManager.
//	}

	/**
	 * @return the current level being played.
	 */
//	public int getCurrentLevel() {
//		return nextLevel;
//	}
	
	/**
	 * @return the next level to be played.
	 */
	public int getNextLevel() {
		return nextLevel;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	
}