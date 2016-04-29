package authoring.interfaces.model;

import gameengine.SpriteFactory;
import tools.VoogaException;

public interface EditSpritable {
    public SpriteFactory getSpriteFactory();
    public String getSpriteIdFromName(String name) throws VoogaException;
}
