package player.leveldatamanager;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.HashMap;
import authoring.interfaces.Elementable;
import authoring.model.VoogaFrontEndText;
import events.AnimationEvent;
import events.AnimationFactory;
import events.VoogaEvent;
import gameengine.Sprite;
import gameengine.SpriteFactory;
import javafx.scene.Node;
import physics.IPhysicsEngine;
import resources.VoogaBundles;
import tools.Pair;
import tools.PairZAxisComparator;
import tools.VoogaBoolean;
import tools.VoogaJukebox;
import tools.VoogaString;
import tools.interfaces.VoogaData;


/**
 * A centralized class to contain and access data including Sprites, Text,
 * Global Variables, and Events
 * 
 * @author Krista, Hunter
 */
public class LevelData implements ILevelData {
	private static final String SAVE_PROGRESS = VoogaBundles.defaultglobalvars.getProperty("SaveProgress");
	private static final String TIMER = VoogaBundles.defaultglobalvars.getProperty("Time");
	private static final String NEXT_LEVEL_KEY = VoogaBundles.defaultglobalvars.getProperty("NextLevelIndex");

	private IPhysicsEngine myPhysics;
	private String myMainCharID;
	private Map<String, Elementable> myElements;
	private SpriteFactory mySpriteFactory;
	private AnimationFactory myAnimationFactory;
	private Map<String, VoogaData> myGlobalVariables;
	private EventsContainer myEventsContainer;
	private LevelTransitioner myTransitioner;
	private ResourceBundle myEventMethods;

	/**
	 * Default constructor that takes in an instance of a physics module
	 */
	public LevelData(IPhysicsEngine physicsengine) {
		myEventMethods = VoogaBundles.EventMethods;
		myEventsContainer = new EventsContainer();
		myPhysics = physicsengine;
		myElements = new HashMap<>();
		myGlobalVariables = new HashMap<>();
	}
	/**
	 * Returns animation created by AnimationFactory
	 */
	public AnimationEvent getAnimationFromFactory(String animationString) {
		if (myAnimationFactory.getMyAnimationSequences().containsKey(animationString)) {
			List<AnimationEvent> clonedSequence = myAnimationFactory.cloneAnimationSequence(animationString);
			return clonedSequence.get(0);
		} else {
			return myAnimationFactory.cloneAnimationEvent(animationString);
		}
	}
	/**
	 * Adds a sprite as a member of the given archetype
	 */
	public Sprite addSprite(String archetype) {
		Elementable newSprite = mySpriteFactory.createSprite(archetype);
		myElements.put(newSprite.getId(), newSprite);
		return (Sprite) newSprite;
	}
	/**
	 * puts Sprite in Elementables list
	 */
	public void putSprite(Sprite s){
		myElements.put(s.getId(), s);
	}
	/**
	 * Returns a list of sprite IDs given an archetype
	 */
	public List<Sprite> getSpritesByArch(String archetype) {
		List<Sprite> list = new ArrayList<>();
		for (String id : myElements.keySet()) {
			if (myElements.get(id) instanceof Sprite && ((Sprite) myElements.get(id)).getArchetype().equals(archetype)) {
				list.add((Sprite) myElements.get(id));
			}
		}
		return list;
	}
	public Sprite getSpriteByID(String id) {
		return (Sprite) myElements.get(id);
	}

	public void removeSpriteByID(String id) {
		myElements.remove(id);
	}

	public Boolean containsSprite(String id) {
		return myElements.containsKey(id);
	}

	public Sprite getMainSprite() {
		return getSpriteByID(myMainCharID);
	}
	/**
	 * Returns a Global Variable (VoogaData) as specified by its variable name
	 */
	public VoogaData getGlobalVar(String variable) {
		return myGlobalVariables.get(variable);
	}

	/**
	 * Returns a text object by ID
	 */
	public VoogaFrontEndText getText(Object id) {
		return (VoogaFrontEndText) myElements.get(id);
	}

