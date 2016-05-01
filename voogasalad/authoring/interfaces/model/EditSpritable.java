package authoring.interfaces.model;

import gameengine.SpriteFactory;
import tools.VoogaException;

public interface EditSpritable {
    
	SpriteFactory getSpriteFactory();
    
	String getSpriteIdFromName(String name) throws VoogaException;
}
