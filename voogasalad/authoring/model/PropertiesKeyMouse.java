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

    public PropertiesKeyMouse (String name, String event) {
        super(name);
        this.setValue(event);
    }

    public StringProperty valueProperty () {
        return this.event;
    }

    public String getalue () {
        return this.valueProperty().get();
    }

    public void setValue (String value) {
        this.valueProperty().set(value);
    }

    @Override
    public String toString () {
        return name.toString() + "  " + event.toString();
    }

}
