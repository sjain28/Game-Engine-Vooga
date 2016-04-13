package authoring.gui.toolbar.toolbaritems;

import authoring.gui.toolbar.ToolbarItemHandler;
import authoring.interfaces.gui.Saveable;
import authoring.interfaces.model.CompleteAuthoringModelable;


public class Save extends ToolbarItemHandler {
    Saveable manager;

    public Save (CompleteAuthoringModelable model) {
        manager = model;
        System.out.println("Manager initialized");
    }

    @Override
    public void handle () {
        System.out.println("Saving");
        manager.onSave();
    }

}
