package tools;

import java.util.Date;

import javafx.beans.property.Property;
import javafx.scene.Node;
import javafx.scene.text.Text;
import tools.interfaces.VoogaData;

public class VoogaDate implements VoogaData{
	private Date myDate;
	
	public VoogaDate(Date voogaDate){
		myDate = voogaDate;
	}
	@Override
	public Object getValue() {
		return myDate;
	}

	@Override
	public void setValue(Object o) {
		myDate = (Date) o;
	}

	@Override
	public Node display() {
		return new Text(myDate.toString());
	}

	@Override
	public <T> Property<T> getProperty() {
		return (Property<T>) myDate;
	}

	@Override
	public <T> void setProperty(T newVal) {
		myDate = (Date) newVal;
	}
	
	public String toString(){
		return myDate.toString();
	}
}
