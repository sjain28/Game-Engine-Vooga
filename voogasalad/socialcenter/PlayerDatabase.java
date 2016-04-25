package socialcenter;

import java.util.List;

public class PlayerDatabase {

	private List<UserInfo> myPlayerData;
	private List<GameInfo> myGameData;
	private String[][] myPlayerGameData;
	
	public PlayerDatabase(String dataLocation){
		//deserialize the player database;
	}
	
	public String[][] generateGameLeaderBoard(){
		return null;
	}
	
	public String[][] generateUserGameStats(){
		return null;
	}
	
	public void createUserProfile(String playerName){
		UserInfo user = new UserInfo(playerName);
		myPlayerData.add(user);
	}
	
	public void createGameProfile(String gameName){
		GameInfo game = new GameInfo(gameName);
		myGameData.add(game);
	}
	
}
