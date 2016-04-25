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
/**
 * Class that generates tags for a given game based on uploaded images
 * These tags are serialized in a game specific xml file, and can be
 * reloaded when the user chooses to sort through Games by Tags.
 * 
 * @author Krista
 *
 */
public class GameTagManager {
	/**Application information**/
	private static final String APP_ID = "2vnxrkiitf-p42_fNF6-cv0HxqRUvZgeC06pK0bJ";
	private static final String APP_SECRET = "5ziUKgQzECfVpaa4C-DCOUspwhC2JMNaDXSfJ-u7";
	private ClarifaiClient clarifai;

	/**Tag information**/
	private static final String TAGS_FOLDER_LOCATION = "tags/";
	private static final String TAGS_SUFFIX = "_tags.xml";
	private List<Tag> myTags;
	
	/**
	 * Constructor for GameTagManager
	 */
	public GameTagManager(){
		clarifai = new ClarifaiClient(APP_ID, APP_SECRET);
		myTags = new ArrayList<Tag>();
	}
	/**
	 * Save tags to a game based on uploaded image
	 * adds these tags to the current game
	 * 
	 * @param filename
	 */
	public void addTagsFromImage(String filename){
		//set tags from current game
		loadCurrentGameTags();
		
		//Retrieve recognition results
		List<RecognitionResult> results = clarifai.recognize(new RecognitionRequest(new File(filename)));
		
		//TODO: here for debugging purposes, print out names of tags
		for (Tag tag : results.get(0).getTags()){
			System.out.println(tag.getName() + ": " + tag.getProbability());
		}
		
		//add all of the Tags to a list
		myTags.addAll(results.get(0).getTags());
		
		//save Tags
		saveCurrentGameTags();
	}
	/**
	 * Serialize Tag list
	 */
	private void saveCurrentGameTags(){
		try {
			Serializer.serialize(myTags,getTagLocation());
		} catch (ParserConfigurationException | TransformerException | IOException | SAXException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * De-serialize Tag list
	 */
	@SuppressWarnings("unchecked")
	private void loadCurrentGameTags(){
		Path path = Paths.get(getTagLocation());
		try {
			if (Files.notExists(path)) {saveCurrentGameTags();}
			List<Object> objects = Deserializer.deserialize(1,getTagLocation());
			myTags = (List<Tag>) objects.get(0);
		} catch (VoogaException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Get tag location based on current Game
	 * @return
	 */
	private String getTagLocation(){
		String gamename = VoogaBundles.preferences.getProperty("GameName");
		return TAGS_FOLDER_LOCATION+gamename+TAGS_SUFFIX;
	}
	
}
