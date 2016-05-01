package authoring.model;

/**
 * Abstract class for cells within a table view.
 * 
 * @author Harry Guo, Nick Lockett, Aditya Srinivasan, Arjun Desai
 * 
 */

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public abstract class PropertiesCellAbstract {

    protected StringProperty name = new SimpleStringProperty();

    /**
     * Initializes the cell based upon its name
     * 
     * @param name
     */
    public PropertiesCellAbstract (String name) {
        this.setName(name);
        System.out.println(name);
    }

    /**
     * Allows access to the name of the Cell
     * 
     * @return
     */
    public StringProperty nameProperty () {
        return this.name;
    }

    /**
     * Returns the name of the Properties
     * 
     * @return
     */
    public String getName () {
        return this.nameProperty().get();
    }

    /**
     * Sets the Name of the Cell
     * 
     * @param value
     */
    public void setName (String name) {
        this.nameProperty().set(name);
    }

    public abstract String toString ();
}
