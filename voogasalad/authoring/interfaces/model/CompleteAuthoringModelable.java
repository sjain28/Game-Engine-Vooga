package authoring.interfaces.model;

import authoring.interfaces.gui.Saveable;
import player.gamedisplay.Menuable;

public interface CompleteAuthoringModelable extends EditElementable, EditEventable, Saveable, Observable {
	
	public String getName();
	public void setName(String name);

}
