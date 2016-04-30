package tests;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import data.Serializer;
import javafx.application.Application;
import javafx.stage.Stage;

public class LevelSaveTest extends Application{
	
	public static void main(String[] args) throws ParserConfigurationException, TransformerException, IOException, SAXException {
		// TODO Auto-generated method stub
		launch(args);
	
	}

	/* COMMENTS FOR ADITYA: I've provided an example here of how you would go about starting to play a GAME
	 * (NOT A LEVEL) in the authoring environment. Presumably what you would do is you would come up with a list of levels.
	 * The order of these levels really doesn't matter when you serialize them. What really matters is this:
	 * 
	 * 
	 */
	
	
	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		
		//Notice how the path of the file (containing the serialized list) is games/NAMEOFTHEGAMEHERE and the file name is NAMEOFTHEGAMEHERE.xml. 
		
		String filePath = "games/JoshGame/";
		String fileName = filePath + "asasdfasdfasdfasdfdfasdf.xml";
		List<String> stringofLevels = new ArrayList<String>();
		
		//
//		stringofLevels.add("Test3.xml");
		stringofLevels.add("Level1.xml");
		stringofLevels.add("Level2.xml");
		stringofLevels.add("Level3.xml");
		stringofLevels.add("Level4.xml");
		stringofLevels.add("Level5.xml");
		Serializer.serialize(stringofLevels,fileName);
		
		// THIS CODE IS STRICTLY IN THE TEST LEVEL
		
		/* For any given level, say, level 2, you have to create a win conditions event.
		 * An example cause for this win condition event would be the combination of the these two CAUSES below.
		 * 1) Reach a certain score 
		 * 2) X position of a character reaches a certain score.
		 * 
		 *  The win condition EFFECT would be a variable in the global variable map being changed from "null" to a String
		 *  of the next Level. This is where a voogastring type comes in to play. Essentially, the workflow for changing levels
		 *  is the following: whenever a current level is being played and a win condition is not met, then the value of this string
		 *  (I Called it "levelString" will always be set to "" (the empty string). However, when a win condition is met,
		 *  The effect of this win condition is to change the "levelString" from "" (the empty string) to the name of the next XML file that will be played.
		 *  
		 * 
		 * So whenever you make the playground for connecting levels together, what I would do is create an order for the list of level. 
		 * Serialize that list. 
		 * The order doesn't matter because the name of the next level is contained within the Win condition event.
		 * 
		 * Make sure that the name of the XML FILE containing the list of xml file strings is in the directory
		 * games/NAMEOFTHEGAMEHERE where the name of the XML file is NAMEOFTHEGAMEHERE.xml. In the code above, it is
		 * the line "Serializer.serialize(stringofLevels, filePath+fileName);"
		 * 
		 *  Then, all the levels of the game will go in the file path,
		 *  games/NAMEOFTHEGAMEHERE/levels.
		 * 
		 * Then, call gamerunner on NAMEOFTHEGAMEHERE, not filePath+fileName like above.
		 * 
		 * An example of the code is 
		 * IGameRunner gameRunner  new GameRunner();
        	gameRunner.playGame(TESTXML_PATH);"
		 * 
		 */
		
//        IGameRunner gameRunner = new GameRunner();
//        gameRunner.playGame(TESTXML_PATH);
		
//		Serializer.serialize(stringofLevels, fileName);

	}

}
