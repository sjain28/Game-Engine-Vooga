package authoring.gui.toolbar.toolbaritems;

import authoring.gui.toolbar.ToolbarItemHandler;
import authoring.interfaces.gui.Saveable;
import authoring.interfaces.model.CompleteAuthoringModelable;
import player.gamedisplay.Menuable;
import tools.VoogaException;


public class Save extends ToolbarItemHandler {
    Menuable manager;

    public Save (Menuable model) {
        manager = model;
        System.out.println("Manager initialized");
    }

    @Override
    public void handle () {
        System.out.println("Saving");
        manager.saveAll();
    }

}
