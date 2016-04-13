package gameengine;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import authoring.interfaces.Elementable;
import authoring.interfaces.Moveable;
import events.Effectable;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import tools.Position;
import tools.Vector;
import tools.VoogaNumber;
import tools.Velocity;
import tools.VoogaBoolean;
import tools.interfaces.*;


public class Sprite implements Moveable, Effectable, Elementable {

	protected static final String MASS = "mass";
	protected static final String ALIVE = "alive";
	private Velocity myVelocity;
	private Position myLoc;
	private String myID;
	private String myName;
	private Map<String, VoogaData> myProperties;
	private String myImagePath;
	private String myArchetype;
	private transient ImageView myImage;

	public Sprite (String imagePath,
			String archetype,
			Map<String, VoogaData> properties,
			VoogaNumber mass) {
		myID = UUID.randomUUID().toString();
		myArchetype = archetype;
		myImagePath = imagePath;
		Image image = null;
		if(myImagePath.contains("file:")) {
			image = new Image(myImagePath);
		} else {
			image = new Image(this.getClass().getResourceAsStream(myImagePath));
		}
		myImage = new ImageView(image);
		myProperties = new HashMap<String, VoogaData>();
		myProperties = properties;
		myProperties.put(MASS, mass);
		myProperties.put(ALIVE, new VoogaBoolean(true));
		myLoc = new Position(0, 0);
		myVelocity = new Velocity(0, 0);
	}

	/**
	 * Initializes JavaFX objects that can't be serialized
	 * Need to call this before using the Sprite in the game engine!
	 */
	public void init () {
		Image image = new Image(this.getClass().getResourceAsStream(myImagePath));
		myImage = new ImageView(image);
	}

	public void update () {
		// Still needed: Apply physics to myVelocity
		myLoc.addVector(myVelocity);
		myImage.setLayoutX(myLoc.getX());
		myImage.setLayoutY(myLoc.getY());
	}
	public void setName(String name){
		myName = name;
	}
	@Override
	public Velocity getVelocity () {
		return myVelocity;
	}

	@Override
	public void setVelocity (Velocity velocity) {
		myVelocity = velocity;
	}

	public Position getPosition () {
		return myLoc;
	}

	public void setPosition (Position position) {
		myLoc = position;
	}

	public void addProperty (String property, VoogaData data) {
		myProperties.put(property, data);
	}

	public VoogaData getProperty (String s) {
		return myProperties.get(s);
	}

	public void setProperties (Map<String, VoogaData> properties) {
		myProperties = properties;
	}

	public HashMap<String, VoogaData> getPropertiesMap () {
		return (HashMap<String, VoogaData>) myProperties;
	}

	public String getID () {
		return myID;
	}

	public ImageView getImage () {
		return myImage;
	}

	public void setArchetype (String archetype) {
		myArchetype = archetype;
	}

	public String getArchetype () {
		return myArchetype;
	}

	public void setImagePath (String path) {
		myImagePath = path;
		Image image = new Image(this.getClass().getResourceAsStream(myImagePath));
		myImage = new ImageView(image);
	}

	public String getImagePath () {
		return myImagePath;
	}

	@Override
	public Map<String, VoogaData> getParameterMap () {
		return myProperties;
	}

	@Override
	public Map<String, VoogaData> getVoogaProperties () {
		return myProperties;
	}

	@Override
	public void removeProperty (String name) {
		myProperties.remove(name);
	}

	@Override
	public Node getNodeObject () {
		if(myImage == null){
			myImage = new ImageView(getImagePath());
		}
		System.out.println("My sprite image is: "+myImage);
		return myImage;
	}

	@Override
	public String getName () {
		return myName;
	}

}
