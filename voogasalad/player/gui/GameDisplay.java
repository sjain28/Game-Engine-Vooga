package Player.gui;

import Player.leveldatamanager.LevelDataManager;

public interface GameDisplay {
	
	void read();
	
	void display(LevelDataManager levelManager);
	
	
	void update();

	void createPrompt();
	
}
