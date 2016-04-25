package authoring.tagextension;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.clarifai.api.ClarifaiClient;
import com.clarifai.api.Tag;

import data.Deserializer;
import resources.VoogaBundles;

public class GameTagLibrary {
	
	private static final String TAGS_FOLDER_LOCATION = "tags/";
	private ClarifaiClient clarifai;

	/**
	 * Constructor for GameTagManager
	 */
	public GameTagLibrary(){
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
	public List<String> getListOfGameNamesByTag(List<String> taglist){
		deSerializeAllGameTags();
		return null;
	}
	private Map<String, List<Tag>> deSerializeAllGameTags(){
		File dir = new File("tags/");
		System.out.println(dir.isDirectory());
		  File[] directoryListing = dir.listFiles();
		  if (directoryListing != null) {
		    for (File child : directoryListing) {
		      System.out.println(child.getName());
		    }
		  } else {
			  System.out.println("didn't work");
		    // Handle the case where dir is not really a directory.
		    // Checking dir.isDirectory() above would not be sufficient
		    // to avoid race conditions with another process that deletes
		    // directories.
		  }
		//List<Object> objects = Deserializer.deserialize(1,);
		  return null;
	}
}
