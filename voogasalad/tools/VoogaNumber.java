package tools;

import authoring.gui.items.NumberTextField;
import javafx.scene.Node;
import tools.interfaces.VoogaData;

public class VoogaNumber implements VoogaData{
    private double myValue;
    
    public VoogaNumber(){
    	this.myValue = (Double) 0.0;
    }
    
    public VoogaNumber(Number number){
        this.myValue = (Double) number;
    }
    
    public void decreaseValue(Number dx){
        myValue-=dx.doubleValue();
    }
    
    public void increaseValue(Number dx){
        myValue+=dx.doubleValue();
    }
    
    public void setValue(Number value){
        this.myValue= value.doubleValue();
    }
    
    public boolean equals(Double num){
    	
    	return num == myValue;
    }
    
    public boolean lessThan(Double num){
    	return num < myValue;
    }
    
    public boolean greaterThan(Double num){
    	return num > myValue;
    }
    
    @Override
    public Object getValue () {
        if (myValue == ((int) myValue)){
            int number = (int) myValue;
            return number;
        }
        return myValue;
    }
    
    public String toString(){
        return "";
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
