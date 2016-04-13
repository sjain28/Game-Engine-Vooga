package authoring.gui.menubar.menuitems;

import application.Launcher;
import authoring.gui.menubar.MenuItemHandler;
import authoring.interfaces.gui.Saveable;
import authoring.interfaces.model.CompleteAuthoringModelable;
import javafx.event.EventHandler;
import javafx.scene.input.InputEvent;
import javafx.stage.Stage;

public class NewProjectFileItem extends MenuItemHandler {
    private Saveable myManager;
	
	public NewProjectFileItem(CompleteAuthoringModelable model, EventHandler<InputEvent> event) {
		super();
		myManager = model;
	}

	@Override
	public void handle() {
		new Launcher(new Stage());
	}

}
