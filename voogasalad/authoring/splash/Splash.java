package authoring.splash;

import authoring.Command;
import authoring.VoogaScene;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * This launches the splash screen for a specified duration, after which it
 * closes.
 *
 */
public class Splash extends Pane {

	private EventHandler<MouseEvent> e;

	/**
	 * Initialized the splash screen,
	 * 
	 * @param e
	 *            MouseEvent to signify when to close the screen
	 */
	@Deprecated
	public Splash(EventHandler<MouseEvent> e) {
		// this.e = e;
		// Image image = new
		// Image(this.getClass().getResourceAsStream("/resources/images/splash.gif"));
		//
		// ImageView iv = new ImageView(image);
		// this.getChildren().add(iv);
		//
		// Scene scene = new VoogaScene(this);
		// Stage stage = new Stage();
		// stage.setScene(scene);
		// stage.initStyle(StageStyle.UNDECORATED);
		// stage.show();
		//
		// PauseTransition delay = new
		// PauseTransition(UILauncher.SPLASH_DURATION);
		// delay.setOnFinished(event -> {
		// stage.close();
		showSplashMessage();
		// });
		// delay.play();

	}
	
	private Command create, learn, open;
	
	public Splash(Command create, Command learn, Command open) {
		this.create = create;
		this.learn = learn;
		this.open = open;
		// this.e = e;
		// Image image = new
		// Image(this.getClass().getResourceAsStream("/resources/images/splash.gif"));
		//
		// ImageView iv = new ImageView(image);
		// this.getChildren().add(iv);
		//
		// Scene scene = new VoogaScene(this);
		// Stage stage = new Stage();
		// stage.setScene(scene);
		// stage.initStyle(StageStyle.UNDECORATED);
		// stage.show();
		//
		// PauseTransition delay = new
		// PauseTransition(UILauncher.SPLASH_DURATION);
		// delay.setOnFinished(event -> {
		// stage.close();
		showSplashMessage();
		// });
		// delay.play();

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

		scene.setOnMouseClicked(e);
		pane.setOnMouseClicked(event -> {
			if (inBounds(event.getSceneY(), 425, 620)) {
				if (inBounds(event.getSceneX(), 525, 685)) {
					create.execute();
					stage.close();
				} else if (inBounds(event.getSceneX(), 745, 910)) {
					learn.execute();
					stage.close();
				} else if (inBounds(event.getSceneX(), 965, 1135)) {
					open.execute();
					stage.close();
				}
			}
		});

	}

	private boolean inBounds(double x, double min, double max) {
		return x > min && x < max;
	}

}
