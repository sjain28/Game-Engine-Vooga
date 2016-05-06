package authoring.gui.toolbar.toolbaritems;

import authoring.UIManager;
import authoring.gui.toolbar.ToolbarItemHandler;
import player.gamedisplay.Menuable;
import player.gamerunner.GameRunner;
import resources.VoogaBundles;


public class RunDebug extends ToolbarItemHandler {
    Save save;
    private UIManager model;

    public RunDebug (Menuable model) {
        // save = new Save(model);
        this.model = (UIManager) model;
    }

    /**
     * Defines what to do when the button is clicked
     */
    @Override
    public void handle () {
        GameRunner gameRunner = new GameRunner();
        gameRunner.testLevel("games/" + VoogaBundles.preferences.getProperty("GameName") +
                             "/levels/" + model.getManager().getName() + ".xml");
    }
}
