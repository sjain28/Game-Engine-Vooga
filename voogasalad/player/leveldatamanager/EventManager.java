// This entire file is part of my masterpiece.
// KRISTA OPSAHL-ONG
package player.leveldatamanager;

import java.util.List;
import java.util.Map;
import events.KeyCause;
import javafx.scene.input.KeyEvent;
/**
 * EventManager is in charge of updating all LevelData according to
 * the Inputed Causes and Events
 * 
 * @author Saumya, Krista, Hunter
 *
 */
public class EventManager implements Updatable {
	
	/**
	 * Evaluates Key Causes with inputed Key Events, and calls update
	 * on all other Events to update level logic. Once done updating,
	 * Refreshes the key causes to false once more.
	 * 
	 * @param myLevelData LevelData used to evaluate and update game logic
	 * @param keyEvents List of Keys the user has clicked
	 */
	public void update(ILevelData myLevelData){
		EventsContainer myEventsContainer = myLevelData.getEventContainer();	
		
		evaluateKeyCauses(myEventsContainer.getKeyCombos(),
				myEventsContainer.getKeyEvents(),myEventsContainer.getKeyCauses());
		myEventsContainer.getEvents().stream().forEach(e -> e.update(myLevelData));
		
		myEventsContainer.getKeyCauses().entrySet().stream()
		.forEach(entry -> entry.getValue().setValue(false));
	} 
	
	/**
	 * Identifies if any of our KeyCauses have been triggered and evaluates these causes
	 * to true or false depending on whether or not a user has inputed these keys
	 * 
	 * @param keyCombos List of Key Combinations that we are looking for
	 * @param keyEvents List of Keys the user has clicked
	 * @param keyCauses List of Causes that the Key Combinations map to
	 */
	private void evaluateKeyCauses(List<KeyCombo> keyCombos, List<KeyEvent> keyEvents, Map<KeyCombo, KeyCause> keyCauses){
		keyCombos.sort((KeyCombo k1 , KeyCombo k2)-> k1.getComboSize()-k2.getComboSize());
		
		for (KeyCombo keyCombo : keyCombos){
			List<KeyEvent> matchedKeys = keyCombo.extractMatchingKeyEvents(keyEvents);
			if(matchedKeys.size()!=0){
				keyCauses.get(keyCombo).setValue(true);
				keyEvents.removeAll(matchedKeys);
			}
		}
	}
}