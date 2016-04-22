package authoring;

import java.util.HashSet;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
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

public class AssetUI extends Tab implements Observer {

	private ResourceTreeView rtv;
	private VoogaFile archetypesFolder;
	private VoogaFile objectsFolder;
	private CompleteAuthoringModelable myManager;
	private Set<Node> gameObjects;

	private static final String WINDOW_NAME = "Game Assets";

	public AssetUI(CompleteAuthoringModelable myManager) {
		this.myManager = myManager;
		this.setText(WINDOW_NAME);
		this.myManager.getSpriteFactory().addObserver(this);
		this.myManager.addObserver(this);
		this.gameObjects = new HashSet<Node>();

		archetypesFolder = new VoogaFile(VoogaFileType.FOLDER, "Archetypes");
		objectsFolder = new VoogaFile(VoogaFileType.FOLDER, "Game Objects");

		initializeArchetypeFolder(myManager.getSpriteFactory());
		addGameObjects(myManager.getElements());

	}
	
	public void setProjectName(String name) {
		rtv = new ResourceTreeView(new VoogaFile(VoogaFileType.FOLDER, name));
		rtv.addItem(archetypesFolder);
		rtv.addItem(objectsFolder);
		this.setContent(rtv);
	}

	private void addAsset(VoogaFileType type, String archetype, String path) {
		// TODO: use reflection or something
		VoogaFile file = new VoogaFile(type, archetype);
		file.setPath(path);
		if (type == VoogaFileType.ARCHETYPE) {
			rtv.addItemToFolder(file, archetypesFolder);
		} else if (type == VoogaFileType.GAME_OBJECT) {
			rtv.addItemToFolder(new VoogaFile(type, archetype), objectsFolder);
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof SpriteFactory) {
			if (arg instanceof VoogaFile) {
				addAsset(((VoogaFile) arg).getType(), ((VoogaFile) arg).toString(), ((VoogaFile) arg).getPath());
			}
		}
		if (o instanceof ElementManager) {
			if (arg instanceof List) {
				addGameObjects((List<Node>) arg);
			}
		}

	}

	private void addGameObjects(List<Node> arg) {
		List<Node> objects = (List<Node>) arg;
		if (arg.size() > 0 && arg.get(0) instanceof Node) {
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

	private void initializeArchetypeFolder(SpriteFactory sf) {
		for (String archetype : sf.getArchetypeMap().keySet()) {
			VoogaFile file = new VoogaFile(VoogaFileType.ARCHETYPE, archetype);
			file.setPath(sf.getArchetype(archetype).getImagePath());
			addAsset(file.getType(), file.toString(), file.getPath());
		}
	}

}
