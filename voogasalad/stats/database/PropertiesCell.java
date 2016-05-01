package stats.database;

import tools.interfaces.VoogaData;

public interface PropertiesCell {
	
	VoogaData getProperty(String param);
	
	void setProperty(String property, VoogaData value);
}
