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
import stats.database.CellEntry;
import stats.database.PlaySession;
import stats.database.StatCell;
import stats.database.VoogaDataBase;
import tools.GUIUtils;
import tools.Pair;
import tools.ScoreCompare;


public class LeaderBoard extends Stage{
    private VBox best;
    private VoogaDataBase database = VoogaDataBase.getInstance();
    private String game;

    /**
     * Initializes a new leaderboard, requires the string name of the game which should be accessed from the properties file
     * @param game
     */
    public LeaderBoard (String game) {
        this.game = game;
        best = new VBox();
        best.getChildren().add(new Text("High Scores"));
        makeLeaders();
        Scene content = new VoogaScene(best);
        this.setScene(content);
        this.show();
    }

    private void makeLeaders () {
        List<Pair<String, Double>> scores = new ArrayList<>();
        
        for(CellEntry c: database.getStatsbyGame(game)){
            for(CellEntry e: ((StatCell) c).getPlayStats()){
            	System.out.println("The cell here is "  + ((StatCell) c).getPlayStats());
            	System.out.println("The e.property thingy here is " + (e.getProperty(StatCell.MY_USER)!=null) );
            	if (e.getProperty(StatCell.MY_USER)!=null){
                
            		scores.add(new Pair<String, Double>(e.getProperty(StatCell.MY_USER).getValue().toString(),
                								    Double.parseDouble(e.getProperty(PlaySession.SCORE)
                								    					.getValue()
                								    					.toString())));
            		System.out.println("The scores here are "+ scores);
            	}
            }
        }
        System.out.println("scores: "+scores);
        scores.sort(new ScoreCompare());
        VoogaDataBase.getInstance().printDataBase();
        System.out.println("The scores here are "+ scores);
        
        for (int i = 0; i < 10; i++) {
            if (scores.get(i) != null) {
                best.getChildren().add(makeHox(scores.get(i).getFirst(), scores.get(i).getLast()));
            }
        }
        
//        for (Pair p:scores){
//        	 best.getChildren().add(makeHox(p.getFirst(), p.getLast()));
//        }
        

    }

    private HBox makeHox (String n, Double s) {
        HBox ans;
        Text name = new Text(n);
        name.setFill(Color.WHITE);

        Text score = new Text(s.toString());
        score.setFill(Color.WHITE);

        ans = GUIUtils.makeRow(name, score);
        ans.setAlignment(Pos.BASELINE_CENTER);
        return ans;
    }

}
