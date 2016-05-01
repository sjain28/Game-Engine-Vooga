package application;

import authoring.UILauncher;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * This class is the entry-point to start the program.
 * It is purely boiler-plate code delegating responsibility
 * of launching to other classes.
 *
 * @author Aditya Srinivasan, Arjun Desai, Nick Lockett, Harry Guo
 */
public class Main extends Application {

	/**
	 * Initializes the launcher.
	 */
	@Override 
	public void start(Stage primaryStage) throws Exception {
		// Launches Main Program
		System.setProperty("glass.accessible.force", "false"); //This fixes a stupid bug on Saumya's machine - it's harmless. 
		new UILauncher(primaryStage);
	}

	/**
	 * Launches application.
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}

}