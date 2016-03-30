package tools;

import tools.interfaces.VoogaData;

public class VoogaNumber implements VoogaData{
    private double value;
    
    public VoogaNumber(Number number){
        this.value = (Double) number;
    }
    
    public void decreaseValue(Number dx){
        value-=dx.doubleValue();
    }
    
    public void increaseValue(Number dx){
        value+=dx.doubleValue();
    }
    
    public void setValue(Number value){
        this.value= value.doubleValue();
    }
    
    @Override
    public Object getValue () {
        if (value == ((int) value)){
            int number = (int) value;
            return number;
        }
        return value;
    }
   
}
