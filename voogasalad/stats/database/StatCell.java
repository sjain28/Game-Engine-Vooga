package stats.database;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tools.VoogaString;

public class StatCell extends CellEntry {
	private Stack<AuthorSession> myAuthorStats;
	private Stack<PlaySession> myPlayStats;
	public static final String MY_USER = "my_user";
	public static final String MY_GAME = "my_game";
	public static final String LATEST_PROGRESS = "latest_progress";
	public static final String LAST_SAVED_LEVEL_LOC = "last_saved_level_loc";
	
	public StatCell(String gamename, String username){
		super();
		myAuthorStats = new Stack<AuthorSession>();
		myPlayStats = new Stack<PlaySession>();
		setProperty(MY_GAME, new VoogaString(gamename));
		setProperty(MY_USER, new VoogaString(username));
		setProperty(LAST_SAVED_LEVEL_LOC, new VoogaString(""));
		setProperty(LATEST_PROGRESS, new VoogaString(""));
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
		System.out.println(myPlayStats.size());
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
	public void updateProgress(String levelName){
		setProperty(LATEST_PROGRESS, new VoogaString(""));
		System.out.println("The levelname here was set to levelName " + levelName);
	}
	public String checkProgress(){
		return (String) ((VoogaString) getProperty(LATEST_PROGRESS)).getValue();
	}
	
}
