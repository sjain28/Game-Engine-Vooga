package authoring.interfaces;

import authoring.gui.Selector;
import authoring.model.AuthoringElementableMenu;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Menu;

public interface AuthoringElementable extends Elementable {
	
    public Elementable getElementable();
    
    public void select(Selector selector);
    public void setMenu(AuthoringElementableMenu menu);
    
}
