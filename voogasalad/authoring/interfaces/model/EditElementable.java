package authoring.interfaces.model;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import authoring.interfaces.Elementable;
import javafx.scene.Node;


public interface EditElementable extends EditSpritable {
    
	void addGameElements (Node ... elements);

    void removeGameElements (Node ... elements);

    List<Node> getElements ();

    void addElementId (String id);

    Node getElement (String id);

    Set<String> getIds ();

    boolean hasElement (String id);

    Elementable getVoogaElement (String id);

    Collection<String> getMySpriteNames ();
}
