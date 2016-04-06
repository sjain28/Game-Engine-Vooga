package gameengine;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import events.Cause;
import events.Event;
import events.KeyCause;

//IDEA: Move all the keyStroke related stuff into a keyHandler class
//Possible Issue: If up+down is typed but not exactly simultaneously, they might get interpreted separately. 

//TODO: 
//Figure out how close key presses have to be to one another time-wise to be considered simultaneous	 
/*
 * This class will hold all of the game events and handle updating the game accordingly.
 */
public class EventManager {

	private List<Character> keyStrokes; //Still need to add a listener to update this list with new keystrokes!
	private List<Event> myEvents;
	private List<String> keyCombos;
	private Map<String, KeyCause> keyCauses;
	
	public EventManager() {
		keyStrokes = new ArrayList<Character>();
		myEvents = new ArrayList<Event>();
		keyCauses = new TreeMap<String, KeyCause>();
	}
	
	public void update(){
		checkKeys();
		for(Event e: myEvents){
			e.update();
		}
		
		for(String cause: keyCauses.keySet()){
			keyCauses.get(cause).setValue(false);
		}
	}
	
	public void addEvent(Event event){
		for(Cause c: event.getCauses()){
			if(c instanceof KeyCause){
				KeyCause keyc = (KeyCause) c;
				keyCauses.put(keyc.getKeys(), keyc); 
				keyCombos.add(keyc.getKeys()); 
				keyCombos.sort((String a, String b) -> -a.length() - b.length());
			}
		}
		myEvents.add(event);
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
	 * Precondition: Strings must NOT have repeated characterx
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
