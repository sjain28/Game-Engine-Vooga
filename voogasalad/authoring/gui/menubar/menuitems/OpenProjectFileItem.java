package authoring.gui.menubar.menuitems;

import java.io.File;

import authoring.UILauncher;
import authoring.gui.menubar.MenuItemHandler;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import player.gamedisplay.Menuable;

public class OpenProjectFileItem extends MenuItemHandler {

	/**
	 * Initializes the MenuItem
	 * 
	 * @param model
	 *            to interface backend interactions with the model
	 * @param event:
	 *            Unused vestige of previous poor programming. Should soon be
	 *            phased out.
	 */
	public OpenProjectFileItem(Menuable model) {
		super();
	}

	/**
	 * Action to be taken on the selection of this menuItem
	 * Opens a new project
	 */
	@Override
	public void handle() {
		FileChooser popUp = new FileChooser();
		popUp.setTitle("Open New Project File");
		popUp.getExtensionFilters().addAll(
				new ExtensionFilter("All Files", "*.*"));
		File file = popUp.showOpenDialog(new Stage());
		if (file != null) {
			new UILauncher(new Stage());
		}
	}

}
