package authoring.gui.toolbar.toolbaritems;

import authoring.gui.toolbar.ToolbarItemHandler;
import player.gamedisplay.Menuable;


public class Save extends ToolbarItemHandler {
    Menuable manager;

    
    /**
     * Initializes button takes in the backend interface
     * @param model
     */
    public Save (Menuable model) {
        manager = model;
        System.out.println("Manager initialized");
    }

    /**
     * Defines what to do when the button is clicked
     */
    @Override
    public void handle () {
        System.out.println("Saving");
        manager.saveAll();
    }

}
