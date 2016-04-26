package socialcenter;

import authoring.VoogaScene;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

public class LogInSceneCreator {

	private Scene logInScene;
	
	public Scene createLogInScene(BorderPane borderPane, int paneDim1, int paneDim2) {
		logInScene = new VoogaScene(borderPane, paneDim1, paneDim2);
		Button button1 = new Button("JOSHUA XU");
		button1.setLayoutX(35);
		borderPane.getChildren().add(button1);
		return logInScene;
	}
	
	
}
