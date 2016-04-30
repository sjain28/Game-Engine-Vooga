package tools;

import java.util.Date;

import javafx.beans.property.Property;
import javafx.scene.Node;
import javafx.scene.text.Text;
import tools.interfaces.VoogaData;

/**
 * Handler and passer of dates within the salad
 */
public class VoogaDate implements VoogaData{
	private Date myDate;
	
	public VoogaDate(Date voogaDate){
		myDate = voogaDate;
	}

	/**
	 * Return a date as a string on a node
	 */
	@Override
	public Node display() {
		return new Text(myDate.toString());
	}
	
	/**
	 * Return a date as a string
	 */
	public String toString(){
		return myDate.toString();
	}
	
	/**
	 * Getters and setters below
	 */
	@Override
	public Object getValue() {
		return myDate;
	}

	@Override
	public void setValue(Object o) {
		myDate = (Date) o;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T> Property<T> getProperty() {
		return (Property<T>) myDate;
	}

	@Override
	public <T> void setProperty(T newVal) {
		myDate = (Date) newVal;
	}
}
