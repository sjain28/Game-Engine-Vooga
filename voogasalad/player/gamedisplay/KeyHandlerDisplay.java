package player.gamedisplay;

import java.util.ArrayList;
import java.util.List;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public class KeyHandlerDisplay implements IKeyHandlerDisplay{


	private List<KeyEvent> myKeyEvents;
	private List<KeyEvent> myKeyPresses;
	private List<KeyEvent> myKeyReleases;

	public KeyHandlerDisplay(){
		myKeyEvents = new ArrayList<>();
		myKeyPresses = new ArrayList<>();
		myKeyReleases = new ArrayList<>();}	
	/**
	 * Creates a keyListener for listening in on key inputs and adds each event to the list
	 */
	public EventHandler<KeyEvent> createKeyListener(){
		return new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				myKeyEvents.add(event);
				if (event.getEventType().equals(KeyEvent.KEY_PRESSED)) {
					myKeyPresses.add(event);
				} else {
					myKeyReleases.add(event);
				}
			}
		};
	}
	
	@Override
	public List<KeyEvent> getKeyEvents() {
		return myKeyEvents;
	}

	public List<KeyEvent> getMyKeyPresses() {
		return myKeyPresses;
	}

	public List<KeyEvent> getMyKeyReleases() {
		return myKeyReleases;
	}
	
	@Override
	public void clearKeyEvents() {
		myKeyEvents.clear();
	}


}
