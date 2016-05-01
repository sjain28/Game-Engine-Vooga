package authoring.model;

import authoring.interfaces.AuthoringElementable;
import authoring.interfaces.model.CompleteAuthoringModelable;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;

public class AuthoringElementableMenu extends ContextMenu{
    private CompleteAuthoringModelable model;
    private AuthoringElementable element;
    /**
     * superclass to manage an elementable menu
     * 
     * @param model
     * @param element
     */
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
    /**
     * Adds and Item to the menu, requires and event and the name of the tag
     * @param name
     * @param e
     */
    public void addItem(String name, EventHandler e){
        MenuItem item = new MenuItem(name);
        item.setOnAction(e);
        this.getItems().add(item);
    }
    
    
}
