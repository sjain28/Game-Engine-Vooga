package authoring.gui;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import authoring.VoogaScene;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import resources.VoogaBundles;
import stats.database.CellEntry;
import stats.database.PlaySession;
import stats.database.StatCell;
import stats.database.VoogaDataBase;
import tools.GUIUtils;
import tools.Pair;
import tools.ScoreCompare;


/**
 * Displays a Top 10 leader board for the scores in each game
 * 
 * @author Nick
 *
 */
public class LeaderBoards extends Stage {
    private static final double WIDTH = 100;
    private static final double HEIGHT = 200;
    private VBox best;
    private VoogaDataBase database = VoogaDataBase.getInstance();
    private String game;

    private int LEADERBOARD_MEMBER_COUNT;
    private String TEXT_COLOR;

    private ResourceBundle leaderboardProperties;

    /**
     * Initializes a new leaderboard, requires the string name of the game which should be accessed
     * from the properties file
     * 
     * @param game
     */
    public LeaderBoards (String game) {
        this.game = game;

        leaderboardProperties = VoogaBundles.leaderboardProperties;
        LEADERBOARD_MEMBER_COUNT =
                Integer.parseInt(leaderboardProperties.getString("LBmemberCount"));
        TEXT_COLOR = leaderboardProperties.getString("TextColor");

        best = new VBox();
        Text t = new Text(leaderboardProperties.getString("HighScores"));
        t.setFont(new Font(30));
        t.setFill(Color.valueOf(TEXT_COLOR));
        best.getChildren().add(t);
        makeLeaders();
        Scene content = new VoogaScene(best);
        best.setPrefSize(WIDTH, HEIGHT);
        this.setScene(content);
        this.show();
    }

    /**
     * Initializes the values on the leader board based on information from the database.
     */
    private void makeLeaders () {
        List<Pair<String, Double>> scores = new ArrayList<>();

        for (CellEntry c : database.getStatsbyGame(game)) {
            for (CellEntry e : ((StatCell) c).getPlayStats()) {
                scores.add(new Pair<String, Double>(e.getProperty(StatCell.MY_USER).getValue()
                        .toString(),
                                                    Double.parseDouble(e
                                                            .getProperty(PlaySession.SCORE)
                                                            .getValue()
                                                            .toString())));
            }
        }
        scores.sort(new ScoreCompare());
        for (int i = 0; i < LEADERBOARD_MEMBER_COUNT; i++) {
            if (scores.get(i) != null) {
                best.getChildren().add(makeHBox(scores.get(i).getFirst(), scores.get(i).getLast()));
            }
        }
    }

    /**
     * HBox to contain the value of the score
     * 
     * @param n
     * @param s
     * @return
     */
    private HBox makeHBox (String n, Double s) {
        HBox ans;
        Text name = new Text(n);
        name.setFill(Color.valueOf(TEXT_COLOR));

        Text score = new Text(s.toString());
        score.setFill(Color.valueOf(TEXT_COLOR));

        ans = GUIUtils.makeRow(name, score);
        ans.setAlignment(Pos.BASELINE_CENTER);
        return ans;
    }
}
