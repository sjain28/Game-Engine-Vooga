package authoring.gui.eventpane;

import javafx.scene.Node;
import tools.VoogaException;

public interface EventGUI {
    public Node display();
    public String getDetails() throws VoogaException;
}
