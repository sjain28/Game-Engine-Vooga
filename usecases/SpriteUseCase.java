package usecases;

import java.util.HashMap;
import java.util.Map;

import authoring.interfaces.Moveable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import tools.Vector;
import tools.Velocity;
import tools.interfaces.VoogaData;

//Remove all the Map double instances. I added them just so I could compile. sorry!

public class SpriteUseCase extends ImageView implements Moveable {

	private Vector myVelocity;
	private String myID;
	private Map<String, VoogaData> myProperties;
	//Remove later. i put this here just so i could compile
	private Map<String, Double> myDoubleProperties;

	public SpriteUseCase(String imageID, String id) {
		super(new Image(imageID));
		myID = id;
		myProperties = new HashMap<String, VoogaData>();
	}
	public void addParameter(String parameter, VoogaData value){
		myProperties.put(parameter, value);
	}
	
	public void addParameter(String parameter, Double value){
		myDoubleProperties.put(parameter, value);
	}
	@Override
	public Vector getVelocity() {
		return myVelocity;
	}

	public void addProperty(String s, VoogaData v){
		myProperties.put(s, v);
	}
	
	//Remove later. i put this here just so i could compile
	public void addProperty(String s, Double v){
		myDoubleProperties.put(s, v);
	}

	public VoogaData getProperty(String s){
		return myProperties.get(s);
	}

	
	//Remove later. i put this here just so i could compile
	public Double getDProperty(String s){
		return myDoubleProperties.get(s);
	}

	public String getID(){
		return myID;
	}
	@Override
	public void setVelocity(Velocity velocity) {
		// TODO Auto-generated method stub
		
	}

}
