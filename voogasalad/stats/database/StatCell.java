package stats.database;

import java.util.Collections;
import java.util.List;
import java.util.Stack;

import tools.VoogaString;
/**
 * Stat cell that stores a list of author stats and play stats
 * for each authoring and playing session for a given game and user
 * Also stores pertinent information that pertains to the intersection
 * of a game and user
 * @author Krista
 *
 */
public class StatCell extends CellEntry {
	private Stack<AuthorSession> myAuthorStats;
	private Stack<PlaySession> myPlayStats;
	public static final String MY_USER = "my_user";
	public static final String MY_GAME = "my_game";
	public static final String LATEST_PROGRESS = "latest_progress";
	public static final String LAST_SAVED_LEVEL_LOC = "last_saved_level_loc";
	/**
	 * Stat Cell Constructor, takes in a gamename and username
	 * @param gamename
	 * @param username
	 */
	public StatCell(String gamename, String username){
		super();
		myAuthorStats = new Stack<>();
		myPlayStats = new Stack<>();
		setProperty(MY_GAME, new VoogaString(gamename));
		setProperty(MY_USER, new VoogaString(username));
		setProperty(LAST_SAVED_LEVEL_LOC, new VoogaString(""));
		setProperty(LATEST_PROGRESS, new VoogaString(""));
	}
	/**
	 * Returns a list of authoring sessions
	 * @return
	 */
	public List<CellEntry> getAuthorStats(){
		return Collections.unmodifiableList(myAuthorStats);
	}
	/**
	 * Adds an authoring session
	 * @param authorsesh
	 */
	public void addAuthoringSession(AuthorSession authorsesh){
		myAuthorStats.add(authorsesh);
	}
	/**
	 * Returns all play session stats
	 * @return
	 */
	public List<CellEntry> getPlayStats(){
		return Collections.unmodifiableList(myPlayStats);
	}
	/**
	 * Returns the latest play session
	 * @return
	 */
	public PlaySession getLatestPlaySession(){
		if (myPlayStats.empty()){
			return null;
		}
		return myPlayStats.peek();
	}
	/**
	 * Returns the latest authoring session
	 * @return
	 */
	public AuthorSession getLatestAuthoringSession(){
		if (myAuthorStats.empty()){
			return null;
		}
		return myAuthorStats.peek();
	}
	/**
	 * Adds a playing session
	 * @param voogaplaysesh
	 */
	public void addPlaySession(PlaySession voogaplaysesh){
		myPlayStats.add(voogaplaysesh);
	}
	/**
	 * Updates the checkpoint to a given level name
	 * @param levelName
	 */
	public void updateProgress(String levelName){
		setProperty(LATEST_PROGRESS, new VoogaString(""));
	}
	/**
	 * Checks the progress
	 * @return
	 */
	public String checkProgress(){
		return (String) ((VoogaString) getProperty(LATEST_PROGRESS)).getValue();
	}
	
}
