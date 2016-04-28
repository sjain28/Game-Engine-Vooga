package tools;

import authoring.gui.items.SwitchButton;
import javafx.beans.binding.Bindings;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Node;
import tools.interfaces.VoogaData;


public class VoogaBoolean implements VoogaData {
    private boolean myValue;
    private transient SimpleBooleanProperty valueProperty;

    public VoogaBoolean () {
        myValue = true;

        initializeProperty();
    }

    public VoogaBoolean (boolean value) {
        this.myValue = value;
        initializeProperty();
    }

    public void setValue (Boolean value) {
        this.myValue = value;
    }

    @Override
    public Object getValue () {
        return (Boolean) myValue;
    }
    
    private void initializeProperty () {
        if (valueProperty != null)
            return;

        this.valueProperty = new SimpleBooleanProperty();
        this.valueProperty.setValue(myValue);

        this.valueProperty.addListener( (obs, old, n) -> {
            this.myValue = (Boolean) n;
            System.out.println(n);
        });
    }

    public Node display () {

        SwitchButton switchButton = new SwitchButton(myValue);
        Bindings.bindBidirectional(switchButton.booleanProperty(), getProperty());
        switchButton.setOn(myValue);
        return switchButton;
    }

    @Override
    public void setValue (Object o) {
        System.out.println("telling me to set value of boolean");

        if (!(o instanceof Boolean)) {
            return;
        }

        System.out.println("setting boolean to : " + (Boolean) o);
        myValue = (Boolean) o;

    }

    @Override
    public <T> Property<T> getProperty () {
        initializeProperty();
        return (Property<T>) this.valueProperty;
    }

    @Override
    public <T> void setProperty (T newVal) {
        System.out.println("telling me to set value of property to: " + (boolean) newVal);
        initializeProperty();
        this.valueProperty.set((boolean) (Object) newVal);
    }
    

    public boolean equals (Boolean val) {
        return val == myValue;
    }

    public String toString () {
        return Boolean.toString(myValue);
    }

}
