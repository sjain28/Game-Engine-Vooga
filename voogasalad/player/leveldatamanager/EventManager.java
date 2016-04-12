package player.leveldatamanager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import events.Cause;
import events.VoogaEvent;
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

	private List<Character> keyStrokes; 
	private List<VoogaEvent> myEvents;
	private List<String> keyCombos;
	private Map<String, KeyCause> keyCauses;
	private EngineObjectManager myEngineManager;
	
	public EventManager(EngineObjectManager manager, List<VoogaEvent> events) {
		keyStrokes = new ArrayList<Character>();
		myEvents = events;
		keyCauses = new TreeMap<String, KeyCause>();
		myEngineManager = manager;
		keyCombos = new ArrayList<String>();
	}
	
	public void update(){
		checkKeys();
		for(VoogaEvent e: myEvents){
			e.update();
		}
		
		for(String cause: keyCauses.keySet()){
			keyCauses.get(cause).setValue(false);
		}
	}
	

	public void addEvent(VoogaEvent voogaEvent){
		for(Cause c: voogaEvent.getCauses()){
			if(c instanceof KeyCause){
				KeyCause keyc = (KeyCause) c;
				keyCauses.put(keyc.getKeys(), keyc); 
				keyCombos.add(keyc.getKeys()); 
				keyCombos.sort((String a, String b) -> -a.length() - b.length());
			}
		}

		voogaEvent.setManager(myEngineManager);
		myEvents.add(voogaEvent);
	}
	
	/**
	 * Checks the list of keyStrokes to see if any of the keycombos we're interested in have occurred
	 */
	public void checkKeys(){
		for (String keyCombo : keyCombos){
			for(int i = 0; i < keyStrokes.size() - keyCombo.length(); i++){
				String temp = keyCombo.substring(i, i+keyCombo.length());
				if(checkEquivalent(temp, keyCombo)){
					clearStrokes(i, i+keyCombo.length());
					keyCauses.get(keyCombo).setValue(true);
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
