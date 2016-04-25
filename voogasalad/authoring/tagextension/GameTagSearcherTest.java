package authoring.tagextension;

import javafx.application.Application;
import javafx.stage.Stage;

public class GameTagSearcherTest extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		new GameTagSearcher().show();
	}
	
	public static void main(String args[]) {
		launch(args);
	}

}
