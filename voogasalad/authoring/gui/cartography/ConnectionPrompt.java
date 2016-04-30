package authoring.gui.cartography;

import java.util.List;

import org.codehaus.groovy.runtime.ArrayUtil;

import authoring.CustomText;
import authoring.VoogaScene;
import authoring.interfaces.model.CompleteAuthoringModelable;
import authoring.resourceutility.ButtonMaker;
import events.Cause;
import groovy.json.internal.ArrayUtils;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import tools.GUIUtils;

public class ConnectionPrompt extends Stage {

	private static final double SPACING = 10;
	private static final String NO_LEVEL_SELECTED = "Please select a valid level.";

	private VBox container;
	private VBox causeList;

	public ConnectionPrompt(String start, String end, CompleteAuthoringModelable model) {
		String startLevel = (start == null) ? NO_LEVEL_SELECTED : start;
		String endLevel = (end == null) ? NO_LEVEL_SELECTED : end;
		causeList = new VBox();
		LevelCauseWindow levelCause = new LevelCauseWindow(model, endLevel);
		Button addCond = new ButtonMaker().makeButton("Add Condition", e -> {
			levelCause.showAndWait();
			List<Cause> causes = levelCause.getCauseDetails();
			for(Cause c : causes) {
				causeList.getChildren().add(new CustomText(c.toString()));
			}
		});
		addCond.setDisable(start == null || end == null);
		container = new VBox();
		container.setAlignment(Pos.CENTER);
		container.getChildren().addAll(GUIUtils.makeRow(new CustomText("Start:", FontWeight.BOLD), new CustomText(startLevel)),
				GUIUtils.makeRow(new CustomText("End:", FontWeight.BOLD), new CustomText(endLevel)), GUIUtils.makeRow(addCond),
				causeList);
		Scene scene = new VoogaScene(container, 300, 300);
		this.setScene(scene);
		this.show();
	}

}
