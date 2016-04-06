package application;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * This class is the entrypoint to start the program.
 * It is purely boiler-plate code delegating responsibility
 * of launching to other classes.
 *
 */
public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		// Launches Main Program (only MenuBar of GUI implemented as of April 5)
		new Launcher(primaryStage);
		
		// Launches Mosaic
		//new Tester();
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}