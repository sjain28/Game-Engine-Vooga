package authoring.tagextension;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.clarifai.api.Tag;
import data.Deserializer;
import tools.VoogaException;

/**
 * Library that returns the list of tagss for the game and stores the tag to game pairs
 * 
 * @author Aditya Srinivasan
 *
 */
public class GameTagLibrary {
	private static final String TAGS_FOLDER_LOCATION = "tags/";
	private static final String TAG_SUFFIX = "_tags.xml";
	private List<String> myDescripList;
	private List<Tag> myTags;
	
	/**
	 * Returns list of GameNames by tag matches
	 * 
	 * @param taglist
	 * @return
	 */
	public List<String> getListOfGameNamesByTag(List<String> descriptionlist){
		myDescripList = descriptionlist;
		List<String> gamenames = new ArrayList<String>();
		List<GameTagPair> gtpairs = deSerializeAllGameTags();
		for(GameTagPair gtp : gtpairs){
			gamenames.add(gtp.getGameName());
		}
		return gamenames;
	}
	/**
	 * DeSerializes the lists of tags for every game
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private List<GameTagPair> deSerializeAllGameTags(){
		File dir = new File(TAGS_FOLDER_LOCATION);
		TagListComparator comp = new TagListComparator(myDescripList);
		List<GameTagPair> gtpairs = new ArrayList<GameTagPair>();
		
		int count = 0;
		
		File[] directoryListing = dir.listFiles();
		for (File child : directoryListing) {
			String ext = child.getName().substring(child.getName().length()-TAG_SUFFIX.length());
			if(ext.equals(TAG_SUFFIX)){
				count++;
				try {
					String gamename = child.getName().substring(0,child.getName().length()-TAG_SUFFIX.length());
					List<Object> objects = Deserializer.deserialize(1,dir.getName()+"/"+child.getName());
					myTags = (List<Tag>) objects.get(0);
					GameTagPair gtp = new GameTagPair(gamename, myTags);
					gtpairs.add(gtp);
				} catch (VoogaException e) {e.printStackTrace();}
			}
		}
		Collections.sort(gtpairs,comp);
		return gtpairs;
	}
}
