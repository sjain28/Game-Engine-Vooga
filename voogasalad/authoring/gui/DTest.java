package authoring.gui;

import javafx.application.Application;
import javafx.stage.Stage;

public class DTest extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		new DataBaseDisplay().show();
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
	
}
