package authoring.gui.menubar.menuitems;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import authoring.UIManager;
import authoring.gui.menubar.MenuItemHandler;
import authoring.model.ElementManager;
import authoring.model.ElementManagerUnserializer;
import javafx.stage.FileChooser.ExtensionFilter;
import player.gamedisplay.Menuable;
import tools.VoogaException;
import tools.VoogaFileChooser;

/**
 * File Menu Item to import an archetype.
 * 
 * @author Aditya Srinivasan, Arjun Desai, Nick Lockett, Harry Guo
 *
 */

public class OpenLevelFileItem extends MenuItemHandler {
	
	private Menuable myUIManager;

	/**
	 * Initializes the MenuItem
	 * 
	 * @param model
	 *            to interface backend interactions with the model
	 * @param event:
	 *            Unused vestige of previous poor programming. Should soon be
	 *            phased out.
	 */
	public OpenLevelFileItem(Menuable model) {
		super();
		myUIManager = model;
	}

	/**
	 * Action to be taken on the selection of this menuItem
	 * Open a certain level.
	 */
	@Override
	public void handle() throws VoogaException {
		String xmlPath = getLevelPath();
		ElementManagerUnserializer unserializer = new ElementManagerUnserializer(xmlPath);
		ElementManager manager = unserializer.unserialize();
		Path p = Paths.get(xmlPath);
		String file = p.getFileName().toString();
		manager.setName(file.replace(".xml", ""));
		((UIManager) myUIManager).openScene(manager);
	}

	/**
	 * Get Level Path through a file chooser.
	 * @return
	 * @throws VoogaException
	 */
	private String getLevelPath() throws VoogaException {
		VoogaFileChooser fileChooser = new VoogaFileChooser();
		fileChooser.addFilters(new ExtensionFilter("XML (.xml)", "*.xml"));
		fileChooser.setInitialDirectory(new File("games/"));
		return fileChooser.launch();
	}

}
