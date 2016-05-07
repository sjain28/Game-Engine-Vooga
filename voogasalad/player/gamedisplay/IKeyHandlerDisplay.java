package player.gamedisplay;

import java.util.List;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public interface IKeyHandlerDisplay {
	EventHandler<KeyEvent> createKeyListener() ;
	List<KeyEvent> getKeyEvents();
	List<KeyEvent> getMyKeyPresses();
	List<KeyEvent> getMyKeyReleases();
	void clearKeyEvents();
}
