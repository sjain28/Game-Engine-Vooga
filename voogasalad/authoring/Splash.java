package authoring;

import javafx.animation.PauseTransition;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

/**
 * This launches the splash screen for a specified duration, after which it closes.
 *
 */
public class Splash extends Pane {

	private EventHandler<MouseEvent> e;
	
	public Splash(EventHandler<MouseEvent> e) {
		this.e = e;
		Image image = new Image(this.getClass().getResourceAsStream("/resources/images/splash.gif"));
		
		ImageView iv = new ImageView(image);
		this.getChildren().add(iv);
		
		Scene scene = new VoogaScene(this);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.initStyle(StageStyle.UNDECORATED);
		stage.show();
		
		PauseTransition delay = new PauseTransition(UILauncher.SPLASH_DURATION);
		delay.setOnFinished(event -> {
			stage.close();
			showSplashMessage();
		});
		delay.play();
		
	}
	
	private void showSplashMessage() {
		Pane pane = new Pane();
		Scene scene = new VoogaScene(pane);
		pane.getChildren().clear();

		Image image = new Image(this.getClass().getResourceAsStream("/resources/images/splash-message.png"));
		
		ImageView iv = new ImageView(image);
		
		pane.getChildren().add(iv);
		
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.initStyle(StageStyle.UNDECORATED);
		stage.show();
		
		pane.setOnMouseClicked(e);
		pane.setOnMouseClicked(event -> {
			stage.close();
		});
	}
	
}
