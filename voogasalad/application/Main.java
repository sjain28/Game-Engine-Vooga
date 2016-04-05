package application;

import authoring.Tester;
import authoring.resourceutility.ResourceUI;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		// Launches Main Program (only MenuBar of GUI implemented as of April 5)
		new Launcher(primaryStage);
		
		// Launches Mosaic
		//new Tester();
		
		// Launches Resource Utility standalone
		//new ResourceUI(primaryStage);
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}