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

public class FinishLinePrompt extends Stage {
	
	private static final double SPACING = 10;
	
	private VBox container;
	private NumberTextField xCoordinate;
	private NumberTextField yCoordinate;
	
	public FinishLinePrompt() {
		initializeContainers();
		initializeScene();
	}
	
	private void initializeContainers() {
		container = new VBox(SPACING);
		container.setPadding(new Insets(SPACING));
		xCoordinate = new NumberTextField();
		yCoordinate = new NumberTextField();
		container.getChildren().addAll(GUIUtils.makeRow(new CustomText("X: "), xCoordinate),
									   GUIUtils.makeRow(new CustomText("Y: "), yCoordinate));
	}
	
	public void setProceedListener(EventHandler<ActionEvent> e) {
		container.getChildren().add(new ButtonMaker().makeButton("OK", e));
	}
	
	private void initializeScene() {
		this.setScene(new VoogaScene(container));
	}

	public Pair<Double, Double> getCoords() {
		return new Pair<Double, Double>(Double.parseDouble(xCoordinate.getText()), Double.parseDouble(yCoordinate.getText()));
	}

}
