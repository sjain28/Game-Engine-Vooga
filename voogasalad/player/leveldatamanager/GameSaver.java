// This entire file is part of my masterpiece.
// Hunter Lee

/**
 * Code Masterpiece - Hunter Lee (hl130)
 * 
 * To demonstrate my knowledge of Object-oriented programming, I chose a design pattern
 * that is relevant to this project. I had this in mind for a while--I wrote this GameSaver
 * class, and when I did so, I felt that the constructor was taking too many arguments. So I learned
 * of an OOP design pattern called the Builder pattern, and I demonstrate my knowledge of the Builder
 * pattern here.
 * 
 * Its advantages are 
 * 1) The Builder pattern allows you to vary a productís internal representation.
 * 2) Encapsulates code for construction and representation.
 * 3) Provides control over steps of construction process.
 * 
 * This changes the way GameSaver is created on the client side (LevelData line 186), which I
 * think is more intuitive for the client. It is a good design because it gives me the control of the 
 * constructor and encapsulates the details of the GameSaver class by using a Builder object.
 * 
 * Attribution: Effective Java, Joshua Block
 * 
 */
package player.leveldatamanager;

import java.util.ArrayList;
import java.util.Map;

import events.AnimationFactory;
import authoring.interfaces.Elementable;
import data.DataContainerOfLists;
import data.FileWriterFromGameObjects;
import gameengine.SpriteFactory;
import resources.VoogaBundles;
import stats.database.CellEntry;
import stats.database.PlaySession;
import stats.database.StatCell;
import stats.database.VoogaDataBase;
import tools.VoogaAlert;
import tools.VoogaString;
import tools.interfaces.VoogaData;

/**
 * Default GameSaver that provides game-saving functionalities
 * 
 * @author Hunter Lee
 *
 */
public class GameSaver implements IGameSaver {

	private static final String XML_SUFFIX = ".xml";
	private static final String GAMES = "games/";
	private static final String LEVELS = "levels/";
	private static final String SLASH = "/";
	private Map<String, Elementable> myElements;
	private KeyEventContainer myKeyEventContainer;
	private Map<String, VoogaData> myGlobalVariables;
	private SpriteFactory mySpriteFactory;
	private AnimationFactory myAnimationFactory;

	/**
	 * Default constructor that saves basic information necessary to save a game state
	 * **Code Masterpiece** - made private
	 * 
	 * @param elements
	 * @param container
	 * @param globals
	 * @param spritefactory
	 */
	private GameSaver(Map<String, Elementable> elements, KeyEventContainer container,
			Map<String, VoogaData> globals, SpriteFactory spritefactory, AnimationFactory AnimationFactory) {
		myElements = elements;
		myKeyEventContainer = container;
		myGlobalVariables = globals;
		mySpriteFactory = spritefactory;
		myAnimationFactory = AnimationFactory;
	}

	/**
	 * Public method called in LevelData to save current state of the game
	 * 
	 * @param nameOfGame
	 */
	public void saveCurrentProgress(String nameOfGame) {
		String gameName = VoogaBundles.preferences.getProperty("GameName");
		String playerName = VoogaBundles.preferences.getProperty("UserName");
		DataContainerOfLists dataContainer = new DataContainerOfLists(new ArrayList<>(myElements.values()), 
				myGlobalVariables, myKeyEventContainer.getEvents(), mySpriteFactory.getArchetypeMap(),
				myAnimationFactory);

		try {
			FileWriterFromGameObjects.saveGameObjects(dataContainer,GAMES+ nameOfGame + SLASH + LEVELS +  playerName + XML_SUFFIX);
		} catch (Exception e) {
			VoogaAlert alert = new VoogaAlert(VoogaBundles.exceptionProperties.getString("SavingFailed"));
			alert.showAndWait();
		}
		CellEntry entry = VoogaDataBase.getInstance().getStatByGameAndUser(gameName, playerName);
		PlaySession latestSession =  ((StatCell) entry).getLatestPlaySession();
		latestSession.setProperty(PlaySession.LEVEL_REACHED,new VoogaString(nameOfGame));
		((StatCell) entry).updateProgress(playerName);
	}
	
    /**
     * Builder class as outlined in the Second Edition of Joshua Bloch's
     * Effective Java. This Builder class is used to generate an instance of GameSaver
     * 
     * @author Hunter Lee
     * 
     */
    public static class SaverBuilder {
    	private Map<String, Elementable> nestedElements;
    	private Map<String, VoogaData> nestedGlobalVariables;
    	private KeyEventContainer nestedKeyEventContainer;
    	private SpriteFactory nestedSpriteFactory;
    	private AnimationFactory nestedAnimationFactory;
   
    	public SaverBuilder sprite(SpriteFactory spritefactory) {
    		this.nestedSpriteFactory = spritefactory;
    		return this;
    	}
    	
    	public SaverBuilder animation(AnimationFactory animationfactory) {
    		this.nestedAnimationFactory = animationfactory;
    		return this;
    	}
    
    	public SaverBuilder elements(Map<String, Elementable> elements) {
    		this.nestedElements = elements;
    		return this;
    	}
    	
    	public SaverBuilder eventcontainer(KeyEventContainer container) {
    		this.nestedKeyEventContainer = container;
    		return this;
    	}
    	
    	public SaverBuilder globals(Map<String, VoogaData> globals) {
    		this.nestedGlobalVariables = globals;
    		return this;
    	}
    	
    	public GameSaver build() {
    		return new GameSaver(this);
    	}
    }
    
    private GameSaver(SaverBuilder builder) {
    	this.myElements = builder.nestedElements;
    	this.myKeyEventContainer = builder.nestedKeyEventContainer;
    	this.myGlobalVariables = builder.nestedGlobalVariables;
    	this.mySpriteFactory = builder.nestedSpriteFactory;
    	this.myAnimationFactory = builder.nestedAnimationFactory;
    }
}
