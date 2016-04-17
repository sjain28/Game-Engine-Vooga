package player.leveldatamanager;

import java.util.List;
import java.util.Map;

import events.KeyCause;
import events.VoogaEvent;
import javafx.scene.input.KeyEvent;
/**
 * EventManager is in charge of updating all LevelData according to
 * the Inputed Causes and Events
 * 
 * @author Krista
 *
 */
public class EventManager {
	/**
	 * updates
	 * @param leveldata
	 * @param keyclicks
	 */
	public void update(LevelData levelData, List<KeyEvent> keyClicks){
		Map<List<String>, KeyCause> keyCauses = levelData.getKeyCauses();

		checkKeys(levelData.getKeyCombos(), keyClicks, keyCauses);
		
		for(VoogaEvent e: levelData.getEvents()){
			e.update(levelData);
		}
				
		for(List<String> cause: keyCauses.keySet()){
			keyCauses.get(cause).setValue(false);
		}
	} 
	/**
	 * Checks the list of keyStrokes to see if any of the keycombos we're interested in have occurred
	 */
	private void checkKeys(List<List<String>> keyCombos, List<KeyEvent> keyClicks, Map<List<String>, KeyCause> keyCauses){

		for (List<String> eventCombo : keyCombos){ //Check all tuples
			if(keyClicks.size() < eventCombo.size()){
				continue;
			}

			for(int i = 0; i < keyClicks.size(); i++){ //Checking for a tuple in a list: Need a nested for loop :(
				boolean match = true;
				for(int j = 0; j < eventCombo.size(); j++){ //Compare the tuple to the keycombo			
					if(!((keyClicks.get(j+i).getCode().toString()).compareTo(eventCombo.get(j))==0)){
						match = false;
					}
				}
				if(match){					
					keyCauses.get(eventCombo).setValue(true);
					clearUsedKeyClick(keyClicks, i, i+eventCombo.size()-1);
					break;
				}
			}
		}
	}
	/**
	 * Removes specified KeyClick from the list once they're used for an event
	 */
	public void clearUsedKeyClick(List<KeyEvent> keyClicks, int a, int b){
		for(int i = b; i >= a; i--){
			keyClicks.remove(i);
		}
	}
	
}
