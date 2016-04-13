package player.gamedisplay;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import player.gamerunner.IGameRunner;

/**
 * Class that generates a set of controls that becomes a component of StandardDisplay
 * Speed control, volume muting, etc.
 * 
 * @author Hunter Lee
 *
 */
public class StandardControl implements IControl {

	private IGameRunner myGameRunner;
	private HBox myControl;

	/**
	 * Default constructor
	 * 
	 */
	public StandardControl() {
		myControl = new HBox();
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
		myControl.setPadding(new Insets(15, 12, 15, 12)); //TODO: Magic number
		myControl.setSpacing(10); //TODO: Magic number

		// Buttons
		Button speedUp = createButton("Speed Up"); //TODO: ResourceBundle
		Button speedDown = createButton("Speed Down");

		// TODO: Assign click actions
		speedUp.setOnMouseClicked(e -> getGameRunner().speedUp());
		speedDown.setOnMouseClicked(e -> getGameRunner().speedDown());

		myControl.getChildren().addAll(speedUp, speedDown);

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
