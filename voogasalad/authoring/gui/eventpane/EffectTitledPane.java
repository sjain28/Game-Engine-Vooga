package authoring.gui.eventpane;

import authoring.interfaces.model.EditEventable;
import resources.VoogaBundles;

public class EffectTitledPane extends EventTitledPane{
    public EffectTitledPane(EditEventable manager){
        super(manager,VoogaBundles.effectNames);
    }
    
}
