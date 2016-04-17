package player.leveldatamanager;

import player.gamerunner.IGameRunner;

public class LevelTransitioner {

	private ILevelDataManager myLevelDataManager;
	private IGameRunner myGameRunner;
	
	public LevelTransitioner(ILevelDataManager levelDataManager, IGameRunner gameRunner){
		myLevelDataManager = levelDataManager;
		myGameRunner = gameRunner;
	}
	
	public int advanceLevel(){
		myLevelDataManager.clear();
		int advanceLevel = 1;
	}
	
	public replayLevel(){
//		myLevelDataManager.getNextLevel();
	}
	
	
	
	
}