	/**
	 * Put all objects into a pair of displayable objects
	 */
	public List<Pair<Node, Boolean>> getDisplayableNodes() {
		List<Pair<Node, Boolean>> displayablenodes = new ArrayList<>();
		for (String key : myElements.keySet()) {
			Elementable elem = myElements.get(key);
			if(isDisplayable(elem)){
				String staticKey = VoogaBundles.spriteProperties.getString("STATIC");
				Boolean isStatic = (Boolean) elem.getVoogaProperties().get(staticKey).getProperty().getValue();
				displayablenodes.add(new Pair<Node, Boolean>(elem.getNodeObject(), isStatic));
			}
		}
		displayablenodes.sort(new PairZAxisComparator());
		return displayablenodes;
	}
	/**
	 * checks if an element should be displayable (if it's alive or not)
	 */
	private boolean isDisplayable(Elementable elem){
		if(elem instanceof Sprite){
			if(!(Boolean) ((Sprite) elem).getProperty(VoogaBundles.spriteProperties.getString("ALIVE")).getValue()){
				return false;
			}
		}
		return true;
	}
	/**
	 * Add a given event and populate the pressed and released KeyCombos
	 */
	public void addEventAndPopulateKeyCombos(VoogaEvent event) {
		myEventsContainer.addEventAndPopulateKeyCombos(event, myEventMethods);
	}

	/**
	 * Refreshes the data and restarts timer in global variable and sets level
	 * 
	 * @param levelfilename
	 */
	public void refreshLevelData(String levelfilename) {
		myTransitioner = new LevelTransitioner(levelfilename, myElements, myEventsContainer, myGlobalVariables, NEXT_LEVEL_KEY);
		myElements = myTransitioner.populateNewSprites();
		myEventsContainer = myTransitioner.populateNewEvents();
		myGlobalVariables = myTransitioner.populateNewGlobals();
		VoogaJukebox.getInstance().stopBGM();
		VoogaJukebox.getInstance().setBGM((String) myGlobalVariables.get(Paths.get(levelfilename).getFileName().toString().replace(".xml", "")+"BGM").getValue());
		VoogaJukebox.getInstance().playBGM();
		mySpriteFactory = myTransitioner.getNewSpriteFactory();
		myMainCharID = myTransitioner.getMainCharID();
		myAnimationFactory = myTransitioner.getAnimationFactory();
	}

	/**
	 * Update the global timer double
	 * 
	 * @param time
	 */
	public void updatedGlobalTimer(double time) {
		myGlobalVariables.get(TIMER).setValue(new Double(time));
	}
	/**
	 * Saves current game progress into a XML file
	 */
	public void saveProgress(String filePath) {
		myGlobalVariables.put(SAVE_PROGRESS, new VoogaBoolean(false));
		GameSaver saver = new GameSaver(myElements, myEventsContainer, myGlobalVariables, mySpriteFactory,
				myAnimationFactory);
		saver.saveCurrentProgress(filePath);
	}

	public String getNextLevelName() {
		return ((String) (((VoogaString) myGlobalVariables.get(NEXT_LEVEL_KEY)).getValue()));
	}

	public boolean getSaveNow() {
		return (Boolean) (((VoogaBoolean) myGlobalVariables.get(SAVE_PROGRESS)).getValue());
	}

	public void setNextLevelName(String levelName) {
		myGlobalVariables.put(NEXT_LEVEL_KEY, new VoogaString(levelName));
	}

	public IPhysicsEngine getPhysicsEngine() {
		return myPhysics;
	}

	public EventsContainer getEventContainer() {
		return myEventsContainer;
	}

	public Map<String, VoogaData> getGlobalVariables() {
		return myGlobalVariables;
	}

	public Set<Entry<String, Elementable>> getElementables() {
		return myElements.entrySet();
	}
}