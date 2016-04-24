package gameengine;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import authoring.interfaces.Elementable;
import authoring.interfaces.Moveable;
import events.Effectable;
import javafx.beans.binding.Bindings;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import tools.Acceleration;
import tools.Position;
import tools.VoogaNumber;
import tools.VoogaString;
import tools.bindings.ImageProperties;
import tools.Velocity;
import tools.VoogaBoolean;
import tools.VoogaException;
import tools.interfaces.*;


public class Sprite implements Moveable, Effectable, Elementable {

    protected static final String MASS = "Mass";
    protected static final String ALIVE = "Alive";
    protected static final String GRAVITY = "Gravity";
    protected static final String WIDTH = "Width";
    protected static final String HEIGHT = "Height";
    protected static final String X_POS = "X_Position";
    protected static final String Y_POS = "Y_Position";
    protected static final String IMAGE_PATH = "Image";

    private boolean isMainCharacter;
    private Velocity myVelocity;
    private Acceleration myAcceleration;
    private Position myLoc;
    private String myID;
    private String myName;
    private Map<String, VoogaData> myProperties;
    private String myArchetype;
    private String previousImage;
    private String myImagePath;

    private transient ImageView myImage;
    private transient SimpleDoubleProperty myX;
    private transient SimpleDoubleProperty myY;
    private transient SimpleDoubleProperty myWidth;
    private transient SimpleDoubleProperty myHeight;
    private transient SimpleStringProperty myImagePathProperty;
    private transient SimpleBooleanProperty myAlive;

    private Map<String, Object> initializationProperties;

    public Sprite (String imagePath,
                   String archetype,
                   Map<String, VoogaData> properties,
                   VoogaNumber mass) {

        myProperties = new HashMap<String, VoogaData>();
        myProperties = properties;

        initializeCoordinates();

        myLoc = new Position(myX.get(), myY.get());
        myVelocity = new Velocity(0, 0);
        myAcceleration = new Acceleration(0, 0);
        myID = UUID.randomUUID().toString();
        myArchetype = archetype;

        initializeImage(imagePath);

        // TODO: use properties file to put these
        myProperties.put(MASS, new VoogaNumber((Double) mass.getValue()));
        myProperties.put(GRAVITY, new VoogaNumber(0.0));

        initializeAlive();
        initializeDimensions(myImage.getFitWidth(), myImage.getFitHeight());

    }

    public Property<Boolean> isAlive () {
        return this.myAlive;
    }

    private void initializeAlive () {
        myProperties.put(ALIVE, new VoogaBoolean(true));
        myAlive =
                new SimpleBooleanProperty((boolean) myProperties.get(ALIVE).getProperty()
                        .getValue());
        Bindings.bindBidirectional(myAlive, myProperties.get(ALIVE).getProperty());
    }

    private void initializeImage (String path) {
        VoogaString imagePathString = new VoogaString(path);
        myImagePath = path;
        previousImage = path;
        myImagePathProperty = new SimpleStringProperty(path);
        myProperties.put(IMAGE_PATH, imagePathString);
        Image newImage = setNewImage();
        Bindings.bindBidirectional(myImagePathProperty, myProperties.get(IMAGE_PATH).getProperty());

        myImage = new ImageView(newImage);
        myImage.setFitHeight(newImage.getHeight());
        myImage.setFitWidth(newImage.getWidth());
    }

    private Image setNewImage () {
        Image image;
        if (myProperties.get(IMAGE_PATH).getValue().toString().contains("file:")) {
            image = new Image(myProperties.get(IMAGE_PATH).getValue().toString());
        }
        else {
            image =
                    new Image(this.getClass().getResourceAsStream(myProperties.get(IMAGE_PATH)
                            .getValue().toString()));

        }
        return image;
    }

    private void initializeDimensions (double width, double height) {
        myProperties.put(WIDTH, new VoogaNumber(width));
        myProperties.put(HEIGHT, new VoogaNumber(height));
        myWidth = new SimpleDoubleProperty();
        myHeight = new SimpleDoubleProperty();
        Bindings.bindBidirectional(myWidth, myProperties.get(WIDTH).getProperty());
        Bindings.bindBidirectional(myHeight, myProperties.get(HEIGHT).getProperty());

    }

