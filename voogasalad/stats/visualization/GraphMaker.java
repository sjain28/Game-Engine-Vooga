package stats.visualization;

import java.util.List;

import javafx.scene.chart.Axis;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
/**
 * Graph Maker to make any sort of scatter plot with two lists of objects
 * @author Krista
 *
 */
public class GraphMaker {
	private Axis xAxis;
	private Axis yAxis;
	private Object xPoint;
	private Object yPoint;
	/**
	 * Creates a scatter plot for any list of x parameters, y parameters
	 * 
	 * @param title
	 * @param xlabel
	 * @param ylabel
	 * @param xparams
	 * @param yparams
	 * @return
	 */
	public ScatterChart<?,?> createScatterPlot(String title, String xlabel, String ylabel, List<Object> xparams, List<Object> yparams){
		//choose x axis type
		if(xparams.get(0) instanceof Number){xAxis = new NumberAxis();}
		else{xAxis = new CategoryAxis();}
		
		//choose y axis type
		if(yparams.get(0) instanceof Number){yAxis = new NumberAxis();}
		else{yAxis = new CategoryAxis();}
		
		//create the chart and get the title, x, and y axis
		final ScatterChart<?,?> sc = new ScatterChart<>(xAxis,yAxis);
		sc.setTitle(title);
		xAxis.setLabel(xlabel);
		yAxis.setLabel(ylabel);
		
		//fill the series with x and y values
		XYChart.Series series = new XYChart.Series();
		for(int i = 0; i < xparams.size(); i++){

			if(xparams.get(i) instanceof Number){ xPoint = Double.parseDouble(xparams.get(i).toString()); }
			else{xPoint = xparams.get(i).toString(); }
			if(yparams.get(i) instanceof Number){ yPoint = Double.parseDouble(yparams.get(i).toString()); }
			else{yPoint = yparams.get(i).toString(); }
			
			series.getData().add(new XYChart.Data(xPoint,yPoint));
		}
		
		sc.getData().addAll(series);
		
		return sc;
	}
}
