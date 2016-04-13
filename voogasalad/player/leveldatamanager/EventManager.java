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
	public void checkKeys(){
		for (List<String> keyCombo : keyCombos){
			if(keyStrokes.size() < keyCombo.size()){
				continue;
			}
			for(int i = 0; i < keyStrokes.size()-1; i++){ //Loop over all tuples in keystrokes
				boolean match = true;
				for(int j = 0; j < keyCombo.size(); j++){ //Compare the tuple to the keycombo
					if(!keyCombo.get(j).equals(keyStrokes.get(j+i))){
						match = false;
					}
				}
				if(match){
					keyCauses.get(keyCombo).setValue(true);
					break;
				}
			}
		}
	}	
	
	/**
	 * Tests if String a is a rearranged version of String b
	 * Precondition: Strings must be same length
	 * Precondition: Strings must NOT have repeated characters
	 */
	public boolean checkEquivalent(String a, String b){

		for(int i = 0; i < a.length(); i++){
			if(!b.contains(a.substring(i,i+1))){
				return false;
			}
		}
		return true;
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
