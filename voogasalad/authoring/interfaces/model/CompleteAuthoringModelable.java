package authoring.interfaces.model;

import authoring.interfaces.gui.Saveable;
import tools.VoogaException;

public interface CompleteAuthoringModelable extends EditElementable, EditEventable, Saveable, Observable {
	
	String getName();
	
	void setName(String name);
	
    String getSpriteNameFromId (String c) throws VoogaException;

}
