package authoring.gui.toolbar.toolbaritems;

import authoring.UIManager;
import authoring.gui.levelpreferences.FinishLinePrompt;
import authoring.gui.toolbar.ToolbarItemHandler;
import authoring.model.ElementManager;
import player.gamedisplay.Menuable;
import tools.VoogaNumber;


public class FinishLine extends ToolbarItemHandler {

    private ElementManager manager;

    public FinishLine (Menuable model) {
        manager = (ElementManager) ((UIManager) model).getManager();
    }

    /**
     * Defines what to do when the button is clicked
     */
    @Override
    public void handle () {
        FinishLinePrompt finishLinePrompt = new FinishLinePrompt();
        finishLinePrompt.setProceedListener(e -> {
            manager.getGlobalVariables()
                    .put(manager.getName() + "EndX",
                         new VoogaNumber(finishLinePrompt.getCoords().getFirst()));
            manager.getGlobalVariables()
                    .put(manager.getName() + "EndY",
                         new VoogaNumber(finishLinePrompt.getCoords().getLast()));
            finishLinePrompt.close();
        });
        finishLinePrompt.show();
    }

}
