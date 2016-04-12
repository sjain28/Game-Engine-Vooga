package authoring.gui.menubar.menuitems;

import application.Launcher;
import authoring.gui.menubar.MenuItemHandler;
import authoring.interfaces.model.CompleteAuthoringModelable;
import authoring.interfaces.model.Savable;
import javafx.event.EventHandler;
import javafx.scene.input.InputEvent;
import javafx.stage.Stage;

public class NewProjectFileItem extends MenuItemHandler {
    private Savable myManager;
	
	public NewProjectFileItem(CompleteAuthoringModelable model, EventHandler<InputEvent> event) {
		super();
		myManager = model;
	}

	@Override
	public void handle() {
		Launcher newProject = new Launcher(new Stage());
	}

}
