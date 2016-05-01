package stats.visualization;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.chart.ScatterChart;
import resources.VoogaBundles;
import stats.database.CellEntry;
/**
 * Allows visualization of stat parameters from the database
 * @author Krista
 *
 */
public class StatsVisualizer {
	/**
	 * Allows a user to visualize stat's from the database
	 * Takes in two lists of cell entry's and an xparam and 
	 * yparam to be graphed
	 * 
	 * @param xlist
	 * @param ylist
	 * @param xparam
	 * @param yparam
	 * @return
	 */
	public ScatterChart<?,?> getVoogaStatsScatterPlot(List<CellEntry> xlist, List<CellEntry> ylist, String xparam, String yparam){
		
		List<Object> xparams = new ArrayList<>();
		List<Object> yparams = new ArrayList<>();
		
		for(CellEntry entry : xlist){
			xparams.add(entry.getProperty(xparam));
		}
		for(CellEntry entry : ylist){
			yparams.add(entry.getProperty(yparam));
		}
		
		String xlabel = VoogaBundles.statsProperties.getProperty(xparam);
		String ylabel = VoogaBundles.statsProperties.getProperty(yparam);
		String title = ylabel + " vs "+ xlabel;
		GraphMaker graphmaker = new GraphMaker();
		return graphmaker.createScatterPlot(title, xlabel, ylabel, xparams, yparams);
	}
}
