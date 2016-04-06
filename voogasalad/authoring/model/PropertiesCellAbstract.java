package authoring.model;

/**
 * Abstract class for cells within a table view.
 * @author Harry Guo, Nick Lockett, Aditya Srinivasan, Arjun Desai
 * 
 */

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public abstract class PropertiesCellAbstract {

	protected StringProperty name = new SimpleStringProperty();

	public PropertiesCellAbstract(String name) {
		this.setName(name);
		System.out.println(name);
	}
	
	public StringProperty nameProperty() {
		return this.name;
	}

	public String getName() {
		return this.nameProperty().get();
	}
	
	public void setName(String name) {
		this.nameProperty().set(name);
	}
	
	public abstract String toString();
}
