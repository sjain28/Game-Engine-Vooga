package authoring.interfaces;

import authoring.gui.Selector;
import authoring.model.AuthoringElementableMenu;

/**
 * This interface designates the elementable within the authoring environment (design board).
 * In authoring environment, the elementable selected will be handled slightly differently, mostly
 * in terms of the display.
 * 
 * @author Aditya Srinivasan, Arjun Desai, Nick Lockett, Harry Guo
 *
 */

public interface AuthoringElementable extends Elementable {
	
    public Elementable getElementable();
    
    public void select(Selector selector);
    
    public void setMenu(AuthoringElementableMenu menu);
    
}
