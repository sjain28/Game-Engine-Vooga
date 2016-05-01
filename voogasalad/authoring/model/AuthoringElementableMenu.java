package authoring.model;

import authoring.interfaces.AuthoringElementable;
import authoring.interfaces.model.CompleteAuthoringModelable;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

public class AuthoringElementableMenu extends ContextMenu{
    private CompleteAuthoringModelable model;
    private AuthoringElementable element;
    
    public AuthoringElementableMenu(CompleteAuthoringModelable model,AuthoringElementable element){
        this.element=element;
        this.model=model;
        initialize();
    }
    
    private void initialize(){
        addItem("Remove",e->removeElement());
    }
    
    private void removeElement(){
        model.removeGameElements((Node) element); 
    }
    
    public void addItem(String name, EventHandler e){
        MenuItem item = new MenuItem(name);
        item.setOnAction(e);
        this.getItems().add(item);
    }
    
    
}
