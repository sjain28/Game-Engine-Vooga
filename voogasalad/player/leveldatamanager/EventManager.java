//This entire class is part of my masterpiece
//SAUMYA JAIN
/*
 * This class's responsibility is to manage interaction and information that has to flow between the gamerunner and 
 * VoogaEvent objects
 * This class reflects good design because previously, handling of events was shared between EventManager and
 * KeyEventContainer. This resulted in duplication of information and logic for no clear reason. 
 * In this refactored version EventManager has been greatly simplified, and takes care of the entire process
 * of updating Events.
 * This new design is also a more accurate implementation of the chain of responsibility design pattern. 
 * Now, data flows from the GameRunner to the LevelData to this Manager to individual Event objects. This is a much
 * more simplified data flow compared to what we previously had. I think setting up a chain of responsibility
 * was a major goal of our backend refactoring, but that goal got lost amidst everything else.  
 */
package player.leveldatamanager;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import events.VoogaEvent;
import javafx.scene.input.KeyEvent;

public class EventManager {

	private List<VoogaEvent> myEvents;
	

	public EventManager() {
		myEvents = new ArrayList<>();
	}

	/**
	 * Add a given VoogaEvent 
	 * @param voogaEvent
	 */
	public void addEvent (VoogaEvent voogaEvent, ResourceBundle eventmethods) {
		myEvents.add(voogaEvent);
	}
	
	/**
	 * Clears and resets the contents of all lists and maps
	 * To be called during level transition
	 * 
	 */
	public void clearAll() {
		myEvents.clear();
	}

	/**
	 * 
	 * @param data an ILevelData object that communicates with Events
	 * @param presses Key presses read in from gamerunner
	 * @param releases Key releases read in from gamerunner
	 */
	public void update(ILevelData data, List<KeyEvent> presses, List<KeyEvent> releases) {
		data.setPresses(presses);
		data.setReleases(releases);
		for(VoogaEvent event: myEvents){
			event.update(data);
		}
		
	}

}