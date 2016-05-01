package authoring.tagextension;

import authoring.splash.ProjectOpenBySearchPrompt;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Test class to search for games based on tags
 * @author Aditya Srinivasan
 *
 */
public class GameTagSearcherTest extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		new ProjectOpenBySearchPrompt().show();
	}
	
	public static void main(String args[]) {
		launch(args);
	}

}
