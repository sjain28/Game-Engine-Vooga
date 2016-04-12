package player.gamedisplay;

import java.util.List;
import javafx.scene.Node;

/**
 * Interface for GameDisplay
 * 
 * @author Hunter Lee
 * @param <E>
 *
 */
public interface IGameDisplay {
	
	IPromptFactory myPromptFactory = new PromptFactory();
		
	IControl myControl = new StandardControl();
		
	IHUD myHUD = new StandardHUD();
	
	void read(List<Node> listToDisplay);
	
	void display();
	
	void createPrompt(String message);
	
}
