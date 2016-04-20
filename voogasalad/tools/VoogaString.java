package tools;

import javafx.scene.Node;
import tools.interfaces.VoogaData;

public class VoogaString implements VoogaData {
	 private String myValue;

	 public VoogaString(String value){
		 this.myValue=value;
	 }
	 
	@Override
	public Object getValue() {
		// TODO Auto-generated method stub
		return myValue;
	}

	
	
    public void setValue(String value){
        this.myValue=value;
    }
	
	@Override
	public void setValue(Object o) {
		// TODO Auto-generated method stub
        if (!(o instanceof String)) return;
        myValue = (String) o;
	}

	@Override
	public Node display() {
		// TODO Auto-generated method stub
		return null;
	}
}