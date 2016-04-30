package authoring.interfaces.model;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import authoring.interfaces.Elementable;
import javafx.scene.Node;


public interface EditElementable extends EditSpritable {
    public void addGameElements (Node ... elements);

    public void removeGameElements (Node ... elements);

    public List<Node> getElements ();

    public void addElementId (String id);

    public Node getElement (String id);

    public Set<String> getIds ();

    public boolean hasElement (String id);

    public Elementable getVoogaElement (String id);

    public Collection<String> getMySpriteNames ();
}
