package authoring.interfaces.model;

import gameengine.SpriteFactory;
import tools.VoogaException;

/**
 * Interface that gives access to other objects the ability to edit sprites.
 * 
 * @author Aditya Srinivasan, Harry Guo, Nick Lockett, Arjun Desai
 *
 */

public interface EditSpritable {
	
	/**
	 * Getter for a sprite factory
	 * @return a sprite factory
	 */
    SpriteFactory getSpriteFactory();
    
    /**
     * Gets a sprite ID from the string name of the sprite
     * 
     * @param name: of sprite
     * @return ID of sprite
     * @throws VoogaException
     */
    String getSpriteIdFromName(String name) throws VoogaException;

}
