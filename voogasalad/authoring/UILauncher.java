package authoring;

import authoring.splash.LoginScreen;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Launches the Splash screen and UI manager
 * 
 * @author Aditya Srinivasan, Arjun Desai, Nick Lockett, Harry Guo
 */
public class UILauncher {

	public static final Duration SPLASH_DURATION = Duration.millis(6500);

	/**
	 * Launches the Splash Screen
	 * 
	 * @param primaryStage:
	 *            to hold splash screen
	 */
	public UILauncher(Stage primaryStage) {
		primaryStage = new LoginScreen();
		primaryStage.show();
	}

}
