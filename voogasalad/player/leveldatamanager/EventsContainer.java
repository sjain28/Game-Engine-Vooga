// This entire file is part of my masterpiece.
// KRISTA OPSAHL-ONG
/**
 * Masterpiece Justification and Description: I had noticed a large amount 
 * of repeated code within this class and others because of the seperation of
 * KeyPresses and KeyReleases, which were being evaluated exactly the same way.
 * To fix this, I combined them into one this, and refactored away confusing 
 * logic by creating a KeyCombo class that held a list of key clicks and an
 * EventType, replacing the need for two lists. This Key Combo class was also
 * given the ability to know which KeyEvents it matched with, which simplified 
 * the logic and work load in EventManager greatly. Map streams were also used
 * to replace loops in the event manager to keep work neater. Also added in an
 * Updatable interface for EventManager to use, along with all the other 
 * Classes in our project that have an update function, which is about 5.
 */
package player.leveldatamanager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.scene.input.KeyEvent;
import events.Cause;
import events.KeyCause;
import events.VoogaEvent;

/**
 * Container class for Events that organizes keyCombos, keyCauses
 * to make work easier for the EventManager
 * Also provides relevant methods to support managing them
 * 
 * Composition element of LevelData interface
 * 
 * @author Hunter Lee
 *
 */
public class EventsContainer {
	
	private List<KeyEvent> myKeyEvents;
	private List<VoogaEvent> myEvents;
	private List<KeyCombo> myKeyCombos;
	private Map<KeyCombo, KeyCause> myKeyCauses;

	/**
	 * Default constructor
	 */
	public EventsContainer() {
		myEvents = new ArrayList<>();
		myKeyCombos = new ArrayList<KeyCombo>();
		myKeyCauses = new HashMap<KeyCombo, KeyCause>();
	}
	/**
	 * Add a VoogaEvent and populate the List of Key Causes if necessary
	 * @param voogaEvent
	 * @param eventmethods
	 */
	public void addEventAndPopulateKeyCombos (VoogaEvent voogaEvent, ResourceBundle eventmethods) {
		myEvents.add(voogaEvent);
		for (Cause cause : voogaEvent.getCauses()) {
			if (cause instanceof KeyCause) {
				KeyCause keyc = (KeyCause) cause;
				categorizeAndPlaceKeyCause(eventmethods, cause, keyc);
			}
		}
	}
	/**
	 * Categorizes a key cause based on its click type and populates KeyCombos and KeyCauses
	 * @param eventmethods
	 * @param cause
	 * @param keyc
	 */
	private void categorizeAndPlaceKeyCause(ResourceBundle eventmethods, Cause cause, KeyCause keyc) {
		KeyCombo newCombo = null;
		if (((KeyCause) cause).getMyPressed().equals(eventmethods.getString("Press"))) {
			newCombo = new KeyCombo(keyc.getKeys(),KeyEvent.KEY_PRESSED);
		}
		else { 
			newCombo = new KeyCombo(keyc.getKeys(),KeyEvent.KEY_RELEASED);
		}
		myKeyCombos.add(newCombo);
		myKeyCauses.put(newCombo,keyc);
	}
	/**
	 * Returns Events
	 * @return the myEvents
	 */
	public List<VoogaEvent> getEvents() {
		return myEvents;
	}
	/**
	 * Sets Events
	 * @param myEvents the myEvents to set
	 */
	public void setEvents(List<VoogaEvent> myEvents) {
		this.myEvents = myEvents;
	}	
	/**
	 * @return the myKeyPressedCombos
	 */
	public List<KeyCombo> getKeyCombos() {
		return myKeyCombos;
	}
	/**
	 * Returns Key Causes
	 * @return
	 */
	public Map<KeyCombo, KeyCause> getKeyCauses() {
		return myKeyCauses;
	}
	/**
	 * Sets key events
	 */
	public void setKeyEvents(List<KeyEvent> keyevents){
		myKeyEvents = keyevents;
	}
	/**
	 * Gets key events
	 */
	public List<KeyEvent> getKeyEvents(){
		return myKeyEvents;
	}
	/**
	 * Clears and resets the contents of all lists and maps
	 * To be called during level transition
	 * 
	 */
	public void clearAll() {
		myEvents.clear();
		myKeyCombos.clear();
		myKeyCauses.clear();
	}
}