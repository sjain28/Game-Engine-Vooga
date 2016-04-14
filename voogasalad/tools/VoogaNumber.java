package tools;

import authoring.gui.items.NumberTextField;
import javafx.scene.Node;
import tools.interfaces.VoogaData;

public class VoogaNumber implements VoogaData{
    private double myValue;
    
    public VoogaNumber(){
    	this.myValue = (Double) 0.0;
    }
    
    public VoogaNumber(Double number){
        this.myValue = number;
    }
    
    public void decreaseValue(Double dx){
        myValue -= dx;
    }
    
    public void increaseValue(Double dx){
        myValue += dx;
    }
    
    public void setValue(Double value){
        this.myValue = value;
    }
    
    public boolean equals(Double num){
    	
    	return num == myValue;
    }
    
    public boolean lessThan(Double num){
    	return myValue < num;
    }
    
    public boolean greaterThan(Double num){
    	return myValue > num;
    }
    
    @Override
    public Object getValue () {
        if (myValue == ((double) myValue)){
            double number = (double) myValue;
            return number;
        }
        return myValue;
    }
    
    public String toString(){
        return ""+myValue;
    }
    
    public Node display(){
        NumberTextField numberField = new NumberTextField();
        numberField.setText(""+myValue);
        
        return numberField;
    }

    @Override
    public void setValue (Object o) {
        if (!(o instanceof Number)) return;
        myValue = (double) o; 
    }
    

   
}
