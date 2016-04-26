package database;

import java.util.ArrayList;
import java.util.List;

public class VoogaDataBase implements IDataBase{
	private List<VoogaGame> myGames;
	private List<VoogaUser> myUsers;
	int totalrows;
	int totalcols;
	List<List<VoogaStatInfo>> myStatInfo;
	/**
	 * DataBase constructor
	 */
	public VoogaDataBase(){
		totalrows = 0;
		totalcols = 0;
		myGames = new ArrayList<VoogaGame>();
		myUsers = new ArrayList<VoogaUser>();
		myStatInfo = new ArrayList<List<VoogaStatInfo>>();
	}
	/**
	 * Returns the Game specified by a GameName
	 * @param gamename
	 * @return
	 */
	public VoogaGame getGame(String gamename){
		for(VoogaGame game : myGames){
			if(game.getProperty("name").equals(gamename)){
				return game;
			}
		}
		return null;
	}
	/**
	 * Adds a new game to the DataBase
	 * @param gamename
	 */
	public void addGame(String gamename){
		totalrows++;
		//initialize game
		myGames.add(new VoogaGame(gamename));
		//initialize stat-info for game for every user
		for(int col = 0; col < totalcols; col++){
			myStatInfo.get(col).add(new VoogaStatInfo());
		}
	}
	/**
	 * Returns the User specified by UserName
	 * @param username
	 * @return
	 */
	public VoogaUser getUser(String username){
		for(VoogaUser user : myUsers){
			if(user.getProperty("name").equals(username)){
				return user;
			}
		}
		return null;
	}
	/**
	 * Adds user to the DataBase
	 * @param displayname
	 * @param username
	 * @param password
	 */
	public void addUser(String displayname, String username, String password, String profPicLocation){
		totalcols++;
		myUsers.add(new VoogaUser(displayname, username, password, profPicLocation));
		//initialize StatInfo for user for every game
		List<VoogaStatInfo> userstatinfo = new ArrayList<VoogaStatInfo>();
		for(int row = 0; row < totalrows; row++){
			userstatinfo.add(row, new VoogaStatInfo());
		}
		//append user StatInfo to the end column of the data base
		myStatInfo.add(userstatinfo);
	}
	/**
	 * Returns StatInfo block for a particular Game and User
	 * @param gamename
	 * @param username
	 * @return
	 */
	public VoogaStatInfo getStatByGameAndUser(String gamename, String username){
		int row = myGames.indexOf(getGame(gamename));
		int col = myUsers.indexOf(getUser(username));
		return myStatInfo.get(col).get(row);
	}
	/**
	 * Returns the List of StatInfo for a particular Game
	 * @param gamename
	 * @return
	 */
	public List<VoogaStatInfo> getStatsbyGame(String gamename){
		List<VoogaStatInfo> gamestats = new ArrayList<VoogaStatInfo>();
		int row = myGames.indexOf(getGame(gamename));
		for(int col = 0; col < myStatInfo.size(); col++){
			gamestats.add(col, myStatInfo.get(col).get(row));
		}
		return gamestats;
	}
	/**
	 * Returns the List of StatInfo for a particular User
	 * @param username
	 * @return
	 */
	public List<VoogaStatInfo> getStatsbyUser(String username){
		int col = myUsers.indexOf(getUser(username));
		return myStatInfo.get(col);
	}
	public void printDataBase(){
		for(int c = 0; c < myStatInfo.size(); c++){
			//print USER 1 USER 2 USER 3 across top
			System.out.print(myUsers.get(c)+" ");
		}
		
		for(int r = 0; r < myStatInfo.get(0).size(); r++)
		{
			System.out.println(" ");
			System.out.print(myGames.get(r)+" ");
			
			for(int c = 0; c < myStatInfo.size(); c++){
				
				System.out.print(myStatInfo.get(c).get(r));
				
			}
		}
	}
}
