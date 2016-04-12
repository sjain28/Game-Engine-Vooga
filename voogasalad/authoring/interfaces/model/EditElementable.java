package authoring.interfaces.model;

import java.util.List;
import gameengine.SpriteFactory;
import javafx.scene.Node;
import tools.interfaces.VoogaData;

public interface EditElementable extends EditSpritable{
    public void addGameElements (Node ... elements);
    public void removeGameElements (Node ... elements);
    public List<Node> getElements ();
    public Node getElement(String id);
    public boolean hasElement(String id);
    public void addGlobalVariable(String name, VoogaData value);
    
}
