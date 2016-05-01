package stats.database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tools.interfaces.VoogaData;
/**
 * Cell entry meant to store data in the database
 * @author Krista
 *
 */
public class CellEntry implements PropertiesCell{
	private Map<String, VoogaData> myPropertyMap;
	/**
	 * CellEntry constructor
	 */
	public CellEntry(){
		myPropertyMap = new HashMap<>();
	}
	/**
	 * Sets the entry property to a VoogaData
	 */
	public void setProperty(String property, VoogaData value){
		myPropertyMap.put(property, value);
	}
	/**
	 * Gets the VoogaData given a parameter key
	 */
	@Override
	public VoogaData getProperty(String param) {
		return myPropertyMap.get(param);
	}
	/**
	 * Returns the property key options to make looping through simple
	 * @return
	 */
	public ObservableList<String> getPropertyOptions(){
	    return FXCollections.observableList(new ArrayList<String>(myPropertyMap.keySet()));
	}
}
