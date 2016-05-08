package stats.database;

import tools.VoogaString;

/**
 * Data base contains a list of games. In each game, all the information about the games is saved here.
 * 
 * @author Joshua Xu
 *
 */

public class VoogaGame extends CellEntry{
	
	public static final String GAME_NAME = "game_name";
	public static final String GAME_DESCRIPTION = "game_description";
	/**
	 * VoogaGame Constructor
	 * @param gameName
	 * @param gameDescrip
	 */
	public VoogaGame(String gameName, String gameDescrip){
		super();
		setProperty(GAME_NAME, new VoogaString(gameName));
		setProperty(GAME_DESCRIPTION, new VoogaString(gameDescrip));
	}

	
	/**
	 * Returns the name of the game in string mode so that it can be viewed in the leaderboard.
	 */

	public String toString(){
		return getProperty(GAME_NAME).getValue().toString();
	}
}
