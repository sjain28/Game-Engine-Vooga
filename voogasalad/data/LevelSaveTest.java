package data;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import javafx.application.Application;
import javafx.stage.Stage;
import player.gamerunner.GameRunner;
import player.gamerunner.IGameRunner;
import player.gamerunner.VoogaGame;

public class LevelSaveTest extends Application{

	public static void main(String[] args) throws ParserConfigurationException, TransformerException, IOException, SAXException {
		// TODO Auto-generated method stub
		launch(args);
	
	}

	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		String filePath = "games/JoshGame/";
		String fileName = filePath + "JoshGame.xml";
		File file = new File(fileName);
		List<String> stringofLevels = new ArrayList<String>();
//		stringofLevels.add("Test3.xml");
		stringofLevels.add("Test2.xml");
		stringofLevels.add("Test4.xml");
		stringofLevels.add("Test3.xml");
		stringofLevels.add("Test3.xml");
		stringofLevels.add("Test1.xml");
//		Map<String,String> levelMap = new HashMap<String,String>();
//		levelMap.put("Player1", "Test1.xml");
//		levelMap.put("Player2", "Test2.xml");
//		levelMap.put("Player3", "level7.xml");
//		levelMap.put("Player4", "Trythislevel.xml");
		Serializer.serialize(stringofLevels, fileName);
//		IGameRunner gameRunner = new GameRunner(game);
//		gameRunner.playGame();
	}

}
