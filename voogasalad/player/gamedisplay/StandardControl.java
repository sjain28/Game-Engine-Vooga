package player.gamedisplay;

import java.util.ResourceBundle;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import player.gamerunner.IGameRunner;
import resources.VoogaBundles;

/**
 * Class that generates a set of controls that becomes a component of StandardDisplay
 * Speed control, volume muting, etc.
 * 
 * @author Hunter Lee
 *
 */
public class StandardControl implements IControl {

	private ResourceBundle gameDisplayProperties;
	private IGameRunner myGameRunner;
	private HBox myControl;
	private static final String PAUSE_KEY = "Pause";
	private static final String START_KEY = "Start";
	private static final String SPEED_UP_KEY = "SpeedUp";
	private static final String SLOW_DOWN_KEY = "SlowDown";
	private static final String PlAYNEXTLEVEL_BUTTON_KEY = "PlayNextLevel";
	private static final String REPLAY_BUTTON_KEY = "ReplayLevel";
	private static final int TOP_PADDING = 15;
	private static final int LEFT_PADDING = 12;
	private static final int RIGHT_PADDING = 15;
	private static final int BOTTOM_PADDING = 12;
	private static final int SPACING = 10;
	/**
	 * Default constructor
	 * 
	 */
	public StandardControl() {
		myControl = new HBox();
		gameDisplayProperties = VoogaBundles.GameDisplayProperties;
	}

	/**
	 * Overloaded constructor that takes in a reference to GameRunner
	 * to enable StandardControl to adjust framerate
	 * 
	 */
	public StandardControl(IGameRunner gamerunner) {
		this();
		this.myGameRunner = gamerunner;
	}

	/**
	 * Creates the control panel using HBox
	 * 
	 * @return myControl
	 */
	public HBox createControl() {
		// myControlBox
		myControl.setPadding(new Insets(TOP_PADDING,RIGHT_PADDING, BOTTOM_PADDING,LEFT_PADDING)); //TODO: Magic number
		myControl.setSpacing(SPACING); //TODO: Magic number

		// Buttons
		Button pause = createButton(gameDisplayProperties.getString(PAUSE_KEY));
		Button start = createButton(gameDisplayProperties.getString(START_KEY));
		Button speedUp = createButton(gameDisplayProperties.getString(SPEED_UP_KEY)); //TODO: ResourceBundle
		Button speedDown = createButton(gameDisplayProperties.getString(SLOW_DOWN_KEY));

//		//Add button here for changing levels
//		Button replayButton = createButton(gameDisplayProperties.getString(REPLAY_BUTTON_KEY));
//		
		Button playNextButton = createButton(gameDisplayProperties.getString(PlAYNEXTLEVEL_BUTTON_KEY));
		
		// TODO: Assign click actions
		pause.setOnAction(e -> getGameRunner().stop());
		start.setOnAction(e -> getGameRunner().start());
		speedUp.setOnMouseClicked(e -> getGameRunner().speedUp());
		speedDown.setOnMouseClicked(e -> getGameRunner().speedDown());
//		replayButton.setOnMouseClicked(e -> getGameRunner().replayLevel());
		playNextButton.setOnMouseClicked(e -> getGameRunner().playNextLevel());
		myControl.getChildren().addAll(start, pause, speedUp, speedDown, playNextButton);

		return myControl;
	}

	/**
	 * Private method used to create a Button
	 * 
	 * @param name
	 * @return
	 */
	private Button createButton(String name) {
		Button button = new Button();
		button.setText(name);
		return button;
	}

	/**
	 * @return the myGameRunner
	 */
	public IGameRunner getGameRunner() {
		return myGameRunner;
	}

}
