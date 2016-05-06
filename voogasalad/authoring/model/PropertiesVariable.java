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
/**
 * Initializes the variable (or Parameter) requiring the name of the Parameter and its value
 * @param name
 * @param value
 */
    public PropertiesVariable (String name, double value) {
        super(name);
        this.setValue(value);
    }

    /**
     * Allows access to the property of the Parameter's value
     * @return
     */
    public DoubleProperty valueProperty () {
        return this.value;
    }

    /**
     * returns the current value for the Parameter
     * @return
     */
    public double getValue () {
        return this.valueProperty().get();
    }

    /**
     * Sets the current value
     * @param value
     */
    public void setValue (double value) {
        this.valueProperty().set(value);
    }

    @Override
    public String toString () {
        return name.toString() + "  " + value.toString();
    }

}
