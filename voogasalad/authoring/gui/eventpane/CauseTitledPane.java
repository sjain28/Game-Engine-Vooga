package authoring.gui.eventpane;

import authoring.interfaces.model.EditEventable;
import resources.VoogaBundles;

public class CauseTitledPane extends EventTitledPane{
    
    public CauseTitledPane(EditEventable manager){
        super(manager,VoogaBundles.causeNames);
    }
    
   
}
