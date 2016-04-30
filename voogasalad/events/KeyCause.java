package events;

import java.util.Arrays;
import java.util.List;

import player.leveldatamanager.ILevelData;

/**
 * This class extends cause to create the specificity needed to address key causes. This will allow us to deal with
 * potential listeners and other complications that are specific to key strokes.
 * @author Saumya Jain
 */
 
public class KeyCause extends Cause {

	private List<String> myKeys;
	private boolean myValue;
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
		return myValue;
	}
	
	/**
	 * Getters and setters below.
	 */
	
	public List<String> getKeys(){
		return myKeys;
	}

	public void setValue(boolean val){
		myValue = val;
	}
	
	@Override
	public String toString(){
		String result = "Checking if the following keys have been " + myPressed + ": ";
		for(String a: myKeys){
			result += " "+a;
		}
		return result;
	}

	public String getMyPressed() {
		return myPressed;
	}
}
