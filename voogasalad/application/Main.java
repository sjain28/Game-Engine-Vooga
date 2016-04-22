package application;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * This class is the entry-point to start the program.
 * It is purely boiler-plate code delegating responsibility
 * of launching to other classes.
 *
 * @author Harry Guo, Nick Lockett, Aditya Srinivasan, Arjun Desai
 */
public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		// launches Main Program
		new Launcher(primaryStage);
		
		// Launches Mosaic
		// new BoundsTester();
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}