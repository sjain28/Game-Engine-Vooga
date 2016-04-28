package authoring.gui.refactoringevents;

import authoring.interfaces.model.EditEventable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;

public class EventNode {
    private EditEventable manager;
    private String name;
    private Node node;
    
    public EventNode(){
        node = new ComboBox();
    }
    
}
