package database;

import tools.interfaces.VoogaData;

public interface VoogaInfoCell {
	VoogaData getProperty(String param);
	public void setProperty(String property, VoogaData value);
}
