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

import authoring.resourceutility.ResourceDecipherer;
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
	/**
	 * Constants
	 */
    private ClarifaiClient clarifai;
    private static final String TAGS_FOLDER_LOCATION = "tags/";
    private static final String TAGS_SUFFIX = "_tags.xml";
    private static final String GIF_SUFFIX = ".gif";
    private List<Tag> myTags;

    /**
     * Constructor for GameTagManager
     */
    public GameTagManager () {
        String Id = VoogaBundles.secrets.getProperty("clarifaiId");
        String Secret = VoogaBundles.secrets.getProperty("clarifaiSecret");
        clarifai = new ClarifaiClient(Id, Secret);
        myTags = new ArrayList<Tag>();
    }

    /**
     * Save tags to a game based on uploaded image
     * adds these tags to the current game
     */
    public void addTagsFromImage (String filename) {
        loadCurrentGameTags();
        try {
        	if(filename.endsWith(GIF_SUFFIX)|!ResourceDecipherer.isImage(filename)){
        		return;
        	}
        }
        catch (VoogaException e) {}
        List<RecognitionResult> results =
                clarifai.recognize(new RecognitionRequest(new File(filename)));
        myTags.addAll(results.get(0).getTags());
        saveCurrentGameTags();
    }

    /**
     * Serialize Tag list
     */
    private void saveCurrentGameTags () {
        try {Serializer.serializeLevel(myTags, getTagLocation());}
        catch (ParserConfigurationException | TransformerException | IOException | SAXException e) {}
    }

    /**
     * De-serialize Tag list
     */
    @SuppressWarnings("unchecked")
    private void loadCurrentGameTags () {
        Path path = Paths.get(getTagLocation());
        try {
            if (Files.notExists(path)) {
                saveCurrentGameTags();
            }
            List<Object> objects = Deserializer.deserialize(1, getTagLocation());
            myTags = (List<Tag>) objects.get(0);
        }
        catch (VoogaException e) {}
    }

    /**
     * Get tag location based on current Game
     */
    private String getTagLocation () {
        String gamename = VoogaBundles.preferences.getProperty("GameName");
        return TAGS_FOLDER_LOCATION + gamename + TAGS_SUFFIX;
    }

}
