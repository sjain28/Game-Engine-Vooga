package gameengine;

import java.util.HashMap;
import java.util.Map;
import authoring.interfaces.Moveable;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import tools.Vector;
import tools.interfaces.*;

public class Sprite implements Moveable {

    private Vector myVelocity;
    private String myID;
    private Map<String, VoogaData> myProperties;
    private String myImagePath;
    private double startingX;
    private double startingY;
    
    private transient Point2D myLoc;
    private transient ImageView myImage;
    
    public Sprite (String imagePath, String id, double x, double y) {
        myImagePath = imagePath;
        myID = id;
        myProperties = new HashMap<String, VoogaData>();
        myLoc = new Point2D(x,y);
        startingX = x;
        startingY = y;
    }
    
    /**
     * Initializes JavaFX objects that can't be serialized
     * Need to call this before using the Sprite!
     */
    public void init(){
       Image image = new Image(this.getClass().getResourceAsStream(myImagePath));
       myImage = new ImageView(image);
       myLoc = new Point2D(startingX, startingY);
    }
    
    public void update(){
    	//Still needed: Apply physics to myVelocity
    	myLoc.add(new Point2D(myVelocity.getX(), myVelocity.getY()));
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
    public void addProperty (String s, VoogaData v) {
        myProperties.put(s, v);
    }

    public VoogaData getProperty (String s) {
        return myProperties.get(s);
    }

    public String getID () {
        return myID;
    }

    public ImageView getImage () {
        return myImage;
    }
    
    public void setImagePath(String path){
    	myImagePath = path;
        Image image = new Image(this.getClass().getResourceAsStream(myImagePath));
        myImage = new ImageView(image);
    }
    
    public String getImagePath(){
    	return myImagePath;
    }

}
