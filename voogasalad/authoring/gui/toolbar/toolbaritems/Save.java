package authoring.gui.toolbar.toolbaritems;

import authoring.gui.toolbar.ToolbarItemHandler;
import authoring.interfaces.gui.Saveable;
import authoring.interfaces.model.CompleteAuthoringModelable;
import tools.VoogaException;


public class Save extends ToolbarItemHandler {
    Saveable manager;

    public Save (CompleteAuthoringModelable model) {
        manager = model;
        System.out.println("Manager initialized");
    }

    @Override
    public void handle () {
        System.out.println("Saving");
        try {
            manager.onSave();
        }
        catch (VoogaException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
