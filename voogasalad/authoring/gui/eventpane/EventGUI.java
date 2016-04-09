package authoring.gui.eventpane;

import auxiliary.VoogaException;
import javafx.scene.Node;

public interface EventGUI {
    public Node display();
    public String getDetails() throws VoogaException;
}
