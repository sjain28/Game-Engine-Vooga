package authoring.model;

import javafx.beans.property.SimpleStringProperty;
/**
 * Observable List to display ket/mouse events in GUI.
 * 
 * @author Harry Guo, Aditya Srininvasan, Nick Lockett, Arjun Desai
 * 
 */
import javafx.beans.property.StringProperty;


public class PropertiesKeyMouse extends PropertiesCellAbstract {

    private StringProperty event = new SimpleStringProperty();
/**
 * Initializes the property to store mouse events, not unused
 * 
 * @param name
 * @param event
 */
    public PropertiesKeyMouse (String name, String event) {
        super(name);
        this.setValue(event);
    }

    /**
     * Allows access to the property storing the event
     * @return
     */
    public StringProperty valueProperty () {
        return this.event;
    }

    /**
     * Gets the current Name of the Property
     * @return
     */
    public String getValue () {
        return this.valueProperty().get();
    }

    
    /**
     * Sets the current value property
     * @param value
     */
    public void setValue (String value) {
        this.valueProperty().set(value);
    }

    @Override
    public String toString () {
        return name.toString() + "  " + event.toString();
    }

}
