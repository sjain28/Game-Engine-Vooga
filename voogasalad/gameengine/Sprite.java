package gameengine;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import authoring.interfaces.Moveable;
import events.Effectable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import tools.Vector;
import tools.VoogaNumber;
import tools.interfaces.*;

public class Sprite implements Moveable, Effectable{
	
	public static final String MASS = "mass";
    private Vector myVelocity;
    private Vector myLoc;
    private Object myID;
    private Map<String, VoogaData> myProperties;
    private String myImagePath;
    private String myArchetype;
    private transient ImageView myImage;
    
    public Sprite (String imagePath, String archetype, Map<String, VoogaData> properties, VoogaNumber mass){
    	myID = UUID.randomUUID();
    	myArchetype = archetype;
        myImagePath = imagePath;
        Image image = new Image(this.getClass().getResourceAsStream(myImagePath));
        myImage = new ImageView(image);
        myProperties = new HashMap<String, VoogaData>();
        myProperties = properties;
        myProperties.put(MASS, mass);
        myLoc = new Vector(0,0);
        myVelocity = new Vector(0,0);
    }
    
    /**
     * Initializes JavaFX objects that can't be serialized
     * Need to call this before using the Sprite in the game engine!
     */
    public void init(){
       Image image = new Image(this.getClass().getResourceAsStream(myImagePath));
       myImage = new ImageView(image);
    }
    
    public void update(){
    	//Still needed: Apply physics to myVelocity
    	myLoc.addVector(myVelocity);
    	myImage.setLayoutX(myLoc.getX());
    	myImage.setLayoutY(myLoc.getY());    	
    }

    @Override
    public Vector getVelocity () {
        return myVelocity;
    }

    @Override
    public void setVelocity (Vector v) {
        myVelocity = v;
    }
    
    public Vector getPosition(){
    	return myLoc;
    }
    
    public void setPosition(Vector v){
    	myLoc = v;
    }
    public void addProperty (String s, VoogaData v) {
        myProperties.put(s, v);
    }

    public VoogaData getProperty (String s) {
        return myProperties.get(s);
    }
    
    public HashMap<String, VoogaData> getPropertiesMap() {
    	return (HashMap<String, VoogaData>) myProperties;
    }

    public Object getID () {
        return myID;
    }

    public ImageView getImage () {
        return myImage;
    }
    
    public void setArchetype(String archetype){
    	myArchetype = archetype;
    }
    
    public String getArchetype(){
    	return myArchetype;
    }
    
    public void setImagePath(String path){
    	myImagePath = path;
        Image image = new Image(this.getClass().getResourceAsStream(myImagePath));
        myImage = new ImageView(image);
    }
    
    public String getImagePath(){
    	return myImagePath;
    }

	@Override
	public Map<String, VoogaData> getParameterMap() {
		return myProperties;
	}
}
