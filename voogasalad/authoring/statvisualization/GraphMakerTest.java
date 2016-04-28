package authoring.statvisualization;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import javafx.application.Application;

import javafx.scene.Scene;
import javafx.scene.chart.ScatterChart;
import javafx.stage.Stage;
import tools.VoogaBoolean;
import tools.VoogaDate;
import tools.VoogaNumber;
import tools.VoogaString;
import tools.interfaces.VoogaData;

public class GraphMakerTest extends Application {
	public void start(Stage stage) {
		List<Object> xparams = new ArrayList<Object>();
		List<Object> yparams = new ArrayList<Object>();
		
		//fill up the x-params and y-params
		fillArrays2(xparams,yparams);

		
		GraphMaker statsvisualizer = new GraphMaker();
		ScatterChart<?,?> sc = statsvisualizer.createScatterPlot("tester plot", "xparam", "yparam", xparams, yparams);

		Scene scene = new Scene(sc,500,500);
		stage.setScene(scene);
		stage.show();
	}
	public void fillArrays1(List<VoogaData> xparams, List<VoogaData> yparams){
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
	public static void main(String[] args){
		launch(args);
	}

}
