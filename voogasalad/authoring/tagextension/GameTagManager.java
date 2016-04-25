package authoring.tagextension;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.xml.sax.SAXException;
import com.clarifai.api.ClarifaiClient;
import com.clarifai.api.RecognitionRequest;
import com.clarifai.api.RecognitionResult;
import com.clarifai.api.Tag;
import data.Deserializer;
import data.Serializer;
import resources.VoogaBundles;
import tools.VoogaException;

public class GameTagManager {
	/**Application information**/
	private static final String APP_ID = "2vnxrkiitf-p42_fNF6-cv0HxqRUvZgeC06pK0bJ";
	private static final String APP_SECRET = "5ziUKgQzECfVpaa4C-DCOUspwhC2JMNaDXSfJ-u7";
	private ClarifaiClient clarifai;

	/**Tag information**/
	private static final String TAG_LIST_LOCATION = "Tags.xml";
	private List<Tag> myTags;
	
	public GameTagManager(){
		clarifai = new ClarifaiClient(APP_ID, APP_SECRET);
		myTags = new ArrayList<Tag>();
		
		//read in the serialized map of objects
		loadTags();
	}
	
	public void addTagsFromImage(String filename){
		//Retrieve recognition results
		List<RecognitionResult> results = clarifai.recognize(new RecognitionRequest(new File(filename)));
		
		//TODO: here for debugging purposes, print out names of tags
		for (Tag tag : results.get(0).getTags()){
			System.out.println(tag.getName() + ": " + tag.getProbability());
		}
		
		//add all of the Tags to a list
		myTags.addAll(results.get(0).getTags());
		
		//save Tags
		saveTags();
	}
	
	/**
	 * Serialize Tag list
	 */
	private void saveTags(){
		try {
			String gamename = VoogaBundles.preferences.getProperty("GameName");
			Serializer.serialize(myTags, gamename+TAG_LIST_LOCATION);
		} catch (ParserConfigurationException | TransformerException | IOException | SAXException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * De-serialize Tag list
	 */
	private void loadTags(){
		String gamename = VoogaBundles.preferences.getProperty("GameName");
		Path path = Paths.get(gamename+TAG_LIST_LOCATION);
		try {
			Path filepath = Paths.get(gamename+TAG_LIST_LOCATION);
			if (Files.notExists(path)) {
				System.out.println("save tags in load tags");
				saveTags();
			}
			List<Object> objects = Deserializer.deserialize(1, gamename+TAG_LIST_LOCATION);
			myTags = (List<Tag>) objects.get(0);
		} catch (VoogaException e) {
			e.printStackTrace();
		}
	}
	
}
