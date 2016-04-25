package socialcenter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SocialManager implements ISocialManager {

	
	private List<UserInfo> myPlayers;
	private LeaderBoardVisual myLeaderBoardVisual;
	
	
	public SocialManager(){
		
	}

	@Override
	public void createSceneToDisplay() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addPlayer() {
		// TODO Auto-generated method stub
		
	}

	private List<UserInfo> findTopTenScores(String game){
		List<UserInfo> topScores = new ArrayList<UserInfo>();
		for (UserInfo player: myPlayers){
			if (player.checkIfGameIsPlayed(game)){
				topScores.add(player);
			}
		}
		return topScores;
	}
	
	@Override
	public void addGameInfo() {
		// TODO Auto-generated method stub
		
	}
	
}
