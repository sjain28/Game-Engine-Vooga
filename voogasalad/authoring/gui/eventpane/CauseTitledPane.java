package authoring.gui.eventpane;

import authoring.interfaces.model.EditEventable;

public class CauseTitledPane extends EventTitledPane{
    
    public CauseTitledPane(EditEventable manager){
        super(manager);
    }
    
    @Override
    protected void initialize(){
        super.initialize();
        addOptions("VariableCause","KeyCause","CollisionCause","TimerCause");
    }
    
   
}
