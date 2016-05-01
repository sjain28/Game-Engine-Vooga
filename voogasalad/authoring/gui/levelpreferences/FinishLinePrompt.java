package authoring.gui.levelpreferences;

import authoring.CustomText;
import authoring.VoogaScene;
import authoring.gui.items.NumberTextField;
import authoring.resourceutility.ButtonMaker;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import tools.GUIUtils;
import tools.Pair;

/**
 * Prompt class that handles the finish line.
 * 
 * @author Harry Guo, Aditya Srinivasan, Arjun Desai, Nick Lockett
 *
 */
public class FinishLinePrompt extends Stage {

	/**
	 * Private instance variables
	 */
	private static final double SPACING = 10;

	private VBox container;
	private NumberTextField xCoordinate;
	private NumberTextField yCoordinate;

	/**
	 * Initialization
	 */
	public FinishLinePrompt() {
		initializeContainers();
		initializeScene();
	}

	/**
	 * Initializes the containers.
	 */
	private void initializeContainers() {
		container = new VBox(SPACING);
		container.setPadding(new Insets(SPACING));
		xCoordinate = new NumberTextField();
		yCoordinate = new NumberTextField();
		container.getChildren().addAll(GUIUtils.makeRow(new CustomText("X: "), xCoordinate),
				GUIUtils.makeRow(new CustomText("Y: "), yCoordinate));
	}

	/**
	 * Creates the OK button.
	 * @param e
	 */
	public void setProceedListener(EventHandler<ActionEvent> e) {
		container.getChildren().add(new ButtonMaker().makeButton("OK", e));
	}

	/**
	 * Initializes the scene.
	 */
	private void initializeScene() {
		this.setScene(new VoogaScene(container));
	}

	/**
	 * Gets the coordinates of the finish coordinates.
	 * @return
	 */
	public Pair<Double, Double> getCoords() {
		return new Pair<>(Double.parseDouble(xCoordinate.getText()), Double.parseDouble(yCoordinate.getText()));
	}

}
