package stats.interaction;

import java.util.Date;

import resources.VoogaBundles;
import stats.database.AuthorSession;
import stats.database.PlaySession;
import stats.database.StatCell;
import stats.database.VoogaDataBase;
import tools.VoogaNumber;
import tools.VoogaString;

public class CurrentSessionStats {
	private String myCurrentGame;
	private String myCurrentUser;
	private VoogaDataBase myDataBase = VoogaDataBase.getInstance();
	
	public void saveGameProgress(String levelurl){
		getCurrentStatCell().setProperty(StatCell.LAST_SAVED_LEVEL_LOC, new VoogaString(levelurl));
	}
	public void getLastSavedLevel(){
		getCurrentStatCell().getProperty(StatCell.LAST_SAVED_LEVEL_LOC);
	}
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
	public void endCurrentPlaySession(double score, double myLevelReached){
		PlaySession playsession = getCurrentStatCell().getLatestPlaySession();
		playsession.endSession(new VoogaNumber(score), new VoogaNumber(myLevelReached));
	}
	public StatCell getCurrentStatCell(){
		setCurrentGameAndUser();
		return  ((StatCell) myDataBase.getStatByGameAndUser(myCurrentGame,myCurrentUser));
	}
	private void setCurrentGameAndUser(){
		myCurrentGame = VoogaBundles.preferences.getProperty("GameName");
		myCurrentUser = VoogaBundles.preferences.getProperty("UserName");
		System.out.println(myCurrentGame +" "+ myCurrentUser);
		myDataBase.printDataBase();
	}
}
