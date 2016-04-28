/**
 * 
 */
package player.leveldatamanager;

import java.util.List;
import java.util.Map;

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
	 * Clears and resets the contents of all lists and maps
	 * To be called when transitioning levels
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

