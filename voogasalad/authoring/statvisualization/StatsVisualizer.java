package authoring.statvisualization;

import java.util.ArrayList;
import java.util.List;

import database.VoogaDataBase;
import database.VoogaPlaySession;
import database.VoogaStatInfo;

public class StatsVisualizer {
	private VoogaDataBase myDataBase = VoogaDataBase.getInstance();
	
	public void getGameInfoGraphByGame(String gamename, String xparam, String yparam){

	}
	public void getAuthorStatInfoGraphByGame(String gamename, String xparam, String yparam){
		
	}
	public void getPlayStatInfoGraphByGame(String gamename, String xparam, String yparam){
		
	}
	public void getUserInfoByUser(String username, String xparam, String yparam){
		
	}
	public void getAuthorStatInfoByUser(String username, String xparam, String yparam){
		List<VoogaStatInfo> gamestatinfo = myDataBase.getStatsbyUser(username);
		
	}
	public void getPlayStatInfoByUserAndGame(String username, String gamename, String xparam, String yparam){
		List<VoogaPlaySession> playinfo = myDataBase.getStatByGameAndUser(username,gamename).getPlayStats();
		
		List<Object> xparams = new ArrayList<Object>();
		List<Object> yparams = new ArrayList<Object>();
		
		for(VoogaPlaySession info : playinfo){
			xparams.add(info.getProperty(xparam));
			yparams.add(info.getProperty(yparam));
		}
		
		
	}
}
