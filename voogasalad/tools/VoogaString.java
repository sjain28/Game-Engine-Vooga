package tools;

import javafx.beans.binding.Bindings;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import tools.interfaces.VoogaData;

/**
 * String methods within the salad
 */
public class VoogaString implements VoogaData {
    private String myValue;
    private transient SimpleStringProperty valueProperty;

    public VoogaString () {
        this("");
    }

    public VoogaString (String value) {
        myValue = value;
        initializeProperty();
    }

    /**
     * Initialize a string with value and listener
     */
    private void initializeProperty () {
        if (valueProperty != null) {
        	return;
        }
        
        this.valueProperty = new SimpleStringProperty();
        this.valueProperty.setValue(myValue);

        this.valueProperty.addListener( (obs, old, n) -> {
            this.myValue = (String) n;
        });
    }

    /**
     * Return a string as a node in the salad
     */
    @Override
    public Node display () {
        TextField field = new TextField();
        
        Bindings.bindBidirectional(field.textProperty(),getProperty());
        
        field.setText(myValue);
        return field;
    }
    
    /**
     * Redundantly return a string as a string?
     */
    public String toString () {
        return myValue;
    }
    
    /**
     * Getters and setters below
     */
    @SuppressWarnings("unchecked")
	@Override
    public Property<String> getProperty () {
        initializeProperty();
        return this.valueProperty;
    }

    @Override
    public <T> void setProperty (T newVal) {
        initializeProperty();
        this.valueProperty.set((String) newVal);
    }
    
    @Override
    public Object getValue () {
        return myValue;
    }

    @Override
    public void setValue (Object o) {
        if (!(o instanceof String)){
            return;
        }
        myValue = (String) o;
    }
    
    public void setValue (String o) {
        myValue = o;
    }
}
