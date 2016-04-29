package database;

import java.util.HashMap;
import java.util.Map;

import tools.interfaces.VoogaData;

public class CellEntry implements PropertiesCell{
	private Map<String, VoogaData> myPropertyMap;
	
	public CellEntry(){
		myPropertyMap = new HashMap<String, VoogaData>();
	}
	public void setProperty(String property, VoogaData value){
		myPropertyMap.put(property, value);
	}
	@Override
	public VoogaData getProperty(String param) {
		return myPropertyMap.get(param);
	}
}
