package application;

import authoring.UILauncher;
import authoring.interfaces.model.CompleteAuthoringModelable;
import authoring.model.ElementManager;
import javafx.stage.Stage;

public class Launcher {
	
	public Launcher(Stage primaryStage) {
	        CompleteAuthoringModelable model = new ElementManager();
		new UILauncher(primaryStage, model);
	}

}