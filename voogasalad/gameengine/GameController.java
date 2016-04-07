package gameengine;
import java.io.File;
import java.util.List;

/**
 * The GameRunner is charge of running the game the user has built
 * in the authoring environment, and ensuring that all pieces of the
 * Game are being managed and updated in one central space
 * 
 */
public class GameController {
	/**level logic**/
	private List<String> myLevels;
	private FileReader myFileReader;
	private LevelHandler myCurrentLevelHandler;
	private int myCurrentLevel;
	
	/**display objects**/
	
	/**
	 * Takes in a File of urls and initializes a Queue of 
	 * urls that each represent a level
	 * @param file
	 */
	public void init(String fileName){
		myFileReader = new FileReader();
		
		//parse through file and extract the game level urls here
		myLevels = extractLevelURLS(fileName); 
		myCurrentLevel = 0;
	}
	
	public void startLevel(){
		//create all level objects with the file reader for the level you're on
		myCurrentLevelHandler = myFileReader.createLevelObjects(myLevels.get(myCurrentLevel));
	}
	
	public void runLevel(){
		//timer in here to run steps and update both the logic and gui
	}
	
	private void step(){
		myCurrentLevelHandler.update();
		List<Object> updatedObjects = myCurrentLevelHandler.extractUpdatedObjects();
		//render display with updated objects
	}
	public void wonLevel(){
		if(myCurrentLevel == myLevels.size()-1){
			//tell the display to display whatever they display when you've won a game
		}
		else{
			//tell the display to display whatever they display when you've won a level 
			//and must proceed to the next.
			myCurrentLevel++;
		}
	}
	
	public void lostLevel(){
		//tell the display to display restart game button and a you lost the game sign
		
		myCurrentLevel = 1;
	}
	
	private List<String> extractLevelURLS(String filename){
		//TODO: Implement this
		return null;
	}
}
