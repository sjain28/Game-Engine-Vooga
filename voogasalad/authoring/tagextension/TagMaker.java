package authoring.tagextension;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

public class TagMaker {
	private static final String APP_ID = "2vnxrkiitf-p42_fNF6-cv0HxqRUvZgeC06pK0bJ";
	private static final String APP_SECRET = "5ziUKgQzECfVpaa4C-DCOUspwhC2JMNaDXSfJ-u7";
	private static final String TAG_LIST_LOCATION = "Tags.xml";
	private ClarifaiClient clarifai;
	private List<Tag> myTags;
	
	public TagMaker(){
		clarifai = new ClarifaiClient(APP_ID, APP_SECRET);
		
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
			Serializer.serialize(myTags, "games/"+gamename+"/"+TAG_LIST_LOCATION);
		} catch (ParserConfigurationException | TransformerException | IOException | SAXException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Deserialize Tag list
	 */
	private void loadTags(){
		try {
			List<Object> objects = Deserializer.deserialize(1, TAG_LIST_LOCATION);
			for(Object obj : objects){
				myTags.add((Tag) obj);
			}
		} catch (VoogaException e) {
			e.printStackTrace();
		}
	}
	
}
