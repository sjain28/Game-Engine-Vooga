package stats.database;

import java.util.Collections;
import java.util.List;
import java.util.Stack;

import tools.VoogaString;

public class StatCell extends CellEntry {
	private Stack<AuthorSession> myAuthorStats;
	private Stack<PlaySession> myPlayStats;
	public static final String MY_USER = "my_user";
	public static final String MY_GAME = "my_game";
	public static final String LAST_SAVED_LEVEL_LOC = "last_saved_level_loc";
	
	public StatCell(String gamename, String username){
		super();
		myAuthorStats = new Stack<AuthorSession>();
		myPlayStats = new Stack<PlaySession>();
		setProperty(MY_GAME, new VoogaString(gamename));
		setProperty(MY_USER, new VoogaString(username));
		setProperty(LAST_SAVED_LEVEL_LOC, new VoogaString(""));
	}
	public List<CellEntry> getAuthorStats(){
		return Collections.unmodifiableList(myAuthorStats);
	}
	public void addAuthoringSession(AuthorSession authorsesh){
		System.out.println("adding authoring session: "+myAuthorStats.size());
		myAuthorStats.add(authorsesh);
	}
	public List<CellEntry> getPlayStats(){
		return Collections.unmodifiableList(myPlayStats);
	}
	public PlaySession getLatestPlaySession(){
		if (myPlayStats.empty()){
			return null;
		}
		return myPlayStats.peek();
	}
	public AuthorSession getLatestAuthoringSession(){
		if (myAuthorStats.empty()){
			return null;
		}
		return myAuthorStats.peek();
	}
	public void addPlaySession(PlaySession voogaplaysesh){
		myPlayStats.add(voogaplaysesh);
	}
	public String toString(){
		return "Cell";
	}
}
