package authoring;

import authoring.resourceutility.ButtonMaker;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class ProjectInitializationPrompt extends Stage {
	
	private static final double SPACING = 10;
	private static final double HEADER_SIZE = 30;
	
	private static final double WINDOW_WIDTH = 400;
	private static final double WINDOW_HEIGHT = 200;
	
	private VBox container;
	private TextField name;
	
	private EventHandler<ActionEvent> e;
	
	public ProjectInitializationPrompt() {
		initializeContainer();
		setTheScene();
	}
	
	public void setProceedEvent(EventHandler<ActionEvent> proceedEvent) {
		this.e = proceedEvent;
		Button proceed = new ButtonMaker().makeButton("OK", e);
		container.getChildren().add(makeRow(proceed));
	}
	
	private void initializeContainer() {
		container = new VBox();
		container.setSpacing(SPACING);
		container.setPadding(new Insets(SPACING));
		name = new TextField();
		container.getChildren().addAll(makeRow(new CustomText("Welcome!", FontWeight.BOLD, HEADER_SIZE)),
									   makeRow(new CustomText("Name your project.", FontWeight.BOLD)),
							  	       makeRow(name));
	}
	
	private void setTheScene() {
		Scene scene = new VoogaScene(container, WINDOW_WIDTH, WINDOW_HEIGHT);
		this.setScene(scene);
	}
	
	private HBox makeRow(Node... nodes) {
		HBox container = new HBox(SPACING);
		container.setAlignment(Pos.CENTER);
		container.getChildren().addAll(nodes);
		return container;
	}

	public String getName() {
		return name.getText();
	}

}
