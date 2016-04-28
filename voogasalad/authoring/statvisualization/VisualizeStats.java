package authoring.statvisualization;

import java.util.ArrayList;
import java.util.List;

import database.VoogaEntry;
import javafx.scene.chart.ScatterChart;

public class VisualizeStats {
	public ScatterChart<?,?> graphVoogaStats(List<VoogaEntry> xlist, List<VoogaEntry> ylist, String xparam, String yparam){
		
		List<Object> xparams = new ArrayList<Object>();
		List<Object> yparams = new ArrayList<Object>();
		
		for(VoogaEntry entry : xlist){
			xparams.add(entry.getProperty(xparam));
		}
		for(VoogaEntry entry : ylist){
			yparams.add(entry.getProperty(yparam));
		}
		
		//should fetch the x and y lable's from a resource file
		GraphMaker graphmaker = new GraphMaker();
		return graphmaker.createScatterPlot("This is my title", xparam, yparam, xparams, yparams);
	}
}
