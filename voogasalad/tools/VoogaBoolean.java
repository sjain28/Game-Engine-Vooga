package tools;

import authoring.gui.items.SwitchButton;
import tools.interfaces.VoogaData;

public class VoogaBoolean implements VoogaData{
    private boolean value;
    
    public VoogaBoolean(boolean value){
        this.value=value;
    }
    
    public void setValue(boolean value){
        this.value=value;
    }
    
    @Override
    public Object getValue () {
        return (Boolean) value;
    }
    
    public void display(){
        SwitchButton switchButton = new SwitchButton();
        switchButton.setOn(value);
    }
    
}
