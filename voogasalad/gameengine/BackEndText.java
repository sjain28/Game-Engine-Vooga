package gameengine;

import java.util.HashMap;
import java.util.Map;

import authoring.interfaces.Elementable;
import data.Displayable;
import javafx.scene.Node;
import tools.interfaces.VoogaData;

public class BackEndText implements Elementable{

    Map<String, VoogaData> myProperties = new HashMap<String, VoogaData>();
    String myID;
	
    public BackEndText(Map<String, VoogaData> map, String ID){
    	 myProperties = myProperties;
    	 myID = ID;
    }
    
	@Override
	public Node getNodeObject() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Map<String, VoogaData> getVoogaProperties() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addProperty(String name, VoogaData data) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeProperty(String name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getID() {
		// TODO Auto-generated method stub
		return null;
	}

}
