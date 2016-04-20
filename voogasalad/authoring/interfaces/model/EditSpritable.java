package authoring.interfaces.model;

import authoring.interfaces.Elementable;
import gameengine.SpriteFactory;
import javafx.scene.Node;
import tools.VoogaException;

public interface EditSpritable {
    public SpriteFactory getSpriteFactory();
    public String getSpriteIdFromName(String name) throws VoogaException;
}
