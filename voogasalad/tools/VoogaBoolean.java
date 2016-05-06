package tools;

import authoring.gui.items.SwitchButton;
import javafx.beans.binding.Bindings;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Node;
import tools.interfaces.VoogaData;

/**
 * Handling and passing of booleans in the salad
 */
public class VoogaBoolean implements VoogaData {
	private boolean myValue;
	private transient SimpleBooleanProperty valueProperty;

	public VoogaBoolean() {
		myValue = true;

		initializeProperty();
	}

	public VoogaBoolean(boolean value) {
		this.myValue = value;
		initializeProperty();
	}

	/**
	 * Create and a new voogaBoolean with listener
	 */
	private void initializeProperty() {
		if (valueProperty != null) {
			return;
		}

		this.valueProperty = new SimpleBooleanProperty();
		this.valueProperty.setValue(myValue);

		this.valueProperty.addListener((obs, old, n) -> {
			this.myValue = (Boolean) n;
		});
	}

	/**
	 * Bind switchbutton to boolean value and return 
	 */
	public Node display() {
		SwitchButton switchButton = new SwitchButton(myValue);
		Bindings.bindBidirectional(switchButton.booleanProperty(),
				getProperty());
		switchButton.setOn(myValue);
		return switchButton;
	}

	/**
	 * Check if input is of a state
	 * 
	 * @param val
	 * @return
	 */
	public boolean equals(Boolean val) {
		return val.equals(myValue);
	}

	/**
	 * Return boolean as a string
	 */
	public String toString() {
		return Boolean.toString(myValue);
	}

	/**
	 * Getters and setters all below
	 * 
	 * @param value
	 */
	public void setValue(Boolean value) {
		this.myValue = value;
	}

	@Override
	public void setValue(Object o) {
		if (!(o instanceof Boolean)) {
			return;
		}
		myValue = (Boolean) o;
	}
	
	@Override
	public Object getValue() {
		return (Boolean) myValue;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T> Property<T> getProperty() {
		initializeProperty();
		return (Property<T>) this.valueProperty;
	}

	@Override
	public <T> void setProperty(T newVal) {
		initializeProperty();
		this.valueProperty.set((boolean) (Object) newVal);
	}
}
