package database;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

import tools.VoogaString;

public class VoogaStatInfo extends VoogaEntry {
	private Stack<VoogaAuthorSession> myAuthorStats;
	private Stack<VoogaPlaySession> myPlayStats;
	public static final String MY_USER = "my_user";
	public static final String MY_GAME = "my_game";
	public static final String LAST_SAVED_LEVEL_LOC = "last_saved_level_loc";
	
	public VoogaStatInfo(String gamename, String username){
		super();
		myAuthorStats = new Stack<VoogaAuthorSession>();
		myPlayStats = new Stack<VoogaPlaySession>();
		setProperty(MY_GAME, new VoogaString(gamename));
		setProperty(MY_USER, new VoogaString(username));
		setProperty(LAST_SAVED_LEVEL_LOC, new VoogaString(""));
	}
	public List<VoogaAuthorSession> getAuthorStats(){
		return Collections.unmodifiableList(myAuthorStats);
	}
	public void addAuthoringSession(VoogaAuthorSession authorsesh){
		myAuthorStats.add(authorsesh);
	}
	public List<VoogaEntry> getPlayStats(){
		return Collections.unmodifiableList(myPlayStats);
	}
	public VoogaEntry getLatestPlaySession(){
		if (myPlayStats.empty()){
			return null;
		}
		return myPlayStats.peek();
	}
	public void addPlaySession(VoogaPlaySession voogaplaysesh){
		myPlayStats.add(voogaplaysesh);
	}
	public String toString(){
		return "Cell";
	}
}
