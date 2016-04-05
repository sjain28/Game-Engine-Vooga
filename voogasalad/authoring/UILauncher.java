package authoring;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class UILauncher {
	
	public UILauncher(Stage primaryStage) {
		
		//new Splash();
		
		UIManager manager = new UIManager();
		
		Scene scene = new VoogaScene(manager);
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}

}
