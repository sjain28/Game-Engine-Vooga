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
	
	/**
	 * CurrentSessionStats Constructor
	 */
	public CurrentSessionStats(){
		myDataBase = VoogaDataBase.getInstance();
	}
	/**
	 * Saves the progress of a level for the current game
	 * @param levelurl
	 */
	public void saveGameProgress(String levelurl){
		getCurrentStatCell().setProperty(StatCell.LAST_SAVED_LEVEL_LOC, new VoogaString(levelurl));
	}
	/**
	 * Returns the last saved level from the current game
	 */
	public void getLastSavedLevel(){
		getCurrentStatCell().getProperty(StatCell.LAST_SAVED_LEVEL_LOC);
	}
	/**
	 * Initializes and begins an authoring session in the database
	 * for the current game
	 */
	public void startAuthoringSession(){
    	StatCell statcell = getCurrentStatCell();
    	statcell.addAuthoringSession(new AuthorSession(new Date()));
	}
	/**
	 * Ends the authoring session of a current game
	 */
	public void endCurrentAuthoringSession(){
    	StatCell statcell = getCurrentStatCell();
    	statcell.getLatestAuthoringSession().endSession();
	}
	/**
	 * Initializes and begins the play session of the current game
	 */
	public void startPlaySession(){
		PlaySession playsession = new PlaySession(new Date());
		getCurrentStatCell().addPlaySession(playsession);
		playsession.startSession();
	}
	/**
	 * Ends the current play session, inputting the score and level reached
	 * @param score
	 * @param myLevelReached
	 */
	public void endCurrentPlaySession(double score, double myLevelReached){
		PlaySession playsession = getCurrentStatCell().getLatestPlaySession();
		playsession.endSession(new VoogaNumber(score), new VoogaNumber(myLevelReached));
	}
	/**
	 * Returns the StatCell belonging to the current game and user
	 * @return
	 */
	public StatCell getCurrentStatCell(){
		setCurrentGameAndUser();
		return  ((StatCell) myDataBase.getStatByGameAndUser(myCurrentGame,myCurrentUser));
	}
	/**
	 * Sets the current game and user
	 */
	private void setCurrentGameAndUser(){
		myCurrentGame = VoogaBundles.preferences.getProperty("GameName");
		myCurrentUser = VoogaBundles.preferences.getProperty("UserName");
	}
}
