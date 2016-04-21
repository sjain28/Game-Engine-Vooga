package gameengine;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import authoring.interfaces.Elementable;
import authoring.interfaces.Moveable;
import events.Effectable;
import javafx.beans.binding.Bindings;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import tools.Acceleration;
import tools.Position;
import tools.VoogaNumber;
import tools.Velocity;
import tools.VoogaBoolean;
import tools.interfaces.*;


public class Sprite implements Moveable, Effectable, Elementable {

    protected static final String MASS = "Mass";
    protected static final String ALIVE = "Alive";
    protected static final String GRAVITY = "Gravity";
    protected static final String WIDTH = "Width";
    protected static final String HEIGHT = "Height";
    protected static final String X_POS = "X Position";
    protected static final String Y_POS = "Y Position";
    private boolean isMainCharacter;
    private Velocity myVelocity;
    private Acceleration myAcceleration;
    private Position myLoc;
    private String myID;
    private String myName;
    private Map<String, VoogaData> myProperties;
    private String myImagePath;
    private String myArchetype;
    private transient ImageView myImage;
    private transient SimpleDoubleProperty myX;
    private transient SimpleDoubleProperty myY;
    private transient SimpleDoubleProperty myWidth;
    private transient SimpleDoubleProperty myHeight;
    private double spriteWidth;
    private double spriteHeight;

    public Sprite (String imagePath,
                   String archetype,
                   Map<String, VoogaData> properties,
                   VoogaNumber mass) {
    	myProperties = new HashMap<String, VoogaData>();
        myProperties = properties;
    	initializeCoordinates();
        myLoc = new Position(myX.get(), myY.get());
        myVelocity = new Velocity(0, 0);
        myAcceleration = new Acceleration(0,0);
        
        myID = UUID.randomUUID().toString();
        myArchetype = archetype;
        myImagePath = imagePath;
        Image image = null;
        if (myImagePath.contains("file:")) {
            image = new Image(myImagePath);
        }
        else 
        {
            image = new Image(this.getClass().getResourceAsStream(myImagePath));
        }
        myImage = new ImageView(image);

        //TODO: use properties file to put these
        myProperties.put(MASS, mass);
        myProperties.put(ALIVE, new VoogaBoolean(true));
        myProperties.put(GRAVITY, new VoogaNumber(0.0));
        myProperties.put(WIDTH, new VoogaNumber(image.getWidth()));
        myProperties.put(HEIGHT, new VoogaNumber(image.getHeight()));
        
        initializeDimensions(image.getWidth(), image.getHeight());
    }
    
    private void initializeDimensions(double width, double height) {
    	myWidth = new SimpleDoubleProperty();
        myHeight = new SimpleDoubleProperty();
        Bindings.bindBidirectional(myWidth, myProperties.get(WIDTH).getProperty());
        Bindings.bindBidirectional(myHeight, myProperties.get(HEIGHT).getProperty());
        spriteWidth = width;
        spriteHeight = height;
        myWidth.addListener((obs, old, n) -> {
        	spriteWidth = (double) n;
        });
        myWidth.addListener((obs, old, n) -> {
        	spriteHeight = (double) n;
        });
    }
    
    private void initializeCoordinates() {
        myProperties.put(X_POS, new VoogaNumber());
        myProperties.put(Y_POS, new VoogaNumber());
    	myX = new SimpleDoubleProperty();
    	myY = new SimpleDoubleProperty();
    	Bindings.bindBidirectional(myX, myProperties.get(X_POS).getProperty());
    	Bindings.bindBidirectional(myY, myProperties.get(Y_POS).getProperty());
    	myX.addListener((obs, old, n) -> {
    		myLoc.setX((double) n);
    	});
    	myY.addListener((obs, old, n) -> {
    		myLoc.setY((double) n);
    	});
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
    	
    	//Velocity in m/s >> Each step is one s, so the number of meters u should increment
        System.out.println("Archetype: "+myArchetype+" "+"velocityY"+myVelocity.getY()+"velocityX"+myVelocity.getX());

        myLoc.addX(myVelocity.getX());
        myLoc.addY(myVelocity.getY());
       
        //Acceleration in m/s^2 >> Each step is one s, so number of m/s u should increment
        myVelocity.addX(myAcceleration.getX());
        myVelocity.addY(myAcceleration.getY());
        
        //Convert the Sprite's Cartesian Coordinates to display-able x and y's
        myImage.setTranslateX(myLoc.getX() - spriteWidth/2);
        myImage.setTranslateY(myLoc.getY() - spriteHeight/2);
        
//        System.out.println(myArchetype +" Location: " +  myLoc.getX() + ", "+myLoc.getY());
        
    }

    public void setName (String name) {
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
        myProperties = new HashMap<String,VoogaData>(properties);
    }

    public HashMap<String, VoogaData> getPropertiesMap () {
        return (HashMap<String, VoogaData>) myProperties;
    }

    public String getId () {
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
        myImage.setLayoutX(myLoc.getX());
        myImage.setLayoutY(myLoc.getY());
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
        if (myImage == null) {
            myImage = new ImageView(getImagePath());
        }
        return myImage;
    }

    @Override
    public String getName () {
        return myName;
    }

	public boolean isMainCharacter() {
		return isMainCharacter;
	}

	public void setMainCharacter(boolean isMainCharacter) {
		this.isMainCharacter = isMainCharacter;
	}

	@Override
	public void setVoogaProperties(Map<String, VoogaData> newVoogaProperties) {
		// TODO Auto-generated method stub
		
	}

	public Property<Number> getX() {
		return this.myX;
	}
	
	public Property<Number> getY() {
		return this.myY;
	}
	
	public Property<Number> getWidth() {
		return this.myWidth;
	}
	
	public Property<Number> getHeight() {
		return this.myWidth;
	}

}
