package player.gamedisplay;

import java.util.List;
import javafx.scene.Node;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 * Interface for GameDisplay
 * 
 * @author Hunter Lee
 *
 */
public interface IGameDisplay {
	
	void read(List<Node> listToDisplay);
	
	void display();
	
	void displayTestMode();
	
	void populateGameScreen();
	
	void createPrompt(String message);
	
	List<KeyEvent> getKeyEvents();
	
	List<KeyEvent> getMyKeyPresses();
	
	List<KeyEvent> getMyKeyReleases();
	
	void clearKeyEvents();
		
	Stage getStage();
	
	void exit();

}
