package authoring.interfaces.model;

import java.util.List;
import java.util.Set;
import authoring.interfaces.Elementable;
import gameengine.SpriteFactory;
import javafx.scene.Node;
import tools.interfaces.VoogaData;

public interface EditElementable extends EditSpritable{
    public void addGameElements (Node ... elements);
    public void removeGameElements (Node ... elements);
    public List<Node> getElements ();
    public void addElementId(String id);
    public Node getElement(String id);
    public Set<String> getIds();
    public boolean hasElement(String id);
    public void addGlobalVariable(String name, VoogaData value);
    public Elementable getVoogaElement(String id);
}
