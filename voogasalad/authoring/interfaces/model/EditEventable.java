package authoring.interfaces.model;

import java.util.List;
import java.util.Map;
import java.util.Observer;

import events.VoogaEvent;
import tools.interfaces.VoogaData;

/**
 * Interface that allows an object to create and manage events.
 * 
 * @author Arjun Desai, Harry Guo, Nick Lockett, Aditya Srinivasan
 *
 */
public interface EditEventable extends EditSpritable,EditElementable{

	
	/**
	 * Adds events to a data structure container
	 * @param events: events to be added
	 */
	void addEvents (VoogaEvent ... events);

	/**
	 * Removes events from a data structure container
	 * @param events: events to be removed
	 */
	void removeEvents (VoogaEvent ... events);

	/**
	 * Gets the global variables of the object
	 * @return properties map of the object
	 */
	Map<String, VoogaData> getGlobalVariables ();

	/**
	 * Adds observer to this object
	 * @param o: observer object
	 */
	void addObserver (Observer o);

	/**
	 * Gets the list of VoogaEvents
	 * @return list of VoogaEvents
	 */
	List<VoogaEvent> getEvents();


}