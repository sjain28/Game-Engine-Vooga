package authoring.gui.toolbar.toolbaritems;

import authoring.gui.toolbar.ToolbarItemHandler;
import player.gamedisplay.Menuable;


public class Save extends ToolbarItemHandler {
    Menuable manager;

    public Save (Menuable model) {
        manager = model;
    }

    @Override
    public void handle () {
        manager.saveAll();
    }

}
