package authoring;

/**
 * 
 * Game Assets Tab class that contains the two default resource folders of Archetype and
 * Game Object. This class manages adding the path and the folder both in the computers resources
 * and the UI Game Assets tab.
 * 
 * @author Aditya Srinivasan
 * 
 */

import java.util.HashSet;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import java.util.Set;

import authoring.interfaces.model.CompleteAuthoringModelable;
import authoring.model.ElementManager;
import authoring.model.GameObject;
import authoring.resourceutility.ResourceTreeView;
import authoring.resourceutility.VoogaFile;
import authoring.resourceutility.VoogaFileType;
import gameengine.SpriteFactory;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import resources.VoogaBundles;

public class AssetUI extends Tab implements Observer {

	private ResourceTreeView rtv;
	private ResourceBundle gameDisplayProperties;
	private VoogaFile archetypesFolder;
	private VoogaFile objectsFolder;
	private CompleteAuthoringModelable myManager;
	private Set<Node> gameObjects;

	/**
	 * Constructor to initialize basic settings of the Game Assets, mostly with
	 * display and creating basic folders for the GUI and connecting the
	 * authoring model (element manager) to this tab.
	 * 
	 * @param myManager:
	 *            the complete authoring element manager that forms the
	 *            connection between the sprite factory and the game assets tab.
	 */
	public AssetUI(CompleteAuthoringModelable myManager) {
		gameDisplayProperties = VoogaBundles.GameDisplayProperties;
		this.myManager = myManager;
		this.setText(gameDisplayProperties.getString("GameAssetsWindowName"));
		this.myManager.getSpriteFactory().addObserver(this);
		this.myManager.addObserver(this);
		this.gameObjects = new HashSet<>();

		rtv = new ResourceTreeView(
				new VoogaFile(VoogaFileType.FOLDER, VoogaBundles.preferences.getProperty("GameName")));
		archetypesFolder = new VoogaFile(VoogaFileType.FOLDER, gameDisplayProperties.getString("Archetypes"));
		objectsFolder = new VoogaFile(VoogaFileType.FOLDER, gameDisplayProperties.getString("GameObjects"));
		rtv.addItem(archetypesFolder);
		rtv.addItem(objectsFolder);
		this.setContent(rtv);

		initializeArchetypeFolder(myManager.getSpriteFactory());
		addGameObjects(myManager.getElements());

	}

	/**
	 * Method for adding asset to this resource tree.
	 * 
	 * @param type:
	 *            type of VoogaFile
	 * @param archetype:
	 *            the archetype to build the VoogaFile upon (whether archetype
	 *            or game object)
	 * @param path:
	 *            adding this file to the overall file path
	 */
	private void addAsset(VoogaFileType type, String archetype, String path) {

		VoogaFile file = new VoogaFile(type, archetype);
		file.setPath(path);
		if (type == VoogaFileType.ARCHETYPE) {
			rtv.addItemToFolder(file, archetypesFolder);
		} else if (type == VoogaFileType.GAME_OBJECT) {
			rtv.addItemToFolder(new VoogaFile(type, archetype), objectsFolder);
		}
	}

	/**
	 * Method to add game objects to the game objects resource file.
	 * 
	 * @param arg:
	 *            list of nodes to add to game objects folder
	 */
	private void addGameObjects(List<Node> arg) {
		List<Node> objects = arg;
		if (!arg.isEmpty() && arg.get(0) instanceof Node) {
			for (Node object : objects) {
				if (object instanceof GameObject && !gameObjects.contains(object)) {
					gameObjects.add(object);
					VoogaFile file = new VoogaFile(VoogaFileType.GAME_OBJECT, ((GameObject) object).getName());
					file.setPath(((GameObject) object).getSprite().getImagePath());
					addAsset(file.getType(), file.toString(), file.getPath());
				}
			}
		}

	}

	/**
	 * Initializes and populates the archetype folder with sprites from a sprite
	 * factory.
	 * 
	 * @param sf:
	 *            sprite factory to get sprites from
	 */
	private void initializeArchetypeFolder(SpriteFactory sf) {
		for (String archetype : sf.getArchetypeMap().keySet()) {
			VoogaFile file = new VoogaFile(VoogaFileType.ARCHETYPE, archetype);
			file.setPath(sf.getArchetype(archetype).getImagePath());
			addAsset(file.getType(), file.toString(), file.getPath());
		}
	}

	/**
	 * Observer interface that communicates with the element manager/sprite
	 * factory in the complete authoring modelable to add the assets.
	 */
	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof SpriteFactory && arg instanceof VoogaFile) {
			addAsset(((VoogaFile) arg).getType(), ((VoogaFile) arg).toString(), ((VoogaFile) arg).getPath());
		}
		if (o instanceof ElementManager && arg instanceof List) {
			addGameObjects((List<Node>) arg);
		}

	}

}
