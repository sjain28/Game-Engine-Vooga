package authoring.interfaces.model;

import java.util.Observer;

/**
 * Observable interface to handle observer-observable relationships.
 * 
 * @author HarryGuo
 *
 */
public interface Observable {
	
	/**
	 * Adds an observer to the current object implementing this interface.
	 * @param o
	 * 			observer to add
	 */
    void addObserver(Observer o);

}
