package gameengine;

import java.util.HashMap;
import java.util.Map;

import data.Displayable;
import javafx.scene.Node;
import tools.interfaces.VoogaData;

public class BackEndText implements Displayable{

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

}
