package authoring;

import java.util.Observable;
import java.util.Observer;

import authoring.interfaces.model.CompleteAuthoringModelable;
import authoring.resourceutility.ResourceTreeView;
import authoring.resourceutility.VoogaFile;
import authoring.resourceutility.VoogaFileType;
import javafx.scene.control.Tab;

public class AssetUI extends Tab implements Observer {

	private ResourceTreeView rtv;
	private VoogaFile archetypesFolder;
	private VoogaFile objectsFolder;
	private CompleteAuthoringModelable myManager;

	private static final String WINDOW_NAME = "Game Assets";
	private static final String DEFAULT_PROJECT_NAME = "My Project";

	public AssetUI(CompleteAuthoringModelable myManager) {
		this.myManager = myManager;
		this.setText(WINDOW_NAME);
		this.myManager.addObserver(this);
		rtv = new ResourceTreeView(new VoogaFile(VoogaFileType.FOLDER, DEFAULT_PROJECT_NAME));
		archetypesFolder = new VoogaFile(VoogaFileType.FOLDER, "Archetypes");
		objectsFolder = new VoogaFile(VoogaFileType.FOLDER, "Game Objects");
		rtv.addItem(archetypesFolder);
		rtv.addItem(objectsFolder);
		this.setContent(rtv);
	}

	private void addAsset(VoogaFileType type, String archetype, String path) {
		//TODO: use reflection or something
		VoogaFile file = new VoogaFile(type, archetype);
		file.setPath(path);
		if(type == VoogaFileType.ARCHETYPE) {
			rtv.addItemToFolder(file, archetypesFolder);
		} else if(type == VoogaFileType.GAME_OBJECT) {
			rtv.addItemToFolder(new VoogaFile(type, archetype), objectsFolder);
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		if(o instanceof CompleteAuthoringModelable) {
			if(arg instanceof String) {
				String[] components = ((String) arg).split("%");
				addAsset(VoogaFileType.ARCHETYPE, components[0], components[1]);
			}
		}
		
	}

}
