package gameengine;

import java.util.List;
import java.util.Map;

import org.w3c.dom.events.Event;

/*
 * This class will hold all of the game events and handle updating the game accordingly.
 */
public class EventManager {

	private List<Character> KeyStrokes;
	private List<Event> myEvents;
	private List<String[]> KeyCombos;
	
	public EventManager() {
		// TODO Auto-generated constructor stub
	}
	public void addEvent(Event event){
		if (event.getCauses().contains(instanceOf(KeyCause))){
			KeyCombos.add(event.getCause(instanceOf(KeyCause).getString());
		}
		myEvents.add(event);
	}
public checkKeys(){
	for (String keyString : KeyEvents.keySet()){
		
	}
	//check map for string sorted by length
	//check permutations of string
	//set keycause in event to true
}
}
