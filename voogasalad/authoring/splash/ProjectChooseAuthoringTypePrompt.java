package authoring.splash;

import authoring.VoogaScene;
import authoring.resourceutility.ButtonMaker;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class ProjectChooseAuthoringTypePrompt extends StarterPrompt {
	
	private static final double WIDTH = 400;
	private static final double HEIGHT = 200;
	private static final double SPACING = 10;
	
	private Button newGame;
	private Button openGame;
	private VBox container;
	
	/**
	 * Choose to start a new game or load one screen 
	 */
	public ProjectChooseAuthoringTypePrompt() {
		super();
	}
	
	@Override
	protected void setProceedEvent(EventHandler<ActionEvent> proceedEvent) {
		//TODO: name IDs the same as the classname and then use reflection in openCommand
		newGame = new ButtonMaker().makeButton("Start a new game", proceedEvent);
		newGame.setId("NewGame");
		openGame = new ButtonMaker().makeButton("Open an existing game", proceedEvent);
		openGame.setId("OpenGame");
		container.getChildren().addAll(newGame, openGame);
	}
	
	@Override
	protected void initializeContainer() {
		container = new VBox();
		container.setSpacing(SPACING);
		container.setAlignment(Pos.CENTER);
	}
	
	@Override
	protected void setTheScene() {
		Scene scene = new VoogaScene(container, WIDTH, HEIGHT);
		this.setScene(scene);
	}

}
