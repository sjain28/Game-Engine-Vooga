package authoring.gui.animation;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Simple tester to debug.
 * 
 * @author Aditya Srinivasan
 *
 */
public class AnimationGUITester extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		new AnimationEventBuilder().show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
