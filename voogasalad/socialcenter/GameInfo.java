package socialcenter;

public class GameInfo {

	
	private String myLastCheckpoint;
	private String myLastLevel;
	private int myHighScore;
	
	public GameInfo(String level, String checkpoint, int HighScore){
		myLastCheckpoint = checkpoint;
		level = myLastLevel;
		myHighScore = HighScore;
	}
	
	public void addHighScore(int scoreAmount){
		myHighScore+= scoreAmount;
	}
	
	public void setHighScore(int scoreAmount){
		myHighScore = scoreAmount;
	}
	
	public void updateCheckpoint(String s){
		myLastCheckpoint = myLastLevel;
	}
	
	public String loadCheckpoint(String gamesPath, String s){
		return gamesPath + s;
	}
	
	public void setLastLevel(String s){
		myLastLevel = s;
		myLastCheckpoint = s;
	}
	
	
}
