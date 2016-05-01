package stats.database;

import java.util.Collections;
import java.util.List;
import java.util.Stack;

import tools.VoogaNumber;
import tools.VoogaString;
import tools.interfaces.VoogaData;

public class StatCell extends CellEntry {
	private Stack<AuthorSession> myAuthorStats;
	private Stack<PlaySession> myPlayStats;
	public static final String MY_USER = "my_user";
	public static final String MY_GAME = "my_game";
	public static final String LATEST_PROGRESS = "latest_progress";
	public static final String LAST_SAVED_LEVEL_LOC = "last_saved_level_loc";
	
	public StatCell(String gamename, String username){
		super();
		myAuthorStats = new Stack<>();
		myPlayStats = new Stack<>();
		setProperty(MY_GAME, new VoogaString(gamename));
		setProperty(MY_USER, new VoogaString(username));
		setProperty(LAST_SAVED_LEVEL_LOC, new VoogaString(""));
		setProperty(LATEST_PROGRESS, new VoogaString(""));
	}
	public List<CellEntry> getAuthorStats(){
		return Collections.unmodifiableList(myAuthorStats);
	}
	public List<CellEntry> getPlayStats(){
		return Collections.unmodifiableList(myPlayStats);
	}
	public PlaySession peekLatestPlaySession(){
//		System.out.println(myPlayStats.size());
		if (myPlayStats.empty()){
			return null;
		}
		return myPlayStats.peek();
//		System.out.println()
	}
	public AuthorSession peekLatestAuthoringSession(){
		if (myAuthorStats.empty()){
			return null;
		}
		return myAuthorStats.peek();
	}
	public void addPlaySession(PlaySession voogaplaysesh){
		myPlayStats.push(voogaplaysesh);
	}
	public void addAuthorSession(AuthorSession voogaauthorsesh){
		myAuthorStats.push(voogaauthorsesh);
	}
	public void updateAuthorSession(String param, VoogaData value){
		AuthorSession lastSession = myAuthorStats.pop();
		lastSession.setProperty(param, value);
		myAuthorStats.push(lastSession);
	}
	public void updatePlaySession(String param, VoogaData value){
		PlaySession lastSession = myPlayStats.pop();
		lastSession.setProperty(param, value);
		myPlayStats.push(lastSession);
	}
	public String toString(){
		return "Cell";
	}
	public void updateProgress(String levelName){
		setProperty(LATEST_PROGRESS, new VoogaString(""));
		System.out.println("The levelname here was set to levelName " + levelName);
	}
	public String checkProgress(){
		return (String) ((VoogaString) getProperty(LATEST_PROGRESS)).getValue();
	}
	
}
