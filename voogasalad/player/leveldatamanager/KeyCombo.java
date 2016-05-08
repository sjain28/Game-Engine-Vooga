// This entire file is part of my masterpiece.
// KRISTA OPSAHL-ONG
package player.leveldatamanager;

import java.util.ArrayList;
import java.util.List;
import javafx.event.EventType;
import javafx.scene.input.KeyEvent;

public class KeyCombo {
	private List<String> myKeyTypes;
	private EventType<?> myEventType;
	/**
	 * Constructor
	 * @param keyTypes
	 * @param eventType
	 */
	public KeyCombo(List<String> keyTypes, EventType<?> eventType){
		myKeyTypes = keyTypes;
		myEventType = eventType;
	}
	/**
	 * Returns a list of the first matching KeyEvents if Key events happen to 
	 * match with the KeyCombo. If they do not, an empty list is returned.
	 * 
	 * @return List<KeyEvent> The list of matched events
	 */
	public List<KeyEvent> extractMatchingKeyEvents(List<KeyEvent> keyEvents){
		List<KeyEvent> matchedEvents = new ArrayList<KeyEvent>();
		for(int i = 0; i < keyEvents.size(); i++) {
			if(keysMatch(keyEvents.get(i),myKeyTypes.get(matchedEvents.size()))){
				matchedEvents.add(keyEvents.get(i));
				if(matchedEvents.size()==myKeyTypes.size()){ break; }
			}
			else{
				matchedEvents.clear(); 
				break;
			}
		}
		return matchedEvents;
	}
	/**
	 * Check if a key event and combo key match
	 * @param keyEvent
	 * @param key
	 * @return Matching result
	 */
	private boolean keysMatch(KeyEvent keyEvent, String key){
		if(!keyEvent.getCode().toString().equals(key)){return false;}
		if(!keyEvent.getEventType().equals(myEventType)){return false;}
		return true;
	}
	/**
	 * Returns keyCombo's size - used for sorting purposes
	 * 
	 * @return Size of Combo
	 */
	public int getComboSize(){
		return myKeyTypes.size();
	}
}
