package socialcenter;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.image.ImageView;

public class UserInfo {

	private ImageView myProfilePicture;
	private Map<String, GameInfo> myGamesInformation; 
	
	public UserInfo(String profilePicPath){
		myProfilePicture = new ImageView(profilePicPath);
		myGamesInformation = new HashMap<String, GameInfo>();
	}
	
	public void addGameInfo(String game, GameInfo gameInformation){
		myGamesInformation.put(game, gameInformation);
	}
	
	
	
	
}
