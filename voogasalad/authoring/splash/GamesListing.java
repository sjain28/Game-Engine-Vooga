package authoring.splash;

import java.io.File;
import java.util.List;

import authoring.resourceutility.ButtonMaker;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class GamesListing extends ScrollPane {
	
	private static final double SPACING = 10;
	private static final double WIDTH = 400;
	
	private VBox gamesList;
	
	public GamesListing() {
		gamesList = new VBox();
		gamesList.setSpacing(SPACING);
		this.setContent(gamesList);
		this.setPrefWidth(WIDTH);
	}

	public void populateGamesList(List<String> games, EventHandler<ActionEvent> e) {
		gamesList.getChildren().clear();
		ButtonMaker maker = new ButtonMaker();
		int gameIndex = 0;
		for(String game : games) {
			Button button = maker.makeButton(null, e);
			button.setGraphic(new GameRowDisplay(game, gameIndex));
			button.setId(game);
			gamesList.getChildren().add(button);
			gameIndex++;
		}
	}

}
