package application;

import java.io.File;
import authoring.UILauncher;
import authoring.interfaces.model.CompleteAuthoringModelable;
import authoring.model.ElementManager;
import javafx.stage.Stage;

/**
 * Class to launch the Program Initializes both the UIComponents through
 * UILauncher and the Model, or "Backend" of the project
 * 
 * @author Nick
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

	/**
	 * Initializes authoring environment
	 * 
	 * @param primaryStage
	 *            to display the environment in
	 * @param file
	 *            to load as the model
	 */
	public Launcher(Stage primaryStage, File fileToLoadIn) {

		// TODO: Parse the file, use it to instantiate the ElementManager
		new UILauncher(primaryStage);
	}

}