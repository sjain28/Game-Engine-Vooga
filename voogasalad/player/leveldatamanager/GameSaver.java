/**
 * 
 */
package player.leveldatamanager;

import java.util.ArrayList;
import java.util.Map;
import authoring.interfaces.Elementable;
import data.DataContainerOfLists;
import data.FileWriterFromGameObjects;
import gameengine.SpriteFactory;
import resources.VoogaBundles;
import stats.database.CellEntry;
import stats.database.PlaySession;
import stats.database.StatCell;
import stats.database.VoogaDataBase;
import tools.VoogaException;
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
    private static final String LEVELS = "levels/";
	private Map<String, Elementable> myElements;
	private KeyEventContainer myKeyEventContainer;
	private Map<String, VoogaData> myGlobalVariables;
	private SpriteFactory mySpriteFactory;
	
	/**
	 * Default constructor that saves basic information necessary to save a game state
	 * 
	 * @param elements
	 * @param container
	 * @param globals
	 * @param spritefactory
	 */
	public GameSaver(Map<String, Elementable> elements, KeyEventContainer container,
					 Map<String, VoogaData> globals, SpriteFactory spritefactory) {
		myElements = elements;
		myKeyEventContainer = container;
		myGlobalVariables = globals;
		mySpriteFactory = spritefactory;
	}
	
	/**
	 * Public method called in LevelData to save current state of the game
	 * 
	 * @param filePath
	 * @param playerName
	 */
	public void saveCurrentProgress(String filePath, String playerName, String gameName) {
        DataContainerOfLists dataContainer = new DataContainerOfLists(new ArrayList<>(myElements.values()), 
        		myGlobalVariables, myKeyEventContainer.getEvents(), mySpriteFactory.getArchetypeMap());
        try {
            FileWriterFromGameObjects.saveGameObjects(dataContainer, filePath +LEVELS +  playerName + XML_SUFFIX);
        } catch (Exception e) {
        	new VoogaException(VoogaBundles.exceptionProperties.getString("SavingFailed"));
        }
        CellEntry entry = VoogaDataBase.getInstance().getStatByGameAndUser(gameName, playerName);
        PlaySession latestSession =  ((StatCell) entry).getLatestPlaySession();
        latestSession.setProperty(PlaySession.LEVEL_REACHED,new VoogaString(filePath));
	}
}
