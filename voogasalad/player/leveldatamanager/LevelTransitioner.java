// This entire file is part of my masterpiece.
// Hunter Lee

/**
 * Code Masterpiece - Hunter Lee (hl130)
 * 
 * To demonstrate my knowledge of Object-oriented programming, I chose a design pattern
 * that is relevant to this project. We have studied and practiced the SOLID design principle.
 * Also, Joshua Block as a designer of Java gives us further insight into how to patch up Java
 * so that we could incorporate those principles in practice.
 * I had this in mind for a while--I wrote this LevelTransitioner
 * class, and when I did so, I felt that the constructor was taking too many arguments. So I learned
 * of an OOP design pattern called the Builder pattern, and I demonstrate my knowledge of the Builder
 * pattern here.
 * 
 * The original design opted for having a constructor with many parameters. Although the number of parameters
 * doesn't border on the extreme (15+) but I felt that this class had a decent amount of parameters. Such
 * brute force method is called the Telescoping Constructor Pattern. The problem with this pattern is that 
 * once constructors are 4 or 5 parameters long it becomes difficult to remember the required order of 
 * the parameters as well as what particular constructor you might want in a given situation. Another problem
 * is that The problem here is that because the object is created over several calls it may be in an 
 * inconsistent state part way through its construction. This also requires a lot of extra effort to ensure 
 * thread safety.
 * 
 * Its advantages are 
 * 1) The Builder pattern allows you to vary a productís internal representation.
 * 2) Encapsulates code for construction and representation.
 * 3) Provides control over steps of construction process.
 * 
 * This results in code that is easy to write and very easy to read and understand. And also if you suspect
 * there might be more additions of parameters in the future, this Builder pattern proves useful as it is 
 * designed to take in many parameters. Also I note that the constructor scope is changed to private, meaning
 * that we are now effectively creating immutable objects.
 * 
 * This changes the way LevelTransitioner is created on the client side (LevelData line 158), which I
 * think is more intuitive for the client. It is a good design because it gives me the control of the 
 * constructor and encapsulates the details of the LevelTransitioner class by using a Builder object.
 * 
 * The Builder pattern can be used for objects that contain flat data, that is to say, data that 
 * can't be easily edited. This type of data cannot be edited step by step and must be edited at once. 
 * The best way to construct such an object is to use a builder class. Because LevelTransitioner contains
 * and operates on such data, I strongly believe this was a suitable change to make.
 * 
 * Attribution: Effective Java, Joshua Block
 * 				StackOverflow
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