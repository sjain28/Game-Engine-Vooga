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
	private VoogaDataBase myDataBase;
	
	public CurrentSessionStats(){
		myDataBase = VoogaDataBase.getInstance();
	}
	public void saveGameProgress(String levelurl){
		getCurrentStatCell().setProperty(StatCell.LAST_SAVED_LEVEL_LOC, new VoogaString(levelurl));
		VoogaDataBase.getInstance().getStatByGameAndUser(myCurrentGame, myCurrentUser).getProperty(StatCell.LAST_SAVED_LEVEL_LOC);
	}
	public String loadGameProgress(){
		return (String) ((VoogaString) getCurrentStatCell().getProperty(StatCell.LAST_SAVED_LEVEL_LOC)).getValue();
	}
	public void startAuthoringSession(){
    	StatCell statcell = getCurrentStatCell();
    	statcell.addAuthorSession(new AuthorSession(new Date()));
	}
	public void endCurrentAuthoringSession(){
    	StatCell statcell = getCurrentStatCell();
    	statcell.peekLatestAuthoringSession().endSession();
	}
	public void startPlaySession(){
		PlaySession playsession = new PlaySession(new Date());
		getCurrentStatCell().addPlaySession(playsession);
		playsession.startSession();
	}
	public void endCurrentPlaySession(double score, double myLevelReached){
		StatCell statcell = getCurrentStatCell();
		statcell.updatePlaySession(PlaySession.SCORE, new VoogaNumber(score));
		statcell.updatePlaySession(PlaySession.LEVEL_REACHED, new VoogaNumber(myLevelReached));	
	}
	public StatCell getCurrentStatCell(){
		setCurrentGameAndUser();
		return  ((StatCell) myDataBase.getStatByGameAndUser(myCurrentGame,myCurrentUser));
	}
	private void setCurrentGameAndUser(){
		myCurrentGame = VoogaBundles.preferences.getProperty("GameName");
		myCurrentUser = VoogaBundles.preferences.getProperty("UserName");
		myDataBase.printDataBase();
	}
}
