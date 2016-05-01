/**
 * 
 */
package player.leveldatamanager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import events.Cause;
import events.KeyCause;
import events.VoogaEvent;

/**
 * A container class that stores Events
 * 								 KeyPressedCombos
 * 								 KeyReleasedCombos
 * 								 KeyPressCauses
 * 								 KeyReleaseCauses
 * Also provides relevant methods to support managing them
 * 
 * Composition element of LevelData interface
 * 
 * @author Hunter Lee
 *
 */
public class KeyEventContainer {

	private List<VoogaEvent> myEvents;
	private List<List<String>> myKeyPressedCombos;
	private List<List<String>> myKeyReleasedCombos;
	private Map<List<String>, KeyCause> myKeyPressCauses;
	private Map<List<String>, KeyCause> myKeyReleaseCauses;

	/**
	 * Default constructor
	 * 
	 */
	public KeyEventContainer() {
		myEvents = new ArrayList<>();
		myKeyPressedCombos = new ArrayList<>();
		myKeyReleasedCombos = new ArrayList<>();
		myKeyPressCauses = new HashMap<>();
		myKeyReleaseCauses = new HashMap<>();
	}

	/**
	 * Add a given VoogaEvent and populate the pressed and released KeyCombos
	 * 
	 * @param voogaEvent
	 */
	public void addEventAndPopulateKeyCombos (VoogaEvent voogaEvent, ResourceBundle eventmethods) {
		myEvents.add(voogaEvent);
		for (Cause cause : voogaEvent.getCauses()) {
			if (cause instanceof KeyCause) {
				KeyCause keyc = (KeyCause) cause;
				if (((KeyCause) cause).getMyPressed().equals(eventmethods.getString("Press"))) {
					myKeyPressedCombos.add(keyc.getKeys());
					myKeyPressCauses.put(keyc.getKeys(), keyc);
				} else { 
					myKeyReleasedCombos.add(keyc.getKeys());
					myKeyReleaseCauses.put(keyc.getKeys(), keyc);
				}
			}
		}
		myKeyReleasedCombos.sort( (List<String> a, List<String> b) -> -(a.size() - b.size()));
		myKeyPressedCombos.sort( (List<String> a, List<String> b) -> -(a.size() - b.size()));
	}
	
	/**
	 * Clears and resets the contents of all lists and maps
	 * To be called during level transition
	 * 
	 */
	public void clearAll() {
		myEvents.clear();
		myKeyPressedCombos.clear();
		myKeyReleasedCombos.clear();
		myKeyPressCauses.clear();
		myKeyReleaseCauses.clear();
	}

	/**
	 * @return the myEvents
	 */
	public List<VoogaEvent> getEvents() {
		return myEvents;
	}

	/**
	 * @param myEvents the myEvents to set
	 */
	public void setEvents(List<VoogaEvent> myEvents) {
		this.myEvents = myEvents;
	}

	/**
	 * @return the myKeyPressedCombos
	 */
	public List<List<String>> getKeyPressedCombos() {
		return myKeyPressedCombos;
	}

	/**
	 * @param myKeyPressedCombos the myKeyPressedCombos to set
	 */
	public void setKeyPressedCombos(List<List<String>> myKeyPressedCombos) {
		this.myKeyPressedCombos = myKeyPressedCombos;
	}

	/**
	 * @return the myKeyReleasedCombos
	 */
	public List<List<String>> getKeyReleasedCombos() {
		return myKeyReleasedCombos;
	}

	/**
	 * @param myKeyReleasedCombos the myKeyReleasedCombos to set
	 */
	public void setKeyReleasedCombos(List<List<String>> myKeyReleasedCombos) {
		this.myKeyReleasedCombos = myKeyReleasedCombos;
	}

	/**
	 * @return the myKeyPressCauses
	 */
	public Map<List<String>, KeyCause> getKeyPressCauses() {
		return myKeyPressCauses;
	}

	/**
	 * @param myKeyPressCauses the myKeyPressCauses to set
	 */
	public void setKeyPressCauses(Map<List<String>, KeyCause> myKeyPressCauses) {
		this.myKeyPressCauses = myKeyPressCauses;
	}

	/**
	 * @return the myKeyReleaseCauses
	 */
	public Map<List<String>, KeyCause> getKeyReleaseCauses() {
		return myKeyReleaseCauses;
	}

	/**
	 * @param myKeyReleaseCauses the myKeyReleaseCauses to set
	 */
	public void setKeyReleaseCauses(Map<List<String>, KeyCause> myKeyReleaseCauses) {
		this.myKeyReleaseCauses = myKeyReleaseCauses;
	}

}