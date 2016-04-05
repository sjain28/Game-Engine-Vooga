package events;

import java.util.List;

import gameengine.Sprite; 

/*
 * This class extends cause to create the specificity needed to address key causes. This will allow us to deal with
 * potential listeners and other complications that are specific to key strokes.
 */
public class KeyCause implements Cause {

	private Sprite mySprite;
	private String myKeys;
	private List<Character> keyStrokes;
	private boolean myValue;
	
	public KeyCause(Sprite sprite, String key) {
		mySprite = sprite;
		myKeys = key;
	}

	@Override
	public boolean check() {
		return myValue;
	}
	
	public String getKeys(){
		return myKeys;
	}
	
	public void setKeyStrokes(List<Character> strokes){
		keyStrokes = strokes;
	}

	public void setValue(boolean val){
		myValue = val;
	}
}
