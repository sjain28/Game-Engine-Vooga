package database;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import tools.interfaces.VoogaData;

public class VoogaCellUser implements VoogaInfoCell{
	
	private ResourceBundle propertyList;  
	private Map<String, VoogaData> myPropertyMap;
	
	public VoogaCellUser(String resourceString){
		propertyList = ResourceBundle.getBundle(resourceString);
		 myPropertyMap = new HashMap<String, VoogaData>();
	}
	
//	public VoogaCellUser(String resourceString, List<VoogaData> dataList){
//		propertyList = ResourceBundle.getBundle(resourceString);
//		 myPropertyMap = new HashMap<String, VoogaData>();
//		addProperties(dataList);
//	}
	
//	private void addProperties(List<VoogaData> dataList){
//		Enumeration<String> iter = propertyList.getKeys();
//	    while (iter.hasMoreElements()) {
//	    	String key = iter.nextElement();
//	    	myPropertyMap.put(key, null);
//	    }
//	}
	
	public void setProperty(String property, VoogaData value){
		myPropertyMap.put(property, value);
	}
	
	@Override
	public VoogaData getProperty(String param) {
		// TODO Auto-generated method stub
		return myPropertyMap.get(param);
	}

}
