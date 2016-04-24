package authoring.splash;

import authoring.Command;
import javafx.scene.control.Button;
import player.gamerunner.GameRunner;
import player.gamerunner.IGameRunner;

public class PlayGameCommand implements Command {

	@Override
	public void execute() {
		ProjectOpenPrompt prompt = new ProjectOpenPrompt();
		prompt.setProceedEvent(ee -> {
			prompt.close();
			String name = ((Button) ee.getSource()).getId();
			IGameRunner gameRunner = new GameRunner();
	        gameRunner.playGame(name);
		});
		prompt.show();
	}
	
}