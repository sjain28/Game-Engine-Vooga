package tools;

import authoring.gui.items.SwitchButton;
import javafx.scene.Node;
import tools.interfaces.VoogaData;

public class VoogaBoolean implements VoogaData{
    private boolean myValue;
    
    public VoogaBoolean() {
    	this.myValue = true;
    }
    
    public VoogaBoolean(boolean value){
        this.myValue=value;
    }
    
    public void setValue(boolean value){
        this.myValue=value;
    }
    
    @Override
    public Object getValue () {
        return (Boolean) myValue;
    }
    
    public Node display(){
        SwitchButton switchButton = new SwitchButton(myValue);
        switchButton.setOn(myValue);
        return switchButton;
    }

    @Override
    public void setValue (Object o) {
        if (!(o instanceof Boolean)) return;
        myValue = (Boolean) o;
        
    }
    
    public boolean equals(Boolean val){
    	return val == myValue;
    }
    
    public String toString() {
    	return Boolean.toString(myValue);
    }
    
}
