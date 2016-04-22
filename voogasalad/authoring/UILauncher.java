package authoring;

import authoring.interfaces.model.CompleteAuthoringModelable;
import javafx.animation.PauseTransition;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Launches the Splash screen and UI manager
 */
public class UILauncher {
	
	static final Duration SPLASH_DURATION = Duration.millis(7000);
	/**
	 * Initializes and Launches the UI components, can be adjusted to implement a splash screen or other UI Features
	 * @param primaryStage to house the UI
	 * @param model interface to mediate interaction with backend
	 */
	// Lines under the comments "***" enable splash screen
	public UILauncher(Stage primaryStage, CompleteAuthoringModelable model) {
		
		// ***
		// new Splash(e -> {
			ProjectInitializationPrompt prompt = new ProjectInitializationPrompt();
			prompt.setProceedEvent(ee -> {
				prompt.close();
				UIManager manager = new UIManager(model);
				manager.setProjectName(prompt.getName());
				Scene scene = new VoogaScene(manager);
				primaryStage.setScene(scene);
				primaryStage.setMaximized(true);
				primaryStage.show();
			});
			prompt.show();
		// });
		
	}

}
