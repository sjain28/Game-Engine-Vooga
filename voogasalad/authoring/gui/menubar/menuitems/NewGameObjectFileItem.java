package authoring.gui.menubar.menuitems;

import authoring.VoogaScene;
import authoring.gui.GameObjectMaker;
import authoring.gui.menubar.MenuItemHandler;
import authoring.interfaces.model.CompleteAuthoringModelable;
import authoring.interfaces.model.EditElementable;
import authoring.model.GameObject;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class NewGameObjectFileItem extends MenuItemHandler {
        EditElementable gameModel;
	
	public NewGameObjectFileItem(CompleteAuthoringModelable model) {
		super(model);
		gameModel = getManager();
	}

	@Override
	public void handle() {
	    Stage popup = new Stage();
	    GameObjectMaker initializer = new GameObjectMaker();
	    Scene scene = new VoogaScene(initializer);
	    
	    
	    gameModel.addGameElements();
	}

}
