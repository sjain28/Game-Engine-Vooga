package application;

import java.io.File;
import authoring.UILauncher;
import authoring.interfaces.model.CompleteAuthoringModelable;
import authoring.model.ElementManager;
import javafx.stage.Stage;

public class Launcher {
	
	public Launcher(Stage primaryStage) {
	        CompleteAuthoringModelable model = new ElementManager();
		new UILauncher(primaryStage, model);
	}
	
	public Launcher(Stage primaryStage, File fileToLoadIn) {
	    
	    //TODO: Parse the file, use it to instantiate the ElementManager
            CompleteAuthoringModelable model = new ElementManager();
            new UILauncher(primaryStage, model);
    }

}