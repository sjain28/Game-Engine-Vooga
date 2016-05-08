// This entire file is part of my masterpiece.
// Aditya Srinivasan

package authoring.gui.animation;

import java.util.Collection;

import authoring.Command;
import tools.Pair;

/**
 * The mediator interface that allows pairs to be registered. It acts as the controller, and orchestrates the relationship
 * between the GUI element and the command without either knowing about the other.
 * 
 * @author Aditya Srinivasan
 *
 */
public interface Mediator {
	
	public void register(Pair<? extends Trigger, Command> pairing);
	
	public Collection<Detailable> getDetailables(); 

}
