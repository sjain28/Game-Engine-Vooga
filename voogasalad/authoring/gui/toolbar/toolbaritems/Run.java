package authoring.gui.toolbar.toolbaritems;

import authoring.UIManager;
import authoring.gui.toolbar.ToolbarItemHandler;
import player.gamedisplay.Menuable;
import player.gamerunner.GameRunner;
import resources.VoogaBundles;


public class Run extends ToolbarItemHandler {
    Save save;
    private UIManager model;
    private String game;

    public Run (Menuable model) {
        // save = new Save(model);
        this.model = (UIManager) model;
        game = VoogaBundles.preferences.getProperty("GameName");
    }

    /**
     * Defines what to do when the button is clicked
     */
    @Override
    public void handle () {
        GameRunner gameRunner = new GameRunner();
        System.out.println("Running:" + "games/" + game + "/levels/" +
                           model.getManager().getName() + ".xml");
        // gameRunner.testLevel("games/" + VoogaBundles.preferences.getProperty("GameName")
        // + "/levels/" + model.getManager().getName() + ".xml");
        gameRunner.playGame(game);
    }
}
