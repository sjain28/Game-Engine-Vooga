package database;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import tools.VoogaString;

public class VoogaStatInfo extends VoogaEntry {
	private List<VoogaAuthorSession> myAuthorStats;
	private List<VoogaPlaySession> myPlayStats;
	public static final String MY_USER = "stat user";
	public static final String MY_GAME = "games stat";
	public static final String LAST_SAVED_LEVEL_LOC = "LastSavedLevelLoc";
	
	public VoogaStatInfo(String gamename, String username){
		super();
		myAuthorStats = new ArrayList<VoogaAuthorSession>();
		myPlayStats = new ArrayList<VoogaPlaySession>();
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
	public List<VoogaPlaySession> getPlayStats(){
		return Collections.unmodifiableList(myPlayStats);
	}
	public void addPlaySession(VoogaPlaySession voogaplaysesh){
		myPlayStats.add(voogaplaysesh);
	}
	public String toString(){
		return "Cell";
	}
}
