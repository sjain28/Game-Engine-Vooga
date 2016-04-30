package authoring.gui.refactoringevents;

import java.util.List;

import authoring.interfaces.model.EditEventable;
import javafx.scene.Node;

public class EventNode {
    private VoogaNode node;
    private EditEventable manager;
    private String name;
    
    
    private List<EventNode> nextEventNodes;
    private Object data;
    
    public EventNode(){
        
    }
    
    public Object data(){
        return data;
    }
    
    public Node getNode(){
        return (Node) node;
    }
    
    
    public void repopulate(){
        
    }
}
