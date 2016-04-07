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
	//private FileReadManager myFileReader;
	/**list of levelUrls w links to the xml representing a given level**/
	private List<String> myLevels;
	
	private FileReader myFileReader;
	
	private 
	/**
	 * Takes in a File of urls and initializes a Queue of 
	 * urls that each represent a level
	 * @param file
	 */
	public void init(String fileName){
		//parse through file and extract the urls here
		myFileReader = new FileReader();
		myFileReader.createLevelObjects()
	}
	public void startLevel(){
		
	}
}
