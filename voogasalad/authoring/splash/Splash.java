package authoring.splash;

import authoring.Command;
import authoring.UILauncher;
import authoring.VoogaScene;
import javafx.animation.PauseTransition;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * This launches the splash screen for a specified duration, after which it
 * closes.
 *
 */
public class Splash extends Pane {

	private static final double MIN_Y = 425;
	private static final double MAX_Y = 620;
	private static final double CREATE_MIN_X = 525;
	private static final double CREATE_MAX_X = 685;
	private static final double LEARN_MIN_X = 745;
	private static final double LEARN_MAX_X = 910;
	private static final double PLAY_MIN_X = 965;
	private static final double PLAY_MAX_X = 1135;
	private static final String SPLASH_PATH = "/resources/images/splash-message.png";
	private static final String SPLASH_GIF_PATH = "/resources/images/splash.gif";
	
	private Command create, learn, open;

	/**
	 * Initializes the splash screen.
	 * 
	 * @param create
	 * @param learn
	 * @param open
	 */
	public Splash(Command create, Command learn, Command open) {
		this.create = create;
		this.learn = learn;
		this.open = open;
		Image image = new Image(this.getClass().getResourceAsStream(SPLASH_GIF_PATH));

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

		Image image = new Image(this.getClass().getResourceAsStream(SPLASH_PATH));
		ImageView iv = new ImageView(image);

		pane.getChildren().add(iv);

		Stage stage = new Stage();
		stage.setScene(scene);
		stage.initStyle(StageStyle.UNDECORATED);
		stage.show();

		pane.setOnMouseClicked(event -> {
			if (inBounds(event.getSceneY(), MIN_Y, MAX_Y)) {
				if (inBounds(event.getSceneX(), CREATE_MIN_X, CREATE_MAX_X)) {
					create.execute(); stage.close();
				} else if (inBounds(event.getSceneX(), LEARN_MIN_X, LEARN_MAX_X)) {
					learn.execute(); stage.close();
				} else if (inBounds(event.getSceneX(), PLAY_MIN_X, PLAY_MAX_X)) {
					open.execute(); stage.close();
				}
			}
		});

	}

	private boolean inBounds(double x, double min, double max) {
		return x > min && x < max;
	}

}
