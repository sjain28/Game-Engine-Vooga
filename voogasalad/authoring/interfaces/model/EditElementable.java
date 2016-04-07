package authoring.interfaces.model;

import java.util.List;
import javafx.scene.Node;

public interface EditElementable {
    public void addGameElements (Node ... elements);
    public void removeGameElements (Node ... elements);
    public List<Node> getElements ();
    public Node getElement(String id);
    public boolean hasElement(String id);
}
