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
public class EventManager {
	
	/**
	 * Release keys getting triggered by key press
	 * Release keys not getting triggered by key release
	 * 
	 * @param myLevelData
	 * @param keyPresses
	 * @param keyReleases
	 */
	public void update(ILevelData myLevelData, List<KeyEvent> keyPresses, List<KeyEvent> keyReleases){
		
		KeyEventContainer myKeyEventContainer = myLevelData.getKeyEventContainer();	
		checkKeys(myKeyEventContainer.getKeyPressedCombos(), keyPresses, 
				  											 myKeyEventContainer.getKeyPressCauses());
		checkKeys(myKeyEventContainer.getKeyReleasedCombos(), keyReleases, 
				  										      myKeyEventContainer.getKeyReleaseCauses());
		
		for(int i = 0; i < myKeyEventContainer.getEvents().size(); i++) {
			myKeyEventContainer.getEvents().get(i).update(myLevelData);
		}
						
		for(List<String> cause : myKeyEventContainer.getKeyReleaseCauses().keySet()) {
			myKeyEventContainer.getKeyReleaseCauses().get(cause).setValue(false);
		}
		for(List<String> cause : myKeyEventContainer.getKeyPressCauses().keySet()) {
			myKeyEventContainer.getKeyPressCauses().get(cause).setValue(false);
		}
	} 
	
	/**
	 * Checks the list of keyStrokes to see if any of the keycombos we're interested in have occurred
	 * 
	 */
	private void checkKeys(List<List<String>> keyCo, List<KeyEvent> keyClicks, Map<List<String>, KeyCause> keyCauses){
				
		for (List<String> eventCombo : keyCo){ //Check all tuples
			if(keyClicks.size() < eventCombo.size()) {
				continue;
			}
			for (int i = 0; i < keyClicks.size(); i++) { 
				boolean match = true;
				for(int j = 0; j < eventCombo.size(); j++) { //Compare the tuple to the keycombo			
					if(!((keyClicks.get(j+i).getCode().toString()).equals(eventCombo.get(j)))) {
						match = false;
					}
				}
				if (match) {	
					keyCauses.get(eventCombo).setValue(true);
					clearUsedKeyClick(keyClicks, i, i+eventCombo.size()-1);
					break;
				}
			}
		}
	}

	/**
	 * Removes specified KeyClick from the list once they're used for an event
	 * 
	 */
	public void clearUsedKeyClick(List<KeyEvent> keyClicks, int a, int b){
		for(int i = b; i >= a; i--){
			keyClicks.remove(i);
		}
	}
}