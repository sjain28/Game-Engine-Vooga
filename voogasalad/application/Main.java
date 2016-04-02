package application;

import authoring.Tester;
import authoring.resourceutility.ResourceUI;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		//new Launcher();
		//new Tester();
		new ResourceUI(primaryStage);
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
