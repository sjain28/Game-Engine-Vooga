package tools;

import authoring.gui.items.NumberTextField;
import javafx.scene.Node;
import tools.interfaces.VoogaData;

public class VoogaNumber implements VoogaData{
    private double value;
    
    public VoogaNumber(){
    	this.value = (Double) 0.0;
    }
    
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
    
    public String toString(){
        return "";
    }
    
    public Node display(){
        NumberTextField numberField = new NumberTextField();
        numberField.setText(""+value);
        
        return numberField;
    }

    @Override
    public void setValue (Object o) {
        if (!(o instanceof Number)) return;
        value = (double) o; 
    }
   
}
