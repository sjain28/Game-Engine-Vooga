package authoring.splash;


import authoring.Command;
import javafx.scene.control.Button;
import player.gamerunner.GameRunner;
import player.gamerunner.IGameRunner;
import resources.VoogaBundles;
import stats.database.VoogaDataBase;


public class OpenCommand implements Command {
    /**
     * Executes the command to open up a particular loaded game
     */
    @Override
    public void execute () {
        ProjectBrowseTypePrompt prompt = new ProjectBrowseTypePrompt();
        prompt.setProceedEvent(e -> {
            prompt.close();
            // TODO: use reflection to open the correct one instead of if statements
            if (((Button) e.getSource()).getId().equals("OpenAll")) {
                showPlayChoicePrompt(new ProjectOpenAllPrompt());
            }
            else if (((Button) e.getSource()).getId().equals("OpenBySearch")) {
                showPlayChoicePrompt(new ProjectOpenBySearchPrompt());
            }
        });
        prompt.show();
    }

    private void showPlayChoicePrompt (StarterPrompt projectPrompt) {
        projectPrompt.setProceedEvent(ee -> {
            projectPrompt.close();
            String name = ((Button) ee.getSource()).getId();
            // set the current gamename to the game being played
            VoogaBundles.preferences.setProperty("GameName", name);
            VoogaDataBase.getInstance().checkThenAddIfNewGame(name, "dragons!!");
            IGameRunner gameRunner = new GameRunner();
            gameRunner.playGame(name);
        });
        projectPrompt.show();
    }

}
