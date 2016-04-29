package stats.database;

import tools.VoogaString;

public class VoogaGame extends CellEntry{
	
	public static final String GAME_NAME = "game_name";
	public static final String GAME_DESCRIPTION = "game_description";
	
	public VoogaGame(String gameName, String gameDescrip){
		super();
		setProperty(GAME_NAME, new VoogaString(gameName));
		setProperty(GAME_DESCRIPTION, new VoogaString(gameDescrip));
	}

	public String toString(){
		return getProperty(GAME_NAME).getValue().toString();
	}
}
