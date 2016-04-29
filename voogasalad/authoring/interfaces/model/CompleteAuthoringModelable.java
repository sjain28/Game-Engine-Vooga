package authoring.interfaces.model;

import authoring.interfaces.gui.Saveable;
import tools.VoogaException;

public interface CompleteAuthoringModelable extends EditElementable, EditEventable, Saveable, Observable {
	
	public String getName();
	public void setName(String name);
        public String getSpriteNameFromId (String c) throws VoogaException;

}
