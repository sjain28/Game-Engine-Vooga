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
    public SpriteFactory getSpriteFactory();
    public String getSpriteIdFromName(String name) throws VoogaException;
}
