package events;

import java.util.Arrays;
import java.util.List;

import player.leveldatamanager.ILevelData;

/*
 * This class extends cause to create the specificity needed to address key causes. This will allow us to deal with
 * potential listeners and other complications that are specific to key strokes.
 */
public class KeyCause extends Cause {

	private List<String> myKeys;
	private boolean myValue;
	
	public KeyCause(String allKeyInputs, VoogaEvent voogaEvent) {
		super(voogaEvent);
		myKeys = Arrays.asList(allKeyInputs.split("\\s+"));
	}

	@Override
	public boolean check(ILevelData data) {
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
}
