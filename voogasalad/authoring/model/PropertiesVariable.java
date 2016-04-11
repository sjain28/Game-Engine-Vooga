package authoring.model;

/**
 * Observable List to display variables in GUI.
 * 
 * @author Harry Guo, Aditya Srininvasan, Nick Lockett, Arjun Desai
 * 
 */

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;


public class PropertiesVariable extends PropertiesCellAbstract {

    private DoubleProperty value = new SimpleDoubleProperty();

    public PropertiesVariable (String name, double value) {
        super(name);
        this.setValue(value);
    }

    public DoubleProperty valueProperty () {
        return this.value;
    }

    public double getValue () {
        return this.valueProperty().get();
    }

    public void setValue (double value) {
        this.valueProperty().set(value);
    }

    @Override
    public String toString () {
        return name.toString() + "  " + value.toString();
    }

}
