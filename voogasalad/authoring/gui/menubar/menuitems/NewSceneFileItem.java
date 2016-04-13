package authoring.gui.menubar.menuitems;

import authoring.gui.menubar.MenuItemHandler;
import authoring.interfaces.model.CompleteAuthoringModelable;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.input.InputEvent;
import javafx.scene.input.MouseEvent;

public class NewSceneFileItem extends MenuItemHandler {
        private EventHandler myEvent;
	
	public NewSceneFileItem(CompleteAuthoringModelable model, EventHandler<InputEvent> event) {
		super();
		myEvent = event;
	}

	@Override
	public void handle() {
		
	}

}
