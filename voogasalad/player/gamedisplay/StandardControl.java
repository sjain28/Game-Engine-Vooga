package player.gamedisplay;

import java.util.ResourceBundle;

import authoring.resourceutility.ButtonMaker;
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

	private static final String PAUSE_KEY = "Pause";
	private static final String START_KEY = "Start";
	private static final String SPEED_UP_KEY = "SpeedUp";
	private static final String SLOW_DOWN_KEY = "SlowDown";
	private static final String SNAPSHOT_KEY = "Snapshot";
	private static final int TOP_PADDING = 15;
	private static final int LEFT_PADDING = 12;
	private static final int RIGHT_PADDING = 15;
	private static final int BOTTOM_PADDING = 12;
	private static final int SPACING = 10;
	
	private ResourceBundle gameDisplayProperties;
	private IGameRunner myGameRunner;
	private HBox myControl;

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
	 * to enable StandardControl to adjust frame rate
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
		myControl.setPadding(new Insets(TOP_PADDING,RIGHT_PADDING, BOTTOM_PADDING, LEFT_PADDING));
		myControl.setSpacing(SPACING);
		ButtonMaker maker = new ButtonMaker();
		// Buttons
		Button pause = maker.makeButton((gameDisplayProperties.getString(PAUSE_KEY)), e -> getGameRunner().getTimeline().pause());
		Button start = maker.makeButton((gameDisplayProperties.getString(START_KEY)), e -> getGameRunner().getTimeline().play());
		Button speedUp = maker.makeButton((gameDisplayProperties.getString(SPEED_UP_KEY)), e -> getGameRunner().speedUp());
		Button speedDown = maker.makeButton((gameDisplayProperties.getString(SLOW_DOWN_KEY)), e -> getGameRunner().speedDown());
		Button snapshot = maker.makeButton((gameDisplayProperties.getString(SNAPSHOT_KEY)), e -> getGameRunner().takeSnapShot());
		myControl.getChildren().addAll(start, pause, speedUp, speedDown,snapshot);
		return myControl;
	}

	/**
	 * @return the myGameRunner
	 */
	public IGameRunner getGameRunner() {
		return myGameRunner;
	}

}
