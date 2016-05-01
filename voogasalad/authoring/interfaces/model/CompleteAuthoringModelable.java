package authoring.interfaces.model;

import authoring.interfaces.gui.Saveable;
import tools.VoogaException;

/**
 * Complete Authoring Model to Handle the front end.
 * 
 * @author Aditya Srinivasan, Harry Guo, Nick Lockett, Arjun Desai
 *
 */
public interface CompleteAuthoringModelable extends EditElementable, EditEventable, Saveable, Observable {

	/**
	 * Get name of the current authoring model
	 * @return name of current authoring model
	 */
	String getName();

	/**
	 * Sets the name of the current authoring model
	 * @param name: desired name of authoring model
	 */
	void setName(String name);

	/**
	 * Gets a sprite ID from the string name of the sprite
	 * 
	 * @param name: of sprite
	 * @return ID of sprite
	 * @throws VoogaException
	 */
	String getSpriteNameFromId (String c) throws VoogaException;


}
