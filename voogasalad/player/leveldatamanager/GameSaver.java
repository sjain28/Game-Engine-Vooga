/**
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
	 * 
	 * @param elements
	 * @param container
	 * @param globals
	 * @param spritefactory
	 */
	public GameSaver(Map<String, Elementable> elements, KeyEventContainer container,
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
}
