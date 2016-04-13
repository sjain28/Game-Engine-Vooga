package player.leveldatamanager;

import java.util.ArrayList;
import java.util.HashMap;
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
		
		//System.out.println("My keystrokes : "+keyStrokes.size());
		
		checkKeys();
		
		for(VoogaEvent e: myEvents){
			System.out.println("updating event "+e);
			System.out.println("updating event "+e.getCauses().get(0).check());
			System.out.println("Cause 1: "+e.getCauses().get(0));

			e.update();
		}

		for(List<String> cause: keyCauses.keySet()){
			keyCauses.get(cause).setValue(false);
		}

		//keyStrokes.clear();
	}

	public void addEvent(VoogaEvent voogaEvent){
		//System.out.println("in add event");
		for(Cause c: voogaEvent.getCauses()){
			if(c instanceof KeyCause){
				//System.out.println("Cause 1: "+c);
				KeyCause keyc = (KeyCause) c;
				//System.out.println("Cause 2: "+keyc);
	
				keyCauses.put(keyc.getKeys(), keyc); 
				keyCombos.add(keyc.getKeys()); 
				keyCombos.sort((List<String> a, List<String> b) -> -a.size() - b.size());
			}
		}

		voogaEvent.setManager(myEngineManager);
		//System.out.println(voogaEvent);
		myEvents.add(voogaEvent);
		//System.out.println("myEvents.size after add event"+myEvents.size());

	}

	/**
	 * Checks the list of keyStrokes to see if any of the keycombos we're interested in have occurred
	 */
	private void checkKeys(){
		//myKeyEvents -> my list of events that has actually happened
		//keycombos -> list of events that are programmed to need to happen
		//System.out.println("my key events:");
		for(KeyEvent keyevent : myKeyEvents){
			//System.out.println(keyevent.getCode());
		}
		
		//System.out.println("my event combos:");
		for(List<String> eventCombo : keyCombos){
			for(String s : eventCombo){
				//System.out.println(s);
			}
		}
		
		//System.out.println("checking keys in event manager: "+myKeyEvents.size());
		for (List<String> eventCombo : keyCombos){ //Check all tuples
			//System.out.println("Size of keycombos: "+keyCombos.size());
			if(myKeyEvents.size() < eventCombo.size()){
				continue;
			}

			for(int i = 0; i < myKeyEvents.size(); i++){ //Checking for a tuple in a list: Need a nested for loop :(
				//System.out.println("Checking key event: "+myKeyEvents.get(i).toString());

				boolean match = true;
				for(int j = 0; j < eventCombo.size(); j++){ //Compare the tuple to the keycombo
					//System.out.println("CHECKING: "+myKeyEvents.get(j+i).getCode()+ "WITH COMBO EVENT: "+eventCombo.get(j));
					
					//System.out.println(myKeyEvents.get(j+i).getCode().toString());
					//System.out.println(eventCombo.get(j));

					//System.out.println((myKeyEvents.get(j+i).getCode().toString()).compareTo(eventCombo.get(j))==0);
					
					if(!((myKeyEvents.get(j+i).getCode().toString()).compareTo(eventCombo.get(j))==0)){
						//System.out.println("here - they didn't match");
						match = false;
					}
				}
				if(match){
					//System.out.println("Math with: "+myKeyEvents.get(i).toString());
					
					keyCauses.get(eventCombo).setValue(true);
					//System.out.println("checking if keycause has been set to true: "+keyCauses.get(eventCombo).check());
					//System.out.println("Cause 1: "+keyCauses.get(eventCombo));

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
		//System.out.println("Setting key events in event manager to be :"+keyevents.size());
		List<KeyEvent> tempevents = (List<KeyEvent>) keyevents;
		myKeyEvents = new ArrayList<KeyEvent>(tempevents);
	}

}
