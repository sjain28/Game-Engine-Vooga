package authoring;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Splash extends Pane {

	public Splash() {
		Image image = new Image(this.getClass().getResourceAsStream("/resources/images/splash.gif"));
		
		ImageView iv = new ImageView(image);
		
		this.getChildren().add(iv);
		
		Scene scene = new VoogaScene(this);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.initStyle(StageStyle.UNDECORATED);
		stage.show();
		
	}
	
}
