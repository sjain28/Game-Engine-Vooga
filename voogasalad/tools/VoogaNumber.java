package tools;

import authoring.gui.items.NumberTextField;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Node;
import tools.interfaces.VoogaData;

public class VoogaNumber implements VoogaData{
    private Double myValue;
    private transient SimpleDoubleProperty valueProperty;
    
    public VoogaNumber() {
    	this(0d);
    }
    
    public VoogaNumber(Double number){
    	this.valueProperty = new SimpleDoubleProperty();
    	this.valueProperty.setValue(myValue);
    	this.myValue = number;
    	this.valueProperty.addListener((obs, old, n) -> {
    		this.myValue = (Double) n;
    	});
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
    	NumberTextField field  = new NumberTextField();
    	field.textProperty().addListener((obs, old, n) -> {
    		try {
    			this.valueProperty.set(Double.parseDouble(n));
    		} catch(Exception e) {
    			
    		}
    	});
        field.setText(""+myValue);
        return field;
    }

    @Override
    public void setValue (Object o) {
        if (!(o instanceof Number)) return;
        myValue = (double) o; 
    }

	@Override
	public Property<Number> getProperty() {
		return this.valueProperty;
	}

	@Override
	public <T> void setProperty(T newVal) {
		this.valueProperty.set(Double.parseDouble((String) newVal));
	}
    

   
}
