package authoring.splash;

import authoring.Command;
import javafx.scene.control.Button;
import player.gamerunner.GameRunner;
import player.gamerunner.IGameRunner;

public class PlayGameCommand implements Command {

    /**
     * excecuts opening the all project prompt
     */
	@Override
	public void execute() {
		ProjectOpenAllPrompt prompt = new ProjectOpenAllPrompt();
		prompt.setProceedEvent(ee -> {
			prompt.close();
			String name = ((Button) ee.getSource()).getId();
			IGameRunner gameRunner = new GameRunner();
	        gameRunner.playGame(name);
		});
		prompt.show();
	}
	
}
