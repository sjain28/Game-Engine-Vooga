package authoring.tagextension;

import java.util.List;

import com.clarifai.api.Tag;

/**
 * Pair of objects between Tags and Games
 * Search for games based on the tag
 * @author Aditya Srinivasan
 *
 */
public class GameTagPair {
	private String myGameName;
	private List<Tag> myTagList;
	
	/**
	 * Constructor for 
	 * @param gamename: Name of Game
	 * @param taglist: list of tags that correspond to game
	 */
	public GameTagPair(String gamename, List<Tag> taglist){
		myGameName = gamename;
		myTagList = taglist;
	}
	
	/**
	 * 
	 * @return game name
	 */
	public String getGameName(){
		return myGameName;
	}
	
	/**
	 * 
	 * @return list of tags the game can be searched by
	 */
	public List<Tag> getTagList(){
		return myTagList;
	}
}
