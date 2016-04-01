package authoring.gui;

import authoring.interfaces.gui.Windowable;
import javafx.scene.Node;
import javafx.scene.control.MenuBar;

public class Menubar extends MenuBar implements Windowable{

    public Node getWindow () {
        return this;
    }
}
