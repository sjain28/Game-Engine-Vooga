package player.gui;

/**
 * Interface for GameDisplay
 * 
 * @author Hunter Lee
 *
 */
public interface IGameDisplay {
	
	IPromptFactory myPromptFactory = new PromptFactory();
	
	IControl myControl = new StandardControl();
	
	void read();
	
	void display();
	
	void createPrompt(String message);
	
}
