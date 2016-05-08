// This entire file is part of my masterpiece.
// Aditya Srinivasan
package authoring.gui.animation;

import tools.Pair;

/**
 * An interface implemented by either trigger or command elements that indicate the presence of useful information. It is used,
 * for example, by the NameAnimationTrigger class in order to pass on the name entered in the text field.
 * 
 * @author Aditya Srinivasan
 *
 */
public interface Detailable {
	
	public Pair<String, Object> getDetails();

}
