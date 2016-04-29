package stats.visualization;

import java.util.List;

import javafx.scene.Group;
import javafx.scene.chart.Axis;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import tools.VoogaNumber;
import tools.interfaces.VoogaData;

public class GraphMaker {
	private Axis xAxis;
	private Axis yAxis;
	private Object xPoint;
	private Object yPoint;
	//TODO: re-factor so that you only need to check the x/y param types once
	public ScatterChart<?,?> createScatterPlot(String title, String xlabel, String ylabel, List<Object> xparam, List<Object> yparam){
		//choose x axis type
		if(xparam.get(0) instanceof Number){xAxis = new NumberAxis();}
		else{xAxis = new CategoryAxis();}
		
		//choose y axis type
		if(yparam.get(0) instanceof Number){yAxis = new NumberAxis();}
		else{yAxis = new CategoryAxis();}
		
		//create the chart and get the title, x, and y axis
		final ScatterChart<?,?> sc = new ScatterChart<Object,Object>(xAxis,yAxis);
		sc.setTitle(title);
		xAxis.setLabel(xlabel);
		yAxis.setLabel(ylabel);
		
		//fill the series with x and y values
		XYChart.Series series = new XYChart.Series();
		for(int i = 0; i < xparam.size(); i++){
			if(xparam.get(i) instanceof Number){ xPoint = Double.parseDouble(xparam.get(i).toString()); }
			else{xPoint = xparam.get(i).toString(); }
			if(yparam.get(i) instanceof Number){ yPoint = Double.parseDouble(yparam.get(i).toString()); }
			else{yPoint = yparam.get(i).toString(); }
			
			series.getData().add(new XYChart.Data(xPoint,yPoint));
		}
		
		sc.getData().addAll(series);
		
		return sc;
	}
}
