package tools;

import authoring.gui.items.NumberTextField;
import javafx.beans.binding.Bindings;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Node;
import tools.interfaces.VoogaData;


public class VoogaNumber implements VoogaData {
    private Double myValue;
    private transient SimpleDoubleProperty valueProperty;
    
    public VoogaNumber (Double number) {
        myValue = number;
        initializeProperty();
    }
    
    public VoogaNumber () {
        myValue = 0d;
        initializeProperty();
    }
 
    private void initializeProperty(){
        if (valueProperty != null) return;
        
        this.valueProperty = new SimpleDoubleProperty();
        this.valueProperty.setValue(myValue);

        this.valueProperty.addListener( (obs, old, n) -> {
            this.myValue = (Double) n;
        });

    }
    
    @Override
    public Property<Number> getProperty () {
        initializeProperty();
        return this.valueProperty;
    }

    @Override
    public <T> void setProperty (T newVal) {
        initializeProperty();
        this.valueProperty.set(Double.parseDouble((String) newVal));
    }

    public Node display () {
        initializeProperty();
        
        NumberTextField field = new NumberTextField();
        field.textProperty().addListener( (obs, old, n) -> {
            try {
                this.valueProperty.set(Double.parseDouble(n));
            }
            catch (Exception e) {

            }
        });
        
        valueProperty.addListener((obs,old,n)->{
            if (!field.isFocused()){
                field.textProperty().set(n.toString());
            }
        });
        
        field.focusedProperty().addListener((obs,old,n)->{
            if (!n){
               field.textProperty().set(valueProperty.getValue().toString()); 
            }
        });
        
        field.setText("" + myValue);
        return field;
    }

    @Override
    public void setValue (Object o) {
        if (!(o instanceof Number))
            return;
        myValue = (double) o;
    }
    
    @Override
    public Object getValue () {
        if (myValue == ((double) myValue)) {
            double number = (double) myValue;
            return number;
        }
        return myValue;
    }
    
    public void decreaseValue (Double dx) {
        myValue -= dx;
    }

    public void increaseValue (Double dx) {
        myValue += dx;
    }

    public void setValue (Double value) {
        this.myValue = value;
    }

    public boolean equals (Double num) {

        return num == myValue;
    }

    public boolean lessThan (Double num) {
        return myValue < num;
    }

    public boolean greaterThan (Double num) {
        return myValue > num;
    }
    
    public String toString () {
        return "" + myValue;
    }
}
