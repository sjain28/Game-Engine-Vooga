package authoring.interfaces;

import authoring.gui.Selector;

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
	 * Gets the elementable selected within the authoring environment.
	 * @return
	 */
	public Elementable getElementable();

	/**
	 * Selector to toggle the effect of the selected elementable in the
	 * authoring environment
	 * @param selector to affect the selected elementable 
	 */
	public void select(Selector selector);
}
