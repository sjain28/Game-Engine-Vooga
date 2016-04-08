package authoring.gui.menubar.menuitems;

import authoring.VoogaScene;
import authoring.gui.GameObjectBuilder;
import authoring.gui.menubar.MenuItemHandler;
import authoring.interfaces.model.CompleteAuthoringModelable;
import authoring.interfaces.model.EditElementable;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class NewGameObjectFileItem extends MenuItemHandler {
        EditElementable myManager;
	
	public NewGameObjectFileItem(CompleteAuthoringModelable model) {
		super();
		myManager = model;
	}

	@Override
	public void handle() {
	    Stage popup = new Stage();
	    popup.setTitle("New Game Object");
	    GameObjectBuilder initializer = new GameObjectBuilder(myManager, popup);
	    Scene scene = new VoogaScene(initializer);
	    popup.setScene(scene);
	    popup.show();
	    
	}

}
