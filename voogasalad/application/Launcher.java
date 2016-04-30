package application;

import authoring.UILauncher;
import javafx.stage.Stage;

/**
 * Class to launch the Program Initializes both the UIComponents through
 * UILauncher and the Model, or "Backend" of the project
 * 
 * @author Arjun Desai, Aditya Srinivasan, Nick Lockett, Harry Guo
 *
 */
public class Launcher {

	/**
	 * Initializes authoring environment
	 * 
	 * @param primaryStage
	 *            to display the environment in
	 */
	public Launcher(Stage primaryStage) {
		new UILauncher(primaryStage);
	}

}