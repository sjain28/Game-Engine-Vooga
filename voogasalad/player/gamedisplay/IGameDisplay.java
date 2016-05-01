package player.gamedisplay;

import java.util.List;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import tools.Pair;

/**
 * Interface for GameDisplay
 * 
 * @author Hunter Lee
 *
 */
public interface IGameDisplay {
	
	void readAndPopulate(List<Pair<Node, Boolean>> listToDisplay);

	void display();
	
	void displayTestMode();
		
	void createPrompt(String message);
	
	List<KeyEvent> getKeyEvents();
	
	List<KeyEvent> getMyKeyPresses();
	
	List<KeyEvent> getMyKeyReleases();
	
	void clearKeyEvents();
	
	Pair<Double, Double> getDimensions();
		
	Stage getStage();
	
	Pane getScreen();
	
	Pane getUI();
	
	void exit();

	Scene getMyScene();
	
	void setSceneDimensions(double width, double height);

}
