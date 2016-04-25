package socialcenter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import data.Serializer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class UserInfo {

	private String myProfilePicturePath;
	private ImageView myProfilePicture;
	private String myUserName;
	private Map<String, GameInfo> myGamesInformation; 
	private String playerInfoLocation = "players/";
	private String XML_SUFFIX = ".xml";
	
	public UserInfo(String name, String profilePicPath){
		myProfilePicturePath = myProfilePicturePath;
		myGamesInformation = new HashMap<String, GameInfo>();
		myUserName = name;
	}
	
	public ImageView displayProfilePicture(){
		return new ImageView(new Image(myProfilePicturePath));
	}
	
	public void addGameInfo(String game, GameInfo gameInformation){
		myGamesInformation.put(game, gameInformation);
	}
	
	public boolean checkIfGameIsPlayed(String game){
		return myGamesInformation.containsKey(game);
	}
	
	public int returnHighScore(String game){
		return myGamesInformation.get(game).getHighScore();
	}
	
	public void savePlayerInfo() throws ParserConfigurationException, TransformerException, IOException, SAXException{
		Serializer.serializeLevel(this, playerInfoLocation + myUserName + XML_SUFFIX);
	}
}