    private void initializeCoordinates () {
        myProperties.put(X_POS, new VoogaNumber());
        myProperties.put(Y_POS, new VoogaNumber());
        myX = new SimpleDoubleProperty();
        myY = new SimpleDoubleProperty();
        Bindings.bindBidirectional(myX, myProperties.get(X_POS).getProperty());
        Bindings.bindBidirectional(myY, myProperties.get(Y_POS).getProperty());
        myX.addListener( (obs, old, n) -> {
            myLoc.setX((double) n);
        });
        myY.addListener( (obs, old, n) -> {
            myLoc.setY((double) n);
        });
    }

    public void update () {
        // Still needed: Apply physics to myVelocity

        // Velocity in m/s >> Each step is one s, so the number of meters u should increment
        // System.out.println("Archetype: "+myArchetype+"
        // "+"velocityY"+myVelocity.getY()+"velocityX"+myVelocity.getX());

        // Velocity in m/s >> Each step is one s, so the number of meters u should increment
        // System.out.println("Archetype: " + myArchetype + " " + "velocityY" + myVelocity.getY() +
        // "velocityX" + myVelocity.getX());
    	//TODO: IF IMAGE PATH UPDATES, ACTUALLY UPDATE THE IMAGE
    	
        myLoc.addX(myVelocity.getX());
        myLoc.addY(myVelocity.getY());

        // Acceleration in m/s^2 >> Each step is one s, so number of m/s u should increment
        myVelocity.addX(myAcceleration.getX());
        myVelocity.addY(myAcceleration.getY());

        // Convert the Sprite's Cartesian Coordinates to display-able x and y's
        myImage.setTranslateX(myLoc.getX() - myImage.getFitWidth() / 2);
        myImage.setTranslateY(myLoc.getY() - myImage.getFitHeight() / 2);

        System.out.println("isAlive: "+ myProperties.get(ALIVE));
        if (!myProperties.get(IMAGE_PATH).getValue().toString().equals(previousImage)) {
            Image newImage = setNewImage();
            myImage.setImage(newImage);
            previousImage = myProperties.get(IMAGE_PATH).getValue().toString();
        }
        // System.out.println(myArchetype +" Location: " + myLoc.getX() + ", "+myLoc.getY());

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
        myProperties = new HashMap<String, VoogaData>(properties);
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
        myProperties.put(IMAGE_PATH, new VoogaString(path));
        Image image =
                new Image(this.getClass()
                        .getResourceAsStream(myProperties.get(IMAGE_PATH).getValue().toString()));
        myImage = new ImageView(image);
        myImage.setLayoutX(myLoc.getX());
        myImage.setLayoutY(myLoc.getY());
    }

    public String getImagePath () {
        return myProperties.get(IMAGE_PATH).getValue().toString();
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
        initializeImage();
        return myImage;
    }

    @Override
    public String getName () {
        return myName;
    }

    public boolean isMainCharacter () {
        return isMainCharacter;
    }

    public void setMainCharacter (boolean isMainCharacter) {
        this.isMainCharacter = isMainCharacter;
    }

    @Override
    public void setVoogaProperties (Map<String, VoogaData> newVoogaProperties) {
        this.myProperties = newVoogaProperties;
    }

    public Property<Number> getX () {
        return this.myX;
    }

    public Property<Number> getY () {
        return this.myY;
    }

    public Property<Number> getWidth () {
        return this.myWidth;
    }

    public Property<Number> getHeight () {
        return this.myHeight;
    }

    public Property<String> getImagePathProperty () {
        return this.myImagePathProperty;
    }

    public void initializeImage () {
        if (myImage == null) {
            myImage = new ImageView(getImagePath());
        }
    }

    public void setInitializationMap (Map<String, Object> ip) {
        initializationProperties = ip;
    }

    /**
     * Initializes JavaFX objects that can't be serialized
     * Need to call this before using the Sprite in the game engine!
     * 
     * @throws VoogaException
     * @throws InvocationTargetException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */

    public void init () throws VoogaException {
        if (myImage != null)
            return;

        ImageProperties imageProperties = new ImageProperties();
        myImagePathProperty = new SimpleStringProperty();
        myImagePathProperty.set(myProperties.get(IMAGE_PATH).getValue().toString());

        Image image = new Image(myProperties.get(IMAGE_PATH).getValue().toString());
        myImage = new ImageView(image);

        imageProperties.loadData(myImage, initializationProperties);

        initializeCoordinates();
        initializeDimensions(myImage.getFitWidth(), myImage.getFitHeight());
        initializeAlive();

        myX.set(myImage.getTranslateX());
        myY.set(myImage.getTranslateY());
        myWidth.set(myImage.getFitWidth());
        myHeight.set(myImage.getFitHeight());
        myAlive.set(true);

    }
}
