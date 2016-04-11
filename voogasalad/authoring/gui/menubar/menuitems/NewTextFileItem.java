package authoring.gui.menubar.menuitems;

import authoring.VoogaScene;
import authoring.gui.GameObjectBuilder;
import authoring.gui.TextObjectBuilder;
import authoring.gui.menubar.MenuItemHandler;
import authoring.interfaces.model.CompleteAuthoringModelable;
import authoring.interfaces.model.EditElementable;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.InputEvent;
import javafx.stage.Stage;

public class NewTextFileItem extends MenuItemHandler {
    private EditElementable myManager;
	
	public NewTextFileItem(CompleteAuthoringModelable model, EventHandler<InputEvent> event) {
		super();
		myManager = model;
		
	}

	@Override
	public void handle() {
	    Stage popup = new Stage();
            popup.setTitle("New Text Object");
            TextObjectBuilder initializer = new TextObjectBuilder(myManager, popup);
            Scene scene = new VoogaScene(initializer);
            popup.setScene(scene);
            popup.show();
            
	}

}
