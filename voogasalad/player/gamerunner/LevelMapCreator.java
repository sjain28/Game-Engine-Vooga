/**
 * 
 */
package player.gamerunner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import authoring.model.Preferences;
import data.Deserializer;
import tools.VoogaException;

/**
 * LevelListCreator that creates a level list to be used in GameRunner
 * A composition element in GameRunner
 * 
 * @author Hunter Lee
 *
 */
public class LevelMapCreator {
	
    private static final String GAMES_PATH_PREFIX = "games/";
    private static final String SLASH = "/";
    private static final String XML_EXTENSION = ".xml";

    private Map<String,String> myLevelMap;
    private String myGameFilePath;
    
    /**
     * Default constructor
     * 
     * @param xmlList
     * @throws VoogaException
     */
    public LevelMapCreator(String xmlList) throws VoogaException {
    	myLevelMap = new HashMap<String,String>();
		myGameFilePath = GAMES_PATH_PREFIX + xmlList + SLASH;
		String XMLwithListOfLevels =myGameFilePath + xmlList + XML_EXTENSION;
//		myLevelMap = ((Preferences) Deserializer.deserialize(1, XMLwithListOfLevels).get(0)).getManagerNames();
    }
    
    /**
     * Returns the level list
     * @return
     */
    public Map<String,String> getLevelList() {
    	return myLevelMap;
    }
    
    /**
     * Returns the current game file path
     * @return
     */
    public String getGameFilePath() {
    	return myGameFilePath;
    }

}
