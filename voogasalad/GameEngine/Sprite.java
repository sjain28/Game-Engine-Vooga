package GameEngine;

import java.util.HashMap;
import java.util.Map;
import authoring.interfaces.Moveable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import tools.Vector;
import tools.interfaces.*;


public class Sprite implements Moveable {
	
	private Vector myVelocity;
	private String myID;
	private Map<String, VoogaData> myProperties;
	private ImageView myImage;

	public Sprite(String imageID, String id) {
		myImage = new ImageView(imageID);
		myID = id;
		myProperties = new HashMap<String, VoogaData>();
		//TODO: 
		//Make imagePath a StringProperty, implement listener in update that changes Sprite's image when 
		//		the imagePath changes (ie in a collision or something)
		//Add getters/setters for ImagePath
		//
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
	
	public ImageView getImage(){
		return myImage;
	}
}
