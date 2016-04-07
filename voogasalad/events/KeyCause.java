package events;

/*
 * This class extends cause to create the specificity needed to address key causes. This will allow us to deal with
 * potential listeners and other complications that are specific to key strokes.
 */
public class KeyCause extends Cause {

	private String myKeys;
	private boolean myValue;
	
	public KeyCause(String key, Event event) {
		super(event);
		myKeys = key;
	}

	@Override
	public boolean check() {
		return myValue;
	}
	
	public String getKeys(){
		return myKeys;
	}

	public void setValue(boolean val){
		myValue = val;
	}
}
