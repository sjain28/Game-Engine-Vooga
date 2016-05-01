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
	
	/**
	 * Returns the currently selected elementable in the authoring environment.
	 * @return
	 */
    public Elementable getElementable();
    
    /**
     * Toggles the display of the selector.
     * @param selector
     */
    public void select(Selector selector);
    
    /**
     * Sets up the context menu.
     * @param menu
     */
    public void setMenu(AuthoringElementableMenu menu);
    
}
