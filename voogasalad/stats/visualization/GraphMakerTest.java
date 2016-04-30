package stats.visualization;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.ScatterChart;
import javafx.stage.Stage;
import stats.database.AuthorSession;
import stats.database.CellEntry;
import stats.database.StatCell;
import stats.database.VoogaDataBase;

public class GraphMakerTest extends Application {
	public void start(Stage stage) {		
		List<Object> xparams = new ArrayList<Object>();
		List<Object> yparams = new ArrayList<Object>();
		
		//fill up the x-params and y-params
		//fillArrays4(xparams,yparams);

		
		GraphMaker statsvisualizer = new GraphMaker();
		StatsVisualizer visualizer = new StatsVisualizer();
		CellEntry statinfo = VoogaDataBase.getInstance().getStatByGameAndUser("test2", "Nick");
		List<CellEntry> list = ((StatCell) statinfo).getAuthorStats();
		//ScatterChart<?,?> sc = visualizer.graphVoogaStats(list, list, VoogaPlaySession.PLAY_DURATION, VoogaPlaySession.SCORE);
		//ScatterChart<?,?> sc = visualizer.getVoogaStatsScatterPlot(list, list, VoogaPlaySession.DATE_PLAYED, VoogaPlaySession.SCORE);
		ScatterChart<?,?> sc = visualizer.getVoogaStatsScatterPlot(list, list, AuthorSession.DATE_AUTHORED, AuthorSession.AUTHOR_DURATION);

		//to graph all the games and their durations
		
		VoogaDataBase database = VoogaDataBase.getInstance();
		
		
		
		Scene scene = new Scene(sc,500,500);
		stage.setScene(scene);
		stage.show();
	}
	/*public void fillArrays1(List<VoogaData> xparams, List<VoogaData> yparams){
		xparams.add(new VoogaString("true"));
		xparams.add(new VoogaString("false"));
		xparams.add(new VoogaString("true"));
		
		yparams.add(new VoogaNumber(1.0));
		yparams.add(new VoogaNumber(2.0));
		yparams.add(new VoogaNumber(3.0));
	}
	public void fillArrays2(List<Object> xparams, List<Object> yparams){
		xparams.add(new VoogaDate(new Date()));
		xparams.add(new VoogaDate(new Date()));
		xparams.add(new VoogaDate(new Date()));
		
		yparams.add(new VoogaNumber(5.0));
		yparams.add(new VoogaNumber(2.0));
		yparams.add(new VoogaNumber(3.0));
	}
	public void fillArrays3(List<Object> xparams, List<Object> yparams){
		xparams.add(new VoogaBoolean(true));
		xparams.add(new VoogaBoolean(true));
		xparams.add(new VoogaBoolean(true));

		yparams.add(new VoogaNumber(5.0));
		yparams.add(new VoogaNumber(2.0));
		yparams.add(new VoogaNumber(3.0));
	}
	//x axis : authoring time, y axis: duration of play
	public void fillArrays4(List<Object> xparams, List<Object> yparams){
		//vooga entry
		VoogaDataBase database = VoogaDataBase.getInstance();
		String xparam = PlaySession.DATE_PLAYED;
		String yparam = PlaySession.PLAY_DURATION;
		
		String username = "klo14";
		String gamename = "game 2";
		
		CellEntry statinfo = database.getStatByGameAndUser(gamename, username);
		List<CellEntry> list = ((StatCell) statinfo).getPlayStats();
		
		for(CellEntry entry : list){
			xparams.add(entry.getProperty(xparam));
			yparams.add(entry.getProperty(yparam));
		}
	}*/
	public static void main(String[] args){
		launch(args);
	}

}
