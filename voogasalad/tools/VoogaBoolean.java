package tools;

import authoring.gui.items.SwitchButton;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Node;
import tools.interfaces.VoogaData;

public class VoogaBoolean implements VoogaData{
    private boolean myValue;
    private transient SimpleBooleanProperty valueProperty;
    
    public VoogaBoolean() {
    	myValue = true;
    	this.valueProperty = new SimpleBooleanProperty();
    	this.valueProperty.setValue(myValue);
    	this.myValue = (Boolean) this.valueProperty.get();
    	this.valueProperty.addListener((obs, old, n) -> {
    		this.myValue = (Boolean) n;
    		System.out.println(n);
    	});
    }
    
    public VoogaBoolean(boolean value) {
    	this();
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
        switchButton.booleanProperty().addListener((obs, old, n) -> {
    		try {
    			this.valueProperty.set(n);
    		} catch(Exception e) {
    			
    		}
    	});
        switchButton.setOn(myValue);
        return switchButton;
    }

    @Override
    public void setValue(Object o) {
    	System.out.println("telling me to set value of boolean");
        if (!(o instanceof Boolean)){return;}
        System.out.println("setting boolean to : " + (Boolean) o);
        myValue = (Boolean) o;
        
    }
    
    public boolean equals(Boolean val){
    	return val == myValue;
    }
    
    public String toString() {
    	return Boolean.toString(myValue);
    }

	@Override
	public <T> Property<T> getProperty() {
		return (Property<T>) this.valueProperty;
	}

	@Override
	public <T> void setProperty(T newVal) {
    	System.out.println("telling me to set value of property to: " +(boolean) newVal);
		this.valueProperty.set((boolean) newVal);
	}
    
}
