package authoring.tagextension;

import java.util.List;

import com.clarifai.api.Tag;

public class GameTagPair {
	private String myGameName;
	private List<Tag> myTagList;
	
	public GameTagPair(String gamename, List<Tag> taglist){
		myGameName = gamename;
		myTagList = taglist;
	}
	public String getGameName(){
		return myGameName;
	}
	public List<Tag> getTagList(){
		return myTagList;
	}
}
