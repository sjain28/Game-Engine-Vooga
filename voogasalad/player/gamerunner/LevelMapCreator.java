/**
 * 
 */
package player.gamerunner;
import java.util.HashMap;
import java.util.Map;

import authoring.gui.cartography.LevelType;
import authoring.gui.cartography.NetworkContainer;
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

    private Map<String,LevelType> myLevelMap;
    private String myGameFilePath;
    
    /**
     * Default constructor
     * 
     * @param xmlList
     * @throws VoogaException
     */
    public LevelMapCreator(String xmlList) throws VoogaException {
    	myLevelMap = new HashMap<>();
		myGameFilePath = GAMES_PATH_PREFIX + xmlList + SLASH;
		String XMLwithListOfLevels =myGameFilePath + "map" + SLASH + xmlList + "Map" + XML_EXTENSION;
		myLevelMap = ((NetworkContainer) Deserializer.deserialize(1, XMLwithListOfLevels).get(0)).getLevelTypes();
    }
    
    /**
     * Returns the level list
     * @return
     */
    public Map<String,LevelType> getLevelMap() {
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
