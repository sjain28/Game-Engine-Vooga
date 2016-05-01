package authoring.interfaces;

import authoring.gui.Selector;
import authoring.model.AuthoringElementableMenu;

public interface AuthoringElementable extends Elementable {
	
    public Elementable getElementable();
    
    public void select(Selector selector);
    public void setMenu(AuthoringElementableMenu menu);
    
}
