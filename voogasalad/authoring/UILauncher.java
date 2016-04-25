package authoring;

import java.io.File;
import java.io.FileOutputStream;

import authoring.interfaces.model.CompleteAuthoringModelable;
import authoring.model.ElementManager;
import authoring.splash.CreateCommand;
import authoring.splash.LearnCommand;
import authoring.splash.OpenCommand;
import authoring.splash.PlayGameCommand;
import authoring.splash.Splash;
import javafx.animation.PauseTransition;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;
import resources.VoogaBundles;

/**
 * Launches the Splash screen and UI manager
 */
public class UILauncher {
	
	static final Duration SPLASH_DURATION = Duration.millis(6500);
	
	/**
	 * Initializes and Launches the UI components, can be adjusted to implement a splash screen or other UI Features
	 * @param primaryStage to house the UI
	 * @param model interface to mediate interaction with backend
	 */
	// Lines under the comments "***" enable splash screen
	public UILauncher(Stage primaryStage) {
		
		// ***
		new Splash(new CreateCommand(), new LearnCommand(), new OpenCommand());
		
	}

}
