package authoring.splash;

import authoring.CustomText;
import authoring.VoogaScene;
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

public class ProjectInitializationPrompt extends StarterPrompt {
	
	private static final double WINDOW_WIDTH = 400;
	private static final double WINDOW_HEIGHT = 200;
	
	private VBox container;
	private TextField name;
	private TextField description;
	
	private EventHandler<ActionEvent> e;
	
	public ProjectInitializationPrompt() {
		super();
	}
	
	@Override
	public void setProceedEvent(EventHandler<ActionEvent> proceedEvent) {
		this.e = proceedEvent;
		Button proceed = new ButtonMaker().makeButton("OK", e);
		container.getChildren().add(makeRow(proceed));
	}
	
	@Override
	protected void initializeContainer() {
		container = new VBox();
		container.setSpacing(SPACING);
		container.setPadding(new Insets(SPACING));
		name = new TextField();
		name.setPromptText("E.g. Zombie Apocalypse II");
		description = new TextField();
		description.setPromptText("E.g. Survive against waves of terrifying zombies!");
		container.getChildren().addAll(makeRow(new CustomText("Welcome!", FontWeight.BOLD, HEADER_SIZE)),
									   makeRow(new CustomText("Build a new game.", FontWeight.BOLD)),
							  	       makeRow(new CustomText("Name:"), name),
							  	       makeRow(new CustomText("Description:"), description));
	}
	
	@Override
	protected void setTheScene() {
		Scene scene = new VoogaScene(container, WINDOW_WIDTH, WINDOW_HEIGHT);
		this.setScene(scene);
	}
	
	public String getName() {
		return name.getText();
	}

}
