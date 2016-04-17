package player.gamedisplay;

import java.util.List;
import javafx.scene.Node;
import javafx.scene.input.KeyEvent;

/**
 * Interface for GameDisplay
 * 
 * @author Hunter Lee
 * @param <E>
 *
 */
public interface IGameDisplay {
	
	void read(List<Node> listToDisplay);
	
	void display();
	
	void populateGameScreen();
	
	void createPrompt(String message);
	
	List<KeyEvent> getKeyClicks();
	
	void clearKeyClicks();
	
}
