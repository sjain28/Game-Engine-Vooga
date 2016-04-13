package player.leveldatamanager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import events.Cause;
import events.VoogaEvent;
import javafx.scene.input.KeyEvent;
import events.KeyCause;

//Possible Issue: If up+down is typed but not exactly simultaneously, they might get interpreted separately. 

//TODO: 
//Figure out how close key presses have to be to one another time-wise to be considered simultaneous
//Move all the keyStroke related stuff into a keyHandler class
//Still need to add a listener to update this list with new keystrokes! This will likely go in one of the Managers/Controllers.

/*
 * This class will hold all of the game events and handle updating the game accordingly.
 */
public class EventManager {

	private List<String> keyStrokes; //List of all key things that have happened
	private List<VoogaEvent> myEvents;
	private List<List<String>> keyCombos; //List of keycombos that are bound to events, sorted by length
	private Map<List<String>, KeyCause> keyCauses; //Maps Strings 
	private ObjectManager myEngineManager;
	
	public EventManager(ObjectManager manager, List<VoogaEvent> events) {
		keyStrokes = new ArrayList<>();
		myEvents = events;
		keyCauses = new TreeMap<List<String>, KeyCause>();
		myEngineManager = manager;
		keyCombos = new ArrayList<List<String>>();
	}
	
	public void update(){
		
		for(KeyEvent k: (List<KeyEvent>) myEngineManager.getKeyEvents()){
			keyStrokes.add(k.toString());
		}
				
		checkKeys();
		for(VoogaEvent e: myEvents){
			e.update();
		}
		
		for(List<String> cause: keyCauses.keySet()){
			keyCauses.get(cause).setValue(false);
		}
		
		keyStrokes.clear();
	}

	public void addEvent(VoogaEvent voogaEvent){
		for(Cause c: voogaEvent.getCauses()){
			if(c instanceof KeyCause){
				KeyCause keyc = (KeyCause) c;
				keyCauses.put(keyc.getKeys(), keyc); 
				keyCombos.add(keyc.getKeys()); 
				keyCombos.sort((List<String> a, List<String> b) -> -a.size() - b.size());
			}
		}

		voogaEvent.setManager(myEngineManager);
		myEvents.add(voogaEvent);
	}
	
	/**
	 * Checks the list of keyStrokes to see if any of the keycombos we're interested in have occurred
	 */
	private void checkKeys(){
		for (List<String> eventCombo : keyCombos){ //Check all tuples
			if(keyStrokes.size() < eventCombo.size()){
				continue;
			}
			for(int i = 0; i < keyStrokes.size()-1; i++){ //Checking for a tuple in a list: Need a nested for loop :(
				boolean match = true;
				for(int j = 0; j < eventCombo.size(); j++){ //Compare the tuple to the keycombo
					if(!eventCombo.get(j).equals(keyStrokes.get(j+i))){
						match = false;
					}
				}
				if(match){
					keyCauses.get(eventCombo).setValue(true);
					clearStrokes(i, i+eventCombo.size()-1);
					break;
				}
			}
		}
	}	
	
	/**
	 * Removes keystrokes from the list once they're used for an event
	 */
	public void clearStrokes(int a, int b){
		for(int i = b; i <= a; i--){
			keyStrokes.remove(i);
		}
	}

}
