package authoring.gui;

import authoring.VoogaScene;
import authoring.gui.items.NumberTextField;
import authoring.resourceutility.ButtonMaker;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ResizePrompt {
	
	private MouseEvent event;
	private Stage stage;
	private Pane db;
	
	public ResizePrompt(MouseEvent e) {
		this.event = e;
		this.db = (Pane) this.event.getSource();
		show();
	}
	
	private void show() {
		VBox vbox = new VBox();
		stage = new Stage();
		Scene scene = new VoogaScene(vbox);
		NumberTextField width = new NumberTextField();
		NumberTextField height = new NumberTextField();
		width.setText(Double.toString(db.getWidth()));
		height.setText(Double.toString(db.getHeight()));
		Button apply = new ButtonMaker().makeButton("Apply", e -> apply(width.getText(), height.getText()));
		vbox.getChildren().addAll(width, height, apply);
		stage.setScene(scene);
		stage.show();
	}
	
	private void apply(String width, String height) {
		db.setMaxWidth(Double.parseDouble(width));
		db.setMaxHeight(Double.parseDouble(height));
		stage.close();
	}

}
