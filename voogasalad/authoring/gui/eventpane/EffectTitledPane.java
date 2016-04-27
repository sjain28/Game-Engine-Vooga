package authoring.gui.eventpane;

import authoring.interfaces.model.EditEventable;

public class EffectTitledPane extends EventTitledPane{
    public EffectTitledPane(EditEventable manager){
        super(manager);
    }
    
    @Override
    protected void initialize(){
        super.initialize();
        addOptions("VariableEffect","PhysicsEffect","SpawnEffect","ProjectileEffect","SoundEffect");
    }
}
