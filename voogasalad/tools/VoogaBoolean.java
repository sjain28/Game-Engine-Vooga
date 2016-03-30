package tools;

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
    
}
