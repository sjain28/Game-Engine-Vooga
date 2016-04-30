package authoring.gui;

import java.util.List;
import java.util.stream.Collectors;
import authoring.VoogaScene;
import authoring.model.VoogaFrontEndText;
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
import stats.database.StatCell;
import stats.database.VoogaDataBase;
import stats.database.VoogaGame;
import stats.database.VoogaUser;
import stats.visualization.GraphMaker;

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
        Tab plotMaker = new Tab("Plot Maker");
        plotMaker.setContent(infoOptions());
        ans.getTabs().addAll(plotMaker);
        ans.setPrefWidth(DATA_WIDTH * 3 / 4);
        return ans;
    }

    private VBox infoOptions () {
       VBox scene = new VBox();
       return scene;
    }

    private Node makeLists () {
        Accordion lists = new Accordion();
        TitledPane games = new TitledPane();
        games.setText("Games");
        ListView<String> actualGames = new ListView<String>();
        List<String> authoredGames = database.getStatsbyUser(user.getProperty(user.USER_NAME).toString()).stream().map(e -> e.getProperty(StatCell.MY_GAME).toString()).collect(Collectors.toList());
        actualGames.getItems().setAll(authoredGames);
        actualGames.setOnMouseClicked(e -> clickList(actualGames.getSelectionModel().getSelectedItem()));
        games.setContent(actualGames);
        lists.getPanes().addAll(games);
        lists.setPrefWidth(DATA_WIDTH/4);
        return lists;
    }

    private void clickList (String game) {
        selectedGame = database.getGame(game);
    }
}
