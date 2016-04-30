package authoring.gui;

import java.util.ArrayList;
import java.util.List;

import authoring.VoogaScene;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import stats.database.PlaySession;
import stats.database.StatCell;
import stats.database.VoogaDataBase;
import tools.Pair;
import tools.ScoreCompare;

public class LeaderBoards extends Stage{
    private VBox best;
    private VoogaDataBase database = VoogaDataBase.getInstance();
    private String game;

    public LeaderBoards (String game) {
        this.game = game;
        best = new VBox();
        best.getChildren().add(new Text("High Scores"));
        makeLeaders();
        Scene content = new VoogaScene(best);    
        this.setScene(content);
        this.show();
    }

    private void makeLeaders () {
       List<Pair<String, Double>> scores = new ArrayList<Pair<String, Double>>();
       database.getStatsbyGame(game).stream().forEach( e -> ((StatCell) e).getPlayStats()
                                                       .stream().forEach(ee -> scores.add( new Pair<String, Double>(
                                                             ee.getProperty(StatCell.MY_USER).getValue().toString(),
                                                             Double.parseDouble(ee.getProperty(PlaySession.SCORE).getValue().toString())))));
       scores.sort(new ScoreCompare());        
       for(int i = 0; i < 10; i++){
           if(scores.get(i) != null){
               best.getChildren().add(makeHox(scores.get(i).getFirst(), scores.get(i).getLast()));
           }
       }
       
    }

    private HBox makeHox (String n, Double s) {
        HBox ans = new HBox(20);
        Text name = new Text(n);
        name.setFill(Color.WHITE);
        
        Text score = new Text(s.toString());
        score.setFill(Color.WHITE);
        
        ans.getChildren().addAll(name, score);
        ans.setAlignment(Pos.BASELINE_CENTER);
        return ans;
    }

}
