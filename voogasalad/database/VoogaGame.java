package database;

import tools.VoogaString;

public class VoogaGame extends VoogaEntry{
	
	public static final String GAME_NAME = "GameName";
	public static final String GAME_DESCRIPTION = "UserName";
	
	public VoogaGame(String gameName, String gameDescrip){
		super();
		setProperty(GAME_NAME, new VoogaString(gameName));
		setProperty(GAME_DESCRIPTION, new VoogaString(gameDescrip));
	}

	public String toString(){
		return getProperty(GAME_NAME).getValue().toString();
	}
}
