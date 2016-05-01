package player.leveldatamanager;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import java.util.Set;
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
	private IPhysicsEngine myPhysics;
	private String myMainCharID;
	private Map<String, Elementable> myElements;
	private SpriteFactory mySpriteFactory;
	private AnimationFactory myAnimationFactory;
	private Map<String, VoogaData> myGlobalVariables;
	private KeyEventContainer myKeyEventContainer;
	private String myTimerKey;
	private String myNextLevelKey;
	private LevelTransitioner myTransitioner;
	private ResourceBundle myEventMethods;

	/**
	 * Default constructor that takes in an instance of a physics module
	 * 
	 * @param physicsengine
	 */
	public LevelData(IPhysicsEngine physicsengine) {
		myEventMethods = VoogaBundles.EventMethods;
		myKeyEventContainer = new KeyEventContainer();
		myPhysics = physicsengine;
		myElements = new HashMap<>();
		myGlobalVariables = new HashMap<>();
		myNextLevelKey = VoogaBundles.defaultglobalvars.getProperty("NextLevelIndex");
		myTimerKey = VoogaBundles.defaultglobalvars.getProperty("Time");
	}

	/**
	 * Returns a list of sprite IDs given an archetype
	 * 
	 * @param archetype
	 * @return
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
	 * 
	 * @param archetype
	 * @return
	 */
	public Sprite addSprite(String archetype) {
		Elementable newSprite = mySpriteFactory.createSprite(archetype);
		myElements.put(newSprite.getId(), newSprite);
		return (Sprite) newSprite;
	}

	/**
	 * Returns a Global Variable (VoogaData) as specified by its variable name
	 * 
	 * @param variable
	 * @return
	 */
	public VoogaData getGlobalVar(String variable) {
		return myGlobalVariables.get(variable);
	}

	/**
	 * Returns a text object by ID
	 * 
	 * @param id
	 * @return
	 */
	public VoogaFrontEndText getText(Object id) {
		return (VoogaFrontEndText) myElements.get(id);
	}

	/**
	 * Put all objects into a pair of displayable objects
	 * 
	 * @return
	 */
	public List<Pair<Node, Boolean>> getDisplayableNodes() {
		List<Pair<Node, Boolean>> displayablenodes = new ArrayList<>();
		for (Object key : myElements.keySet()) {
			Boolean isStatic = (Boolean) myElements.get(key).getVoogaProperties().get(VoogaBundles.spriteProperties.getString("STATIC")).getProperty().getValue();
			displayablenodes.add(new Pair<Node, Boolean>(myElements.get(key).getNodeObject(), isStatic));
		}
		displayablenodes.sort(new PairZAxisComparator());
		return displayablenodes;
	}

	/**
	 * Add a given event and populate the pressed and released KeyCombos
	 */
	public void addEventAndPopulateKeyCombos(VoogaEvent event) {
		myKeyEventContainer.addEventAndPopulateKeyCombos(event, myEventMethods);
	}

	/**
	 * Refreshes the data and restarts timer in global variable and sets level
	 * 
	 * @param levelfilename
	 */
	public void refreshLevelData(String levelfilename) {
		myTransitioner = new LevelTransitioner(levelfilename, myElements, myKeyEventContainer, myGlobalVariables, myNextLevelKey);
		myElements = myTransitioner.populateNewSprites();
		myKeyEventContainer = myTransitioner.populateNewEvents();
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
		myGlobalVariables.get(myTimerKey).setValue(new Double(time));
	}

	/**
	 * Saves current game progress into a XML file
	 */
	public void saveProgress(String filePath) {
		myGlobalVariables.put(SAVE_PROGRESS, new VoogaBoolean(false));
		GameSaver saver = new GameSaver(myElements, myKeyEventContainer, myGlobalVariables, mySpriteFactory,
				myAnimationFactory);
		saver.saveCurrentProgress(filePath);
	}

	public String getNextLevelName() {
		return ((String) (((VoogaString) myGlobalVariables.get(myNextLevelKey)).getValue()));
	}

	public boolean getSaveNow() {
		return (Boolean) (((VoogaBoolean) myGlobalVariables.get(SAVE_PROGRESS)).getValue());
	}

	public void setNextLevelName(String levelName) {
		myGlobalVariables.put(myNextLevelKey, new VoogaString(levelName));
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

	public IPhysicsEngine getPhysicsEngine() {
		return myPhysics;
	}

	public KeyEventContainer getKeyEventContainer() {
		return myKeyEventContainer;
	}

	public Map<String, VoogaData> getGlobalVariables() {
		return myGlobalVariables;
	}

	@Override
	public Map<String, Elementable> getElements() {
		return myElements;
	}

	public Set<Entry<String, Elementable>> getElementables() {
		return myElements.entrySet();
	}
}