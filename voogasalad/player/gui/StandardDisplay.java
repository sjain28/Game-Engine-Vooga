/**
 * 
 */
package player.gui;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

/**
 * Standard Display that creates a display with basic user-interaction controls
 * Uses composition to contain elements of the display
 * 
 * @author Hunter Lee
 *
 */
public class StandardDisplay implements IGameDisplay {
	
	private Pane myGameScreen;
	private PromptFactory myPrompt;
	
	public StandardDisplay() {
		
		myGameScreen = new BorderPane();
		myPrompt = new PromptFactory();
		
	}

	@Override
	public void read() {
		// Auto-generated method stub
		//TODO: What is this reading? Format of the file being read(Sprites)?
	}

	@Override
	public void display() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createPrompt(String message) {
		
		getPrompt().prompt(message);
		
	}
	
	private Pane createDisplay() {
		
		return myGameScreen;
		
	}

	/**
	 * @return the myGameScreen
	 */
	public Pane getGameScreen() {
		return myGameScreen;
	}

	/**
	 * @return the myPrompt
	 */
	public PromptFactory getPrompt() {
		return myPrompt;
	}
}
