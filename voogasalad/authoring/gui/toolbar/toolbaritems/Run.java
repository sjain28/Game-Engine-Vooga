package authoring.gui.toolbar.toolbaritems;

import java.io.FileNotFoundException;
import java.io.IOException;
import authoring.gui.toolbar.ToolbarItemHandler;
import authoring.interfaces.gui.Saveable;
import authoring.interfaces.model.CompleteAuthoringModelable;
import auxiliary.VoogaAlert;
import player.gamedisplay.GameDisplayTester;
import player.gamerunner.GameRunner;


public class Run extends ToolbarItemHandler {
    Save save;
    
    public Run (CompleteAuthoringModelable model) {
        save = new Save(model);
    }

    @Override
    public void handle () {
        save.handle();
        // new GameDisplayTester();
        try {
            GameRunner gameRunner = new GameRunner("levels/Test.xml");
            gameRunner.playLevel("levels/Test.xml");
        }
        catch (FileNotFoundException e) {
            new VoogaAlert("Can't Run this level");
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            new VoogaAlert("Can't Run this level");
        }

    }

}
