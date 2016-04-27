package tools;

import authoring.gui.items.NumberTextField;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import tools.interfaces.VoogaData;

public class VoogaString implements VoogaData {
	private String myValue;
	private transient SimpleStringProperty myTextProperty;

	public VoogaString() {
		this("");
	}

	public VoogaString(String value) {
		initialize(value);
	}

	private void initialize(String value) {
		this.myTextProperty = new SimpleStringProperty();
		this.myTextProperty.setValue(value);
		this.myTextProperty.addListener((obs, old, n) -> {
			this.myValue = (String) n;
		});
		this.myValue = value;
	}

	@Override
	public Object getValue() {
		// TODO Auto-generated method stub
		return myValue;
	}

	public void setValue(String value) {
		this.myValue = value;
	}

	@Override
	public void setValue(Object o) {
		// TODO Auto-generated method stub
		if (!(o instanceof String))
			return;
		myValue = (String) o;
	}

	@Override
	public Node display() {
		// TODO Auto-generated method stub
		TextField field = new TextField();
        field.textProperty().addListener( (obs, old, n) -> {
            try {
                this.myTextProperty.set(n);
            }
            catch (Exception e) {

            }
        });
        field.setText("" + myValue);
        return field;
	}

	@SuppressWarnings("unchecked")
	@Override
	public SimpleStringProperty getProperty() {
		// TODO Auto-generated method stub
		return this.myTextProperty;
	}

	@Override
	public <T> void setProperty(T newVal) {
		// TODO Auto-generated method stub
		this.myTextProperty.set((String) newVal);
	}
	public String toString(){
		return myValue;
	}
}
