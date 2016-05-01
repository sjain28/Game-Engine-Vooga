package stats.database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
	
	public ObservableList<String> getPropertyOptions(){
	    //return new ArrayList<String>(myPropertyMap.keySet());
	    return FXCollections.observableList(new ArrayList<String>(myPropertyMap.keySet()));
	}
}
