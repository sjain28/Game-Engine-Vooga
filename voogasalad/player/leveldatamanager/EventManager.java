package player.leveldatamanager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

	//private List<String> keyStrokes; //List of all key things that have happened
	private List<KeyEvent> myKeyEvents; //List of all key things that have happened

	private List<VoogaEvent> myEvents;
	private List<List<String>> keyCombos; //List of keycombos that are bound to events, sorted by length
	private Map<List<String>, KeyCause> keyCauses; //Maps Strings 
	private ObjectManager myEngineManager;

	public EventManager(ObjectManager manager, List<VoogaEvent> events) {
		myKeyEvents = new ArrayList<KeyEvent>();
		myEvents = new ArrayList<VoogaEvent>();
		keyCauses = new HashMap<List<String>, KeyCause>();
		myEngineManager = manager;
		keyCombos = new ArrayList<List<String>>();
		
		//add events
		for(VoogaEvent e : events){
			addEvent(e);
		}
	}

	public void update(){

//		for(KeyEvent k: (List<KeyEvent>) myEngineManager.getKeyEvents()){
//			if(!keyStrokes.contains(k.toString())){
//				keyStrokes.add(k.toString());
//			}
//		}
				
		checkKeys();
		
		for(VoogaEvent e: myEvents){
			e.update();
		}

		for(List<String> cause: keyCauses.keySet()){
			keyCauses.get(cause).setValue(false);
		}

		//keyStrokes.clear();
	}

	public void addEvent(VoogaEvent voogaEvent){
		voogaEvent.setManager(myEngineManager);
		myEvents.add(voogaEvent);
		
		for(Cause c: voogaEvent.getCauses()){
			c.init();
			if(c instanceof KeyCause){
				KeyCause keyc = (KeyCause) c;
	
				keyCauses.put(keyc.getKeys(), keyc); 
				keyCombos.add(keyc.getKeys()); 
				keyCombos.sort((List<String> a, List<String> b) -> -a.size() - b.size());
			}
		}
	}

	/**
	 * Checks the list of keyStrokes to see if any of the keycombos we're interested in have occurred
	 */
	private void checkKeys(){

		for (List<String> eventCombo : keyCombos){ //Check all tuples
			if(myKeyEvents.size() < eventCombo.size()){
				continue;
			}

			for(int i = 0; i < myKeyEvents.size(); i++){ //Checking for a tuple in a list: Need a nested for loop :(
				boolean match = true;
				for(int j = 0; j < eventCombo.size(); j++){ //Compare the tuple to the keycombo			
					if(!((myKeyEvents.get(j+i).getCode().toString()).compareTo(eventCombo.get(j))==0)){
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
		for(int i = b; i >= a; i--){
			myKeyEvents.remove(i);
		}
	}
	
	public void setKeyStrokes(List<?> keyevents){
		List<KeyEvent> tempevents = (List<KeyEvent>) keyevents;
		myKeyEvents = new ArrayList<KeyEvent>(tempevents);
	}

}
