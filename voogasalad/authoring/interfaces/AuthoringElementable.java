package authoring.interfaces;

import authoring.gui.Selector;
import authoring.model.AuthoringElementableMenu;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Menu;

/**
 * This interface designates the elementable within the authoring environment (design board).
 * In authoring environment, the elementable selected will be handled slightly differently, mostly
 * in terms of the display.
 * 
 * @author Aditya Srinivasan, Arjun Desai, Nick Lockett, Harry Guo
 *
 */

public interface AuthoringElementable extends Elementable {
//<<<<<<< HEAD
//
//	/**
//	 * Gets the elementable selected within the authoring environment.
//	 * @return
//	 */
//	public Elementable getElementable();
//
//	/**
//	 * Selector to toggle the effect of the selected elementable in the
//	 * authoring environment
//	 * @param selector to affect the selected elementable 
//	 */
//	public void select(Selector selector);
//=======
	
    public Elementable getElementable();
    
    public void select(Selector selector);
    
    public void setMenu(AuthoringElementableMenu menu);
    
}
