/**
 * 
 */
package player.gamerunner;

import java.util.ArrayList;
import java.util.List;

import data.Deserializer;
import tools.VoogaException;

/**
 * LevelListCreator that creates a level list to be used in GameRunner
 * A composition element in GameRunner
 * 
 * @author Hunter Lee
 *
 */
public class LevelListCreator {
	
    private static final String GAMES_PATH_PREFIX = "games/";

    private List<String> myLevelList;
    private String myGameFilePath;
    
    /**
     * Default constructor
     * 
     * @param xmlList
     * @throws VoogaException
     */
    public LevelListCreator(String xmlList) throws VoogaException {
    	myLevelList = new ArrayList<>();
		myGameFilePath = GAMES_PATH_PREFIX + xmlList + "/";
		String XMLwithListOfLevels = myGameFilePath + xmlList + ".xml";
		myLevelList = (List<String>) Deserializer.deserialize(1, XMLwithListOfLevels).get(0);
    }
    
    /**
     * Returns the level list
     * @return
     */
    public List<String> getLevelList() {
    	return myLevelList;
    }
    
    /**
     * Returns the current game file path
     * @return
     */
    public String getGameFilePath() {
    	return myGameFilePath;
    }

}
