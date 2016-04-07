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
	
	// Lines under the comments "***" enable splash screen
	public UILauncher(Stage primaryStage, CompleteAuthoringModelable model) {
		
		// ***
		// new Splash();
		
		// ***
		// PauseTransition delay = new PauseTransition(SPLASH_DURATION);
		// delay.setOnFinished(e -> {
			
			UIManager manager = new UIManager(model);
			Scene scene = new VoogaScene(manager);
			primaryStage.setScene(scene);
			primaryStage.setMaximized(true);
			primaryStage.show();
			
		// ***
		// });
		// delay.play();
		
	}

}
