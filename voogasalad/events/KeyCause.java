//This entire class is part of my masterpiece
//SAUMYA JAIN
/*
 * This class is an extension of Cause whose purpose is to detect whether a certain key or combination of keys has 
 * been pressed or released. 
 * For my masterpiece I moved the checkKeys() logic into this class and removed some of its accessors and setters.
 * This class reflects good design because it is now a much more active class than it was before refactoring. Previously
 * all KeyCauses and all of their data was made visible to the EventManager, which checked each KeyCause in turn. 
 * Now, the checking logic is delegated to each KeyCause, so KeyCause is made fully responsible for updating itself.
 * 
 * Also i think that this class and its implementation demonstrate the benefits of our events API. This class itself
 * has a fairly narrowly defined purpose; it's only job is to check for one kind of key action and return true or false.
 * However, this Cause can be combined with other Causes and bundled with any combination of Effects. This shows how
 * our Events API allows the user to combine a bunch of autonomous, simple parts into potentially highly complex 
 * interactions like the ones shown in our demo.
 */
package events;

import java.util.Arrays;
import java.util.List;
import javafx.scene.input.KeyEvent;
import player.leveldatamanager.ILevelData;
import resources.VoogaBundles;

/**
 * This class extends cause to create the specificity needed to address key causes. This will allow us to deal with
 * potential listeners and other complications that are specific to key strokes.
 * @author Saumya Jain
 */
 
public class KeyCause extends Cause {

	private List<String> myKeys;
	private String myPressed; 
	
	/**
	 * Constructor
	 * @param allKeyInputs Space-separated list of keys that are relevant to this cause
	 * @param pressStatus The type of KeyEvent that this cause triggers - either Press or Release
	 * @param voogaEvent Event that this Cause belongs to
	 */
	public KeyCause(String allKeyInputs, String pressStatus, VoogaEvent voogaEvent) {
		super(voogaEvent);
		myKeys = Arrays.asList(allKeyInputs.split("\\s+"));
		myPressed = pressStatus;
	}
	
	/**
	 * Returns myValue
	 * Since information about keyevents is held outside of this class, the value of this class is determined elsewhere
	 * This method simply returns the value that is determined for it
	 */
	@Override
	public boolean check(ILevelData data) {
		
		if(myPressed.equals(VoogaBundles.EventMethods.getString("Press"))){
			return checkKeys(data.getPresses());
		}
		return checkKeys(data.getReleases());
	}
	
	/**
	 * Checks a list of keyClicks to see if the keys relevant to this Cause have occured
	 */
	private boolean checkKeys(List<KeyEvent> keyClicks){
				
		if(keyClicks.size() < myKeys.size()) {
			return false;
		}
			
		for (int i = 0; i < keyClicks.size(); i++) { 
			for(int j = 0; j < myKeys.size(); j++) { //Compare the tuple to the keycombo			
				if(!((keyClicks.get(j+i).getCode().toString()).equals(myKeys.get(j)))) {
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * Getters and setters below.
	 */
	
	public List<String> getKeys(){
		return myKeys;
	}

	public String getMyPressed() {
		return myPressed;
	}
	
	@Override
	public String toString(){
		String result = "Checking if the following keys have been " + myPressed + ": ";
		for(String a: myKeys){
			result += " "+a;
		}
		return result;
	}
}
