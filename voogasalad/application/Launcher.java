package application;

import authoring.UILauncher;
import authoring.model.ElementManager;
import javafx.stage.Stage;

public class Launcher {
	
	public Launcher(Stage primaryStage) {
	        new ElementManager();
		new UILauncher(primaryStage);
	}

}