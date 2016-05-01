package tools;

import authoring.gui.items.NumberTextField;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Node;
import tools.interfaces.VoogaData;


/**
 * Properties and methods for numbers within the salad
 */
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

    /**
     * Set value and listener for a number in the salad
     */
    private void initializeProperty () {
        if (valueProperty != null) {
            return;
        }
        
        this.valueProperty = new SimpleDoubleProperty();
        this.valueProperty.setValue(myValue);

        this.valueProperty.addListener( (obs, old, n) -> {
            this.myValue = (Double) n;
        });

    }

    /**
     * Return a node in the salad for the number
     */
    // TODO method is too long
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

        valueProperty.addListener( (obs, old, n) -> {
            if (!field.isFocused()) {
                field.textProperty().set(n.toString());
            }
        });

        field.focusedProperty().addListener( (obs, old, n) -> {
            if (!n) {
                field.textProperty().set(valueProperty.getValue().toString());
            }
        });

        field.setText("" + myValue);
        return field;
    }

    /**
     * Decrease value of a voogaNumber
     * 
     * @param dx
     */
    public void decreaseValue (Double dx) {
        myValue -= dx;
    }

    /**
     * Increase Value of a voogaNumber
     * 
     * @param dx
     */
    public void increaseValue (Double dx) {
        myValue += dx;
    }

    /**
     * Return whether a voogaNumber equals an input
     * 
     * @param num
     * @return
     */
    public boolean equals (Double num) {

        return num.equals(myValue);
    }

    /**
     * Return whether a voogaNumber is lessThan an input
     * 
     * @param num
     * @return
     */
    public boolean lessThan (Double num) {
        return myValue < num;
    }

    /**
     * Return whether a voogaNumber is greaterThan an input
     * 
     * @param num
     * @return
     */
    public boolean greaterThan (Double num) {
        return myValue > num;
    }

    /**
     * Return a voogaNumber as a string
     */
    public String toString () {
        return "" + myValue;
    }

    /**
     * Getters and setters below
     */
    public void setValue (Double value) {
        this.myValue = value;
    }

    @Override
    public void setValue (Object o) {
        if (!(o instanceof Number)) {
            return;
        }
        
        initializeProperty();
        myValue = (double) o;
    }

    @Override
    public Object getValue () {
        return (double) myValue;
    }

    @SuppressWarnings("unchecked")
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
}
