package stats.interaction;

import java.util.Date;

import resources.VoogaBundles;
import stats.database.AuthorSession;
import stats.database.PlaySession;
import stats.database.StatCell;
import stats.database.VoogaDataBase;
import tools.VoogaNumber;

public class CurrentSessionStats {
	private String myCurrentGame;
	private String myCurrentUser;
	private VoogaDataBase myDataBase;
	
	public void startAuthoringSession(){
    	StatCell statcell = getCurrentStatCell();
    	statcell.addAuthoringSession(new AuthorSession(new Date()));
	}
	public void endCurrentAuthoringSession(){
    	StatCell statcell = getCurrentStatCell();
    	statcell.getLatestAuthoringSession().endSession();
	}
	public void startPlaySession(){
		PlaySession playsession = new PlaySession(new Date());
		getCurrentStatCell().addPlaySession(playsession);;
		playsession.startSession();
	}
	public void endCurrentPlaySession(VoogaNumber score, VoogaNumber levelreached){
		PlaySession playsession = getCurrentStatCell().getLatestPlaySession();
		playsession.endSession(score, levelreached);
	}
	public StatCell getCurrentStatCell(){
		setCurrentGameAndUser();
		return  ((StatCell) myDataBase.getStatByGameAndUser(myCurrentGame,myCurrentUser));
	}
	private void setCurrentGameAndUser(){
		myCurrentGame = VoogaBundles.preferences.getProperty("GameName");
		myCurrentUser = VoogaBundles.preferences.getProperty("UserName");
	}
}
