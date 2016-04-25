package authoring.splash;

import java.io.File;

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
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class ProjectOpenPrompt extends StarterPrompt {
	
	private static final double WINDOW_WIDTH = 600;
	private static final double WINDOW_HEIGHT = 300;
	
	private static final double WIDTH = 400;
	
	private static final String GAMES_FOLDER_PATH = "games";
	
	private VBox container;
	private ScrollPane scrollPane;
	private VBox gamesList;
	
	private EventHandler<ActionEvent> e;
	
	public ProjectOpenPrompt() {
		super();
	}
	
	@Override
	public void setProceedEvent(EventHandler<ActionEvent> proceedEvent) {
		this.e = proceedEvent;
		populateGamesList();
	}
	
	@Override
	protected void initializeContainer() {
		container = new VBox();
		container.setSpacing(SPACING);
		container.setPadding(new Insets(SPACING));
		gamesList = new VBox();
		gamesList.setSpacing(SPACING);
		container.setPadding(new Insets(SPACING));
		scrollPane = new ScrollPane();
		scrollPane.setContent(gamesList);
		scrollPane.setPrefWidth(WIDTH);
		container.getChildren().addAll(makeRow(new CustomText("Welcome back!", FontWeight.BOLD, HEADER_SIZE)),
									   makeRow(new CustomText("Load a game.", FontWeight.BOLD)),
							  	       makeRow(scrollPane));
	}
	
	private void populateGamesList() {
		ButtonMaker maker = new ButtonMaker();
		File gamesFolder = new File(GAMES_FOLDER_PATH);
		int gameIndex = 0;
		for(File game : gamesFolder.listFiles()) {
			Button button = maker.makeButton(null, this.e);
			button.setGraphic(new GameRowDisplay(game.getName(), gameIndex));
			button.setId(game.getName());
			gamesList.getChildren().add(button);
			gameIndex++;
		}
	}
	
	@Override
	protected void setTheScene() {
		Scene scene = new VoogaScene(container, WINDOW_WIDTH, WINDOW_HEIGHT);
		this.setScene(scene);
	}

}
