package authoring.gui.cartography;

import authoring.CustomText;
import authoring.VoogaScene;
import authoring.gui.eventpane.CauseTitledPane;
import authoring.interfaces.model.CompleteAuthoringModelable;
import authoring.resourceutility.ButtonMaker;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class ConnectionPrompt extends Stage {

	private static final double SPACING = 10;
	private static final String NO_LEVEL_SELECTED = "Please select a valid level.";
	
	private VBox container;
	
	public ConnectionPrompt(String start, String end, CompleteAuthoringModelable model) {
		String startLevel = (start == null) ? NO_LEVEL_SELECTED : start;
		String endLevel = (end == null) ? NO_LEVEL_SELECTED : end;
		Button addCond = new ButtonMaker().makeButton("Add Condition", e -> new LevelCauseWindow(model));
		addCond.setDisable(start == null || end == null);
		container = new VBox();
		container.getChildren().addAll(makeRow(new CustomText("Start:", FontWeight.BOLD), new CustomText(startLevel)),
									   makeRow(new CustomText("End:"  , FontWeight.BOLD), new CustomText(endLevel)),
									   addCond);
		Scene scene = new VoogaScene(container);
		this.setScene(scene);
		this.show();
	}
	
	private HBox makeRow(Node... nodes) {
		HBox row = new HBox(SPACING);
		row.getChildren().addAll(nodes);
		return row;
	}

}
