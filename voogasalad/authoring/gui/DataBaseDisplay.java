package authoring.gui;

import authoring.VoogaScene;
import authoring.model.VoogaFrontEndText;
import authoring.statvisualization.GraphMaker;
import database.VoogaDataBase;
import database.VoogaGame;
import database.VoogaUser;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import resources.VoogaBundles;

public class DataBaseDisplay extends Stage {
    private static final double HEADER_HEIGHT = 50;
    private static final double HEADER_WIDTH = 700;
    private static final double DATA_WIDTH = 700;
    private static final double DATA_HEIGHT = 400;
    
    private VoogaDataBase database = VoogaDataBase.getInstance();
    private VoogaUser user;
    private VoogaGame selectedGame;
    
    public DataBaseDisplay(){
        user = database.getUser(VoogaBundles.preferences.getProperty("Username"));
        VBox content = new VBox();
        HBox header = makeHeader();
        HBox data = makeData();
        Scene scene = new VoogaScene(content);
        content.getChildren().addAll(header, data);
        this.setScene(scene);
    }

    private HBox makeHeader () {
        HBox ans = new HBox(20);
        ImageView pict = new ImageView();
        pict.setImage(new Image("file:" + user.getProperty(user.PROF_PIC_LOC)));
        pict.setFitHeight(HEADER_HEIGHT);
        pict.setPreserveRatio(true);
        Text t = new Text(user.getProperty(user.DISPLAY_NAME).toString());
        t.setFill(Color.WHITE);
        t.setFont(Font.font(30));
        ans.getChildren().addAll(pict, t);
        ans.setAlignment(Pos.BASELINE_LEFT);
        ans.setPrefSize(HEADER_WIDTH, HEADER_HEIGHT);
        return ans;
    }

    private HBox makeData () {
        HBox ans = new HBox();
        ans.getChildren().addAll(makeLists(), makeTabs());
        ans.setPrefSize(DATA_WIDTH, DATA_HEIGHT);
        return ans;
    }

    private Node makeTabs () {
        TabPane ans = new TabPane();
        GraphMaker graphMaker = new GraphMaker();
        Tab playsScore = new Tab("Score on Game");
        //TODO: add in playsScore content
        playsScore.setContent(graphMaker.createScatterPlot("Score on Game", "Game" , "Score", database.getStatByGameAndUser(selectedGame.getProperty(selectedGame.GAME_NAME).toString(), user.getProperty(user.USER_NAME).toString())) , yparam));
        Tab playsTime = new Tab("Time Lasted on Game");
        //TODO: add in playsScore content
        Tab highScore = new Tab("High Scores");
        //TODO: add in playsScore content
        ans.getTabs().addAll(playsScore, playsTime, highScore);
        ans.setPrefWidth(DATA_WIDTH * 3 / 4);
        return ans;
    }

    private Node makeLists () {
        Accordion lists = new Accordion();
        TitledPane gamesMade = new TitledPane();
        gamesMade.setText("Games Made");
        ListView<String> made = new ListView<String>();
        made.getItems().setAll(database.getStatsbyUser(user.getProperty(user.USER_NAME).toString()).toString());
        made.setOnMouseClicked(e -> clickList(made.getSelectionModel().getSelectedItem()));
        gamesMade.setContent(made);
        TitledPane gamesPlayed = new TitledPane();
        ListView<String> played = new ListView<String>();
        //THIS IS INCORRECT
        played.getItems().setAll(database.getStatsbyUser(user.getProperty(user.USER_NAME).toString()).toString());
        played.setOnMouseClicked(e -> clickList(made.getSelectionModel().getSelectedItem()));
        gamesPlayed.setText("Games Played");
        gamesPlayed.setContent(played);
        lists.getPanes().addAll(gamesMade, gamesPlayed);
        lists.setPrefWidth(DATA_WIDTH/4);
        return lists;
    }

    private void clickList (String game) {
        selectedGame = database.getGame(game);
    }
}
