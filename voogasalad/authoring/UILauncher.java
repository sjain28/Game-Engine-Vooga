package authoring;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class UILauncher {
	
	public UILauncher(Stage primaryStage) {
		UIManager manager = new UIManager();
		
		Scene scene = new Scene(manager);
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}

}
