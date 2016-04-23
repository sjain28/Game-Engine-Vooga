package authoring.gui.menubar.menuitems;

import java.nio.file.Path;
import java.nio.file.Paths;

import authoring.gui.menubar.MenuItemHandler;
import authoring.model.ElementManager;
import authoring.model.ElementManagerUnserializer;
import javafx.stage.FileChooser.ExtensionFilter;
import player.gamedisplay.Menuable;
import tools.VoogaException;
import tools.VoogaFileChooser;

public class OpenLevelFileItem extends MenuItemHandler {

	private Menuable myUIManager;

	public OpenLevelFileItem(Menuable model) {
		super();
		myUIManager = model;
	}

	@Override
	public void handle() throws VoogaException {
		String xmlPath = getLevelPath();
		ElementManagerUnserializer unserializer = new ElementManagerUnserializer(xmlPath);
		ElementManager manager = unserializer.unserialize();
		Path p = Paths.get(xmlPath);
		String file = p.getFileName().toString();
		manager.setName(file.replace(".xml", ""));
		myUIManager.openScene(manager);
	}

	private String getLevelPath() throws VoogaException {
		VoogaFileChooser fileChooser = new VoogaFileChooser();
		fileChooser.addFilters(new ExtensionFilter("XML (.xml)", "*.xml"));
		return fileChooser.launch();
	}

}
