package stats.database;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import data.Deserializer;
import data.Serializer;
import tools.VoogaException;
/**
 * Data base holds all relevant info about Games, Users, and 
 * Stats about interactions between the Games and the Users
 * 
 * @author Krista
 *
 */
public class VoogaDataBase implements IDataBase{
	private static final String FILE_LOCATION = "DataBase.xml";
	private List<VoogaGame> myGames;
	private List<VoogaUser> myUsers;
	private static VoogaDataBase myInstance;
	int totalrows;
	int totalcols;
	List<List<CellEntry>> myStatInfo;
	/**
	 * Private DataBase Constructor
	 */
	private VoogaDataBase(){
		load();
	}
	/**
	 * Singleton method to getInstance of DataBase
	 * @return
	 */
	public static VoogaDataBase getInstance( ) {
		if(myInstance==null){new VoogaDataBase();}
		return myInstance;
	}
	/**
	 * Returns the Game specified by a GameName
	 * @param gamename
	 * @return
	 */
	public VoogaGame getGame(String gamename){
		for(VoogaGame game : myGames){
			if(game.getProperty(VoogaGame.GAME_NAME).toString().equals(gamename)){
				return game;
			}
		}
		return null;
	}
	/**
	 * Adds a new game to the DataBase
	 * @param gamename
	 */
	public void addGame(String gamename, String gamedescrip){
		if(getGame(gamename)==null){
			totalrows++;
			//initialize game
			myGames.add(new VoogaGame(gamename, gamedescrip));
			//initialize stat-info for game for every user
			for(int col = 0; col < totalcols; col++){
				String username = myUsers.get(col).getProperty(VoogaUser.USER_NAME).toString();
				myStatInfo.get(col).add(new StatCell(gamename,username));
			}
			System.out.println("What is my game here, this can only be shown if a game was added 2 list " + myGames);
		}
	}
	/**
	 * Returns the User specified by UserName
	 * @param username
	 * @return
	 */
	public VoogaUser getUser(String username){
		for(VoogaUser user : myUsers){
			if(user.getProperty(VoogaUser.USER_NAME).toString().equals(username)){
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
	public void addUser(String displayname, String username, String password, String profpiclocation){
		System.out.println("in user");
		if(getUser(username)==null){
			System.out.println("actually adding in the user");
			totalcols++;
			myUsers.add(new VoogaUser(displayname, username, password, profpiclocation));
			List<CellEntry> userstatinfo = new ArrayList<CellEntry>();
			for(int row = 0; row < totalrows; row++){
				String gamename = myGames.get(row).getProperty(VoogaGame.GAME_NAME).toString();
				userstatinfo.add(row, new StatCell(gamename,username));
			}
			myStatInfo.add(userstatinfo);
		}
	}
	/**
	 * Returns StatInfo block for a particular Game and User
	 * @param gamename
	 * @param username
	 * @return
	 */
	public CellEntry getStatByGameAndUser(String gamename, String username){
		this.printDataBase();
		int row = myGames.indexOf(getGame(gamename));
		int col = myUsers.indexOf(getUser(username));
		System.out.println("the row here is " + row + "and the column is" + col);
		return myStatInfo.get(col).get(row);
	}
	/**
	 * Returns the List of StatInfo for a particular Game
	 * @param gamename
	 * @return
	 */
	public List<CellEntry> getStatsbyGame(String gamename){
		List<CellEntry> gamestats = new ArrayList<CellEntry>();
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
	public List<CellEntry> getStatsbyUser(String username){
		int col = myUsers.indexOf(getUser(username));
		return myStatInfo.get(col);
	}
	/**
	 * DeSerializes all information
	 */
	private void load(){
		if(!(new File(FILE_LOCATION)).exists()){
			System.out.println("loading, and file location does not yet exist.");
			totalrows = 0;
			totalcols = 0;
			myGames = new ArrayList<VoogaGame>();
			myUsers = new ArrayList<VoogaUser>();
			myStatInfo = new ArrayList<List<CellEntry>>();
			save();
		}
		try {
			List<Object> objects = Deserializer.deserialize(1, FILE_LOCATION);
			if(objects.get(0) instanceof VoogaDataBase){
				myInstance = (VoogaDataBase) objects.get(0);
			}
		} catch (VoogaException e) {e.printStackTrace();}
	}
	/**
	 * Verifies a username in password
	 */
	public boolean verifyLoginInfo(String username, String password){
		if(getUser(username)!=null&&getUser(username).verifyPassword(password)){return true;}
		return false;
	}
	/**
	 * Saves all information
	 */
	public void save(){
		try {Serializer.serialize(this, FILE_LOCATION);}
		catch (ParserConfigurationException | TransformerException | IOException | SAXException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Clears the database
	 */
	public void clear(){
		myGames.clear();
		myUsers.clear();
		totalrows = 0;
		totalcols = 0;
		myStatInfo.clear();
		save();
	}
	/**
	 * Method to print out all data in data base in matrix form
	 * used for debugging properties
	 */
	public void printDataBase(){
		System.out.print("\t");
		for(int c = 0; c < myStatInfo.size(); c++){
			System.out.print(myUsers.get(c)+"\t");
		}
		for(int r = 0; r < myStatInfo.get(0).size(); r++)
		{
			System.out.println(" ");
			System.out.print(myGames.get(r)+"\t");
			for(int c = 0; c < myStatInfo.size(); c++){
				System.out.print(myStatInfo.get(c).get(r)+"\t");
			}
		}
		System.out.println("");
	}
}
