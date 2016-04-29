package authoring.statvisualization;

import java.util.ArrayList;
import java.util.List;

import database.VoogaEntry;
import javafx.scene.chart.ScatterChart;
import resources.VoogaBundles;

public class StatsVisualizer {
	public ScatterChart<?,?> getVoogaStatsScatterPlot(List<VoogaEntry> xlist, List<VoogaEntry> ylist, String xparam, String yparam){
		
		List<Object> xparams = new ArrayList<Object>();
		List<Object> yparams = new ArrayList<Object>();
		
		for(VoogaEntry entry : xlist){
			xparams.add(entry.getProperty(xparam));
		}
		for(VoogaEntry entry : ylist){
			yparams.add(entry.getProperty(yparam));
		}
		
		String xlabel = VoogaBundles.statsProperties.getProperty(xparam);
		String ylabel = VoogaBundles.statsProperties.getProperty(yparam);
		String title = ylabel + " vs "+ xlabel;
		GraphMaker graphmaker = new GraphMaker();
		return graphmaker.createScatterPlot(title, xlabel, ylabel, xparams, yparams);
	}
}
