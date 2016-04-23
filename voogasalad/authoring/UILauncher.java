package authoring;

import authoring.interfaces.model.CompleteAuthoringModelable;
import authoring.model.ElementManager;
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
	private static final String DEFAULT_PROJECT_NAME = "My DoovalSalad Project";
	
	/**
	 * Initializes and Launches the UI components, can be adjusted to implement a splash screen or other UI Features
	 * @param primaryStage to house the UI
	 * @param model interface to mediate interaction with backend
	 */
	// Lines under the comments "***" enable splash screen
	public UILauncher(Stage primaryStage) {
		
		// ***
		// new Splash(e -> {
			ProjectInitializationPrompt prompt = new ProjectInitializationPrompt();
			prompt.setProceedEvent(ee -> {
				prompt.close();
				String name = (prompt.getName() == null) ? DEFAULT_PROJECT_NAME : prompt.getName();
				VoogaBundles.preferences.setProperty("GameName", name);
				UIManager manager = new UIManager(new ElementManager());
				Scene scene = new VoogaScene(manager);
				primaryStage.setScene(scene);
				primaryStage.setMaximized(true);
				primaryStage.show();
			});
			prompt.show();
		// });
		
	}

}
