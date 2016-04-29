package data;

import java.util.List;

public class LevelsSaveObject {

	private String myFirstLevel;
	private List<String> listLevels;
	
	public LevelsSaveObject(String firstLevel, List<String> levels){
		myFirstLevel = firstLevel;
		listLevels = levels;
	}
	
	public List<String> getList(){
		return listLevels;
	}
	
	public String getFirstLevel(){
		return myFirstLevel;
	}
}
