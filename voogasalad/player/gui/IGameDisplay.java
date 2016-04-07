package player.gui;

/**
 * Interface for GameDisplay
 * 
 * @author Hunter Lee
 *
 */
public interface IGameDisplay {
	
	IPromptFactory myPromptFactory = new PromptFactory();
	
//	IPromptFactory getPromptFactory();
	
	IControl myControl = new StandardControl();
	
//	IControl getControl();
	
	void read();
	
	void display();
	
	void createPrompt(String message);
	

	
}
