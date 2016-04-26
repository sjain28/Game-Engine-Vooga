package authoring;


import authoring.splash.CreateCommand;
import authoring.splash.LearnCommand;
import authoring.splash.OpenCommand;
import authoring.splash.Splash;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Launches the Splash screen and UI manager
 * 
 * @author Arjun Desai, Nick Lockett, Aditya Srinivasan, Harry Guo
 */
public class UILauncher {
	
	static final Duration SPLASH_DURATION = Duration.millis(6500);
	
	/**
	 * Launches the Splash Screen
	 * 
	 * @param primaryStage: to hold splash screen
	 */
	public UILauncher(Stage primaryStage) {
		new Splash(new CreateCommand(), new LearnCommand(), new OpenCommand());
	}

}
