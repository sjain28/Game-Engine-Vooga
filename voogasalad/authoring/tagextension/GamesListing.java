package authoring.tagextension;

import java.util.List;
import authoring.CustomText;
import authoring.resourceutility.ButtonMaker;
import authoring.splash.GameRowDisplay;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.FontWeight;


/**
 * The scrolling pane that holds relevant games, listed in order based
 * on the tags defined by the user.
 * 
 * @author adityasrinivasan
 *
 */
public class GamesListing extends ScrollPane {

    /**
     * Constants
     */
    private static final double SPACING = 10;
    private static final double WIDTH = 400;
    private static final String INSTRUCTION = "Please enter a tag and hit ENTER.";

    private VBox gamesList;

    public GamesListing () {
        gamesList = new VBox();
        gamesList.setSpacing(SPACING);
        gamesList.getChildren().addAll(new CustomText(INSTRUCTION, FontWeight.BOLD));
        this.setContent(gamesList);
        this.setPrefWidth(WIDTH);
    }

    /**
     * Populates the scroll pane with clickable buttons corresponding to the levels
     * specified.
     * 
     * @param games: the names of the games to include.
     * @param e: the event to be executed upon clicking on a game.
     */
    public void populateGamesList (List<String> games, EventHandler<ActionEvent> e) {
        gamesList.getChildren().clear();
        ButtonMaker maker = new ButtonMaker();
        int gameIndex = 0;
        for (String game : games) {
            Button button = maker.makeButton(null, e);
            button.setGraphic(new GameRowDisplay(game, gameIndex));
            button.setId(game);
            gamesList.getChildren().add(button);
            gameIndex++;
        }
    }

}
