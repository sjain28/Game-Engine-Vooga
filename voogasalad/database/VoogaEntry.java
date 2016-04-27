package database;

import java.util.HashMap;
import java.util.Map;

import tools.interfaces.VoogaData;

public class VoogaEntry implements VoogaInfoCell{
	private Map<String, VoogaData> myPropertyMap;
	
	public VoogaEntry(){
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
