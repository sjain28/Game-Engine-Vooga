package authoring.gui.levelpreferences;

import authoring.CustomText;
import authoring.VoogaScene;
import authoring.gui.items.NumberTextField;
import javafx.geometry.Insets;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import tools.GUIUtils;

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
	
//	public void setProceedListener(EventHandler<ActionEvent> e) {
//		
//	}
	
	private void initializeScene() {
		this.setScene(new VoogaScene(container));
	}

}
