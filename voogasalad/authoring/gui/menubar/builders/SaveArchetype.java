package authoring.gui.menubar.builders;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import authoring.interfaces.model.EditSpritable;
import resources.VoogaBundles;
import tools.VoogaAlert;
import tools.VoogaException;

public class SaveArchetype extends ImportArchetype{
    public SaveArchetype (EditSpritable manager) {
        super();
        setManager(manager);
        addArchetypeNames(manager.getSpriteFactory().getAllArchetypeNames());
    }
    
    protected void apply(){
        try {
            for (String name : getSelectedArchetypes()){
                getManager().getSpriteFactory().saveArchetype(name);
            }
            this.close();
        }
        catch (VoogaException e) {
            new VoogaAlert(e.getMessage());
		}
    }
}
