package authoring;

import javafx.animation.PauseTransition;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

/**
 * This launches the splash screen for a specified duration, after which it closes.
 *
 */
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
		
		PauseTransition delay = new PauseTransition(UILauncher.SPLASH_DURATION);
		delay.setOnFinished(event -> stage.close());
		delay.play();
		
	}
	
}
