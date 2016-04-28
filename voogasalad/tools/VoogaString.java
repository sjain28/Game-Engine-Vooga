package tools;

import javafx.beans.binding.Bindings;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import tools.interfaces.VoogaData;


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

    private void initializeProperty () {
        if (valueProperty != null) return;
        
        this.valueProperty = new SimpleStringProperty();
        this.valueProperty.setValue(myValue);

        this.valueProperty.addListener( (obs, old, n) -> {
            this.myValue = (String) n;
        });
    }

    @Override
    public Node display () {
        // TODO Auto-generated method stub
        TextField field = new TextField();
        Bindings.bindBidirectional(getProperty(), field.textProperty());
        field.setText("" + myValue);
        return field;
    }

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
        if (!(o instanceof String))
            return;
        myValue = (String) o;
    }
    
    public String toString () {
        return myValue;
    }
}
