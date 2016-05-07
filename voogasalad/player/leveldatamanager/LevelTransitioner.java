/**
 * Code Masterpiece - Hunter Lee (hl130)
 * 
 * To demonstrate my knowledge of Object-oriented programming, I chose a design pattern
 * that is relevant to this project. I had this in mind for a while--I wrote this LevelTransitioner
 * class, and when I did so, I felt that the constructor was taking too many arguments. So I learned
 * of an OOP design pattern called the Builder pattern, and I demonstrate my knowledge of the Builder
 * pattern here.
 * 
 * Its advantages are 
 * 1) The Builder pattern allows you to vary a product’s internal representation.
 * 2) Encapsulates code for construction and representation.
 * 3) Provides control over steps of construction process.
 * 
 * This changes the way LevelTransitioner is created on the client side (LevelData line 158), which I
 * think is more intuitive for the client. It is a good design because it gives me the control of the 
 * constructor and encapsulates the details of the LevelTransitioner class by using a Builder object.
 * 
 * Attribution: Effective Java, Joshua Block
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
    private final Map<String, Elementable> myElements;
    private final KeyEventContainer myKeyEventContainer;
    private final ResourceBundle myEventMethods;
    private DataContainerOfLists myData;
    private FileReaderToGameObjects myFileManager;
    private Map<String, VoogaData> myGlobalVariables;
    private final String myLevelFileName;
    private final String myNextLevelKey;
    private final String myMainCharKey;

    /**
     * Default constructor that stores all game data that needed to be renewed to transition
     * into a new level.
     * 
     * **Code Masterpiece** - changed to private to make the instance immutable
     * 
     * @param levelfilename
     * @param elements
     * @param container
     * @param globals
     * @param nextlevelkey
     */
    private LevelTransitioner(String levelfilename, Map<String, Elementable> elements, KeyEventContainer container, 
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
    
    /**
     * Builder class as outlined in the Second Edition of Joshua Bloch's
     * Effective Java. This Builder class is used to generate an instance of LevelTransitioner
     * 
     * @author Hunter Lee
     * 
     */
    public static class TransitionerBuilder {
    	private String nestedFilename;
    	private String nestedLevelkey;
    	private Map<String, Elementable> nestedElements;
    	private KeyEventContainer nestedEventContainer;
    	private Map<String, VoogaData> nestedGlobals;
   
    	public TransitionerBuilder filename(String filename) {
    		this.nestedFilename = filename;
    		return this;
    	}
    	
    	public TransitionerBuilder levelkey(String levelkey) {
    		this.nestedLevelkey = levelkey;
    		return this;
    	}
    
    	public TransitionerBuilder elements(Map<String, Elementable> elements) {
    		this.nestedElements = elements;
    		return this;
    	}
    	
    	public TransitionerBuilder eventcontainer(KeyEventContainer container) {
    		this.nestedEventContainer = container;
    		return this;
    	}
    	
    	public TransitionerBuilder globals(Map<String, VoogaData> globals) {
    		this.nestedGlobals = globals;
    		return this;
    	}
    	
    	public LevelTransitioner build() {
    		return new LevelTransitioner(this);
    	}
    }
    
    private LevelTransitioner(TransitionerBuilder builder) {
    	this.myData = new DataContainerOfLists();
    	this.myEventMethods = VoogaBundles.EventMethods;
    	this.myMainCharKey = VoogaBundles.defaultglobalvars.getProperty("MainCharacter");
    	this.myElements = builder.nestedElements;
    	this.myKeyEventContainer = builder.nestedEventContainer;
    	this.myGlobalVariables = builder.nestedGlobals;
        this.myLevelFileName = builder.nestedFilename;
        this.myNextLevelKey = builder.nestedLevelkey;
    }
}