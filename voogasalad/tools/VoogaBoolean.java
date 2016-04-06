package tools;

import authoring.gui.items.SwitchButton;
import javafx.scene.Node;
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
    
    public Node display(){
        SwitchButton switchButton = new SwitchButton(value);
        switchButton.setOn(value);
        return switchButton;
    }

    @Override
    public void setValue (Object o) {
        if (!(o instanceof Boolean)) return;
        value = (Boolean) o;
        
    }
    
}
