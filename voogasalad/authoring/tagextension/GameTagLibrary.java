package authoring.tagextension;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.clarifai.api.ClarifaiClient;
import com.clarifai.api.Tag;

import data.Deserializer;
import resources.VoogaBundles;
import tools.VoogaException;

public class GameTagLibrary {
	//TODO: make sure to make a resources file for the tag information.
	private static final String TAGS_FOLDER_LOCATION = "tags/";
	private static final String TAG_SUFFIX = "_tags.xml";
	private List<String> myDescripList;
	private List<Tag> myTags;
	private ClarifaiClient clarifai;

	/**
	 * Constructor for GameTagManager
	 */
	public GameTagLibrary(){
		myTags = new ArrayList<Tag>();
		myDescripList = new ArrayList<String>();
		String Id = VoogaBundles.preferences.getProperty("clarifaiId");
		String Secret = VoogaBundles.preferences.getProperty("clarifaiSecret");
		clarifai = new ClarifaiClient(Id,Secret);
	}
	/**
	 * Returns list of GameNames by tag matches
	 * 
	 * @param taglist
	 * @return
	 */
	public List<String> getListOfGameNamesByTag(List<String> descriptionlist){
		myDescripList = descriptionlist;
		List<String> gamenames = new ArrayList<String>();
		Map<List<Tag>,String> tagmap = deSerializeAllGameTags();
		
		for(List<Tag> key : tagmap.keySet()){
			gamenames.add(tagmap.get(key));
		}
		System.out.println(gamenames);
		return gamenames;
	}
	/**
	 * DeSerializes the lists of tags for every game
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private Map<List<Tag>,String> deSerializeAllGameTags(){
		File dir = new File(TAGS_FOLDER_LOCATION);
		TagListComparator comp = new TagListComparator(myDescripList);
		Map<List<Tag>,String> tagtogamemap = new TreeMap<List<Tag>,String>(comp);
		
		int count = 0;
		
		File[] directoryListing = dir.listFiles();
		for (File child : directoryListing) {
			String ext = child.getName().substring(child.getName().length()-TAG_SUFFIX.length());
			if(ext.equals(TAG_SUFFIX)){
				count++;
				try {
					String gamename = child.getName().substring(0,child.getName().length()-TAG_SUFFIX.length());
					List<Object> objects = Deserializer.deserialize(1,dir.getName()+"/"+child.getName());
					System.out.println("objects: "+objects);
					myTags = (List<Tag>) objects.get(0);
					System.out.println("moreobjects: "+myTags);
					tagtogamemap.put(myTags,gamename);
					System.out.println(tagtogamemap);
					System.out.println("getting gamename: "+tagtogamemap.get(myTags));
				} catch (VoogaException e) {e.printStackTrace();}
			}
		}
		System.out.println(count);
		System.out.println(tagtogamemap.size());
		return tagtogamemap;
	}
}
		  
