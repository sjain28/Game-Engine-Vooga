package player.gamerunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import data.Deserializer;
import data.Serializer;
import tools.VoogaException;

public class VoogaGame {

	private List<String> levelOrder;
	private Map<String, String> userProgress;
	
	public VoogaGame(String xmlFileName){
		try{
		levelOrder = (List<String>) Deserializer.deserialize(1, xmlFileName).get(0);
		}
		catch(VoogaException e){
			e.printStackTrace();
		}
//		userProgress = (Map<String,String>) Deserializer.deserialize(2, xmlFileName).get(1);
		System.out.println(levelOrder);
	}
	
	public VoogaGame(List<String> initialLevels){
		levelOrder = initialLevels;
		userProgress = new HashMap<String,String>();
	}
	
	public VoogaGame(List<String> stringofLevels, Map<String, String> levelMap) {
		// TODO Auto-generated constructor stub
		levelOrder = stringofLevels;
		userProgress = levelMap;
	}
	
	public List<String> getGameLevels() {
		// TODO Auto-generated constructor stub
		return levelOrder;
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String fileName = "levelTestFile.xml";
		
		VoogaGame game = new VoogaGame(fileName);
	}
	
}
