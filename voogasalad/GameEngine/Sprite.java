package GameEngine;

import java.util.HashMap;
import java.util.Map;
import authoring.interfaces.Moveable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import tools.Vector;
import tools.interfaces.*;

public class Sprite extends ImageView implements Moveable {
	
	private Vector myVelocity;
	private String myID;
	private Map<String, VoogaData> myProperties;

	public Sprite(String imageID, String id) {
		super(new Image(imageID));
		myID = id;
		myProperties = new HashMap<String, VoogaData>();
	}

	@Override
	public Vector getVelocity() {
		return myVelocity;
	}

	@Override
	public void setVelocity(Vector v) {
		myVelocity = v;
	}
	
	public void addProperty(String s, VoogaData v){
		myProperties.put(s, v);
	}
	
	public VoogaData getProperty(String s){
		return myProperties.get(s);
	}
	
	public String getID(){
		return myID;
	}

}
