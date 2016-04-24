package events;

import java.util.Arrays;
import java.util.List;

import player.leveldatamanager.ILevelData;

/*
 * This class extends cause to create the specificity needed to address key causes. This will allow us to deal with
 * potential listeners and other complications that are specific to key strokes.
 * 
 * This cause returns true if its keys are being pressed. 
 * NOTE: In some cases (ex. movement) user will want to reset someting once this cause is no longer true. 
 * User should be prompted to add an additional event that's prompted by a keyRelease and resets. 
 */
 
public class KeyCause extends Cause {

	private List<String> myKeys;
	private boolean myValue;
	private String myPressed = "press"; //can be the strings press or release
	
	public KeyCause(String allKeyInputs, String pressStatus, VoogaEvent voogaEvent) {
		super(voogaEvent);
		myKeys = Arrays.asList(allKeyInputs.split("\\s+"));
		myPressed = pressStatus;
	}
	
	public KeyCause(String allKeyInputs,VoogaEvent voogaEvent) {
		super(voogaEvent);
		myKeys = Arrays.asList(allKeyInputs.split("\\s+"));
	}

	@Override
	public boolean check(ILevelData data) {
		if(myValue)
			System.out.println("KEY: " + myKeys.get(0));
		return myValue;
	}
	
	public List<String> getKeys(){
		return myKeys;
	}

	public void setValue(boolean val){
		myValue = val;
	}
	
	@Override
	public String toString(){
		String result = "Checking if the following keys have been pressed: ";
		for(String a: myKeys){
			result += " "+a;
		}
		return result;
	}

	public String getMyPressed() {
		return myPressed;
	}
}
