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
import resources.VoogaBundles;
import tools.Acceleration;
import tools.Position;
import tools.Velocity;
import tools.VoogaBoolean;
import tools.VoogaException;
import tools.VoogaNumber;
import tools.VoogaString;
import tools.bindings.ImageProperties;
import tools.interfaces.VoogaData;


public class Sprite implements Moveable, Effectable, Elementable {
	private boolean isMainCharacter;
	private Velocity myVelocity;
	private Acceleration myAcceleration;
	private Position myLoc;
	private String myID;
	private String myName;
	private Map<String, VoogaData> myProperties;
	private String myArchetype;
	private String previousImage;

	private transient ImageView myImage;
	private transient SimpleDoubleProperty myX;
	private transient SimpleDoubleProperty myY;
	private transient SimpleDoubleProperty myZ;
	private transient SimpleDoubleProperty myWidth;
	private transient SimpleDoubleProperty myHeight;
	private transient SimpleStringProperty myImagePathProperty;
	private transient SimpleBooleanProperty myAlive;

	private Map<String, Object> initializationProperties;

	private static final int RESIZE_FACTOR = 2;

	public Sprite (String imagePath,
			String archetype,
			Map<String, VoogaData> properties,
			VoogaNumber mass) {

		myProperties = properties;

		initializeImage(imagePath);
		initializeCoordinates();

		myLoc = new Position(myX.get(), myY.get());
		myVelocity = new Velocity(0, 0);
		myAcceleration = new Acceleration(0, 0);
		myID = UUID.randomUUID().toString();
		myArchetype = archetype;

		myProperties.put(VoogaBundles.spriteProperties.getString("MASS"),
				new VoogaNumber((Double) mass.getValue()));
		myProperties.put(VoogaBundles.spriteProperties.getString("GRAVITY"), new VoogaNumber(0.0));
		myProperties.put(VoogaBundles.spriteProperties.getString("STATIC"),
				new VoogaBoolean(false));

		initializeAlive();
		initializeDimensions(myImage.getFitWidth(), myImage.getFitHeight());
	}

	private void initializeAlive () {
		myProperties.put(VoogaBundles.spriteProperties.getString("ALIVE"), new VoogaBoolean(true));
		reloadAlive();
	}

	private void reloadAlive () {
		myAlive = new SimpleBooleanProperty();
		Bindings.bindBidirectional(myAlive, myProperties
				.get(VoogaBundles.spriteProperties.getString("ALIVE")).getProperty());
	}

	private void initializeImage (String path) {
		VoogaString imagePathString = new VoogaString(path);

		previousImage = path;

		myProperties.put(VoogaBundles.spriteProperties.getString("IMAGE_PATH"), imagePathString);
		reloadImage();
	}

	private void reloadImage () {

		if (myImagePathProperty == null) {
			myImagePathProperty = new SimpleStringProperty();
		}
		Bindings.bindBidirectional(myImagePathProperty, myProperties
				.get(VoogaBundles.spriteProperties.getString("IMAGE_PATH")).getProperty());
		setImagePath((String) myProperties
				.get(VoogaBundles.spriteProperties.getString("IMAGE_PATH")).getProperty()
				.getValue());
	}

	public void setImagePath (String path) {
		myImagePathProperty.set(path);
		Image image=setNewImage();

		myImage = new ImageView(image);
		myImage.setFitWidth(image.getWidth());
		myImage.setFitHeight(image.getHeight());
	}

	private Image setNewImage () {
		Image image;
		if (myProperties.get(VoogaBundles.spriteProperties.getString("IMAGE_PATH")).getValue()
				.toString().contains("file:")) {
			image =
					new Image(myProperties
							.get(VoogaBundles.spriteProperties.getString("IMAGE_PATH")).getValue()
							.toString());
		}
		else {
			image =
					new Image(this.getClass().getResourceAsStream(myProperties
							.get(VoogaBundles.spriteProperties.getString("IMAGE_PATH"))
							.getValue().toString()));

		}
		return image;
	}

	private void initializeDimensions (double widthValue, double heightValue) {
		VoogaNumber width = new VoogaNumber(widthValue);
		VoogaNumber height = new VoogaNumber(heightValue);
		myProperties.put(VoogaBundles.spriteProperties.getString("WIDTH"), width);
		myProperties.put(VoogaBundles.spriteProperties.getString("HEIGHT"), height);

		reloadDimensions();
	}

	private void reloadDimensions () {
		myWidth = new SimpleDoubleProperty();
		myHeight = new SimpleDoubleProperty();

		Bindings.bindBidirectional(myProperties
				.get(VoogaBundles.spriteProperties.getString("WIDTH")).getProperty(),
				myImage.fitWidthProperty());
		Bindings.bindBidirectional(myProperties
				.get(VoogaBundles.spriteProperties.getString("HEIGHT")).getProperty(),
				myImage.fitHeightProperty());
		Bindings.bindBidirectional(myWidth, myProperties
				.get(VoogaBundles.spriteProperties.getString("WIDTH")).getProperty());
		Bindings.bindBidirectional(myHeight, myProperties
				.get(VoogaBundles.spriteProperties.getString("HEIGHT")).getProperty());
	}

	private void initializeCoordinates () {
		myProperties.put(VoogaBundles.spriteProperties.getString("X_POS"), new VoogaNumber());
		myProperties.put(VoogaBundles.spriteProperties.getString("Y_POS"), new VoogaNumber());
		myProperties.put(VoogaBundles.spriteProperties.getString("Z_POS"), new VoogaNumber());
		reloadCoordinates();
	}

	private void reloadCoordinates () {
		myX = new SimpleDoubleProperty();
		myY = new SimpleDoubleProperty();
		myZ = new SimpleDoubleProperty();

		Bindings.bindBidirectional(myX, myProperties
				.get(VoogaBundles.spriteProperties.getString("X_POS")).getProperty());
		Bindings.bindBidirectional(myY, myProperties
				.get(VoogaBundles.spriteProperties.getString("Y_POS")).getProperty());
		Bindings.bindBidirectional(myZ, myProperties
				.get(VoogaBundles.spriteProperties.getString("Z_POS")).getProperty());

		myX.addListener( (obs, old, n) -> {
			myLoc.setX((double) n);
		});
		myY.addListener( (obs, old, n) -> {
			myLoc.setY((double) n);
		});
	}

	public void update () {

		myLoc.addX(myVelocity.getX());
		myLoc.addY(myVelocity.getY());

		// Acceleration in m/s^2 >> Each step is one s, so number of m/s u should increment
		myVelocity.addX(myAcceleration.getX());
		myVelocity.addY(myAcceleration.getY());

		// Convert the Sprite's Cartesian Coordinates to display-able x and y's
		myImage.setTranslateX(myLoc.getX() - myImage.getFitWidth() / RESIZE_FACTOR);
		myImage.setTranslateY(myLoc.getY() - myImage.getFitHeight() / RESIZE_FACTOR);
		myImage.setTranslateZ(myZ.doubleValue());
		
		myX.set(myLoc.getX());
		myY.set(myLoc.getY());
		
		myImage.setFitWidth((double) myProperties
				.get(VoogaBundles.spriteProperties.getString("WIDTH")).getValue());
		myImage.setFitHeight((double) myProperties
				.get(VoogaBundles.spriteProperties.getString("HEIGHT")).getValue());

		if (!myProperties.get(VoogaBundles.spriteProperties.getString("IMAGE_PATH")).getValue()
				.toString().equals(previousImage)) {
			Image newImage = setNewImage();
			myImage.setImage(newImage);
			previousImage =
					myProperties.get(VoogaBundles.spriteProperties.getString("IMAGE_PATH"))
					.getValue().toString();
		}

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
		if (myImage != null) {
			return;
		}
		ImageProperties imageProperties = new ImageProperties();
		reloadImage();
		imageProperties.loadData(myImage, initializationProperties);
		reloadCoordinates();
		reloadDimensions();
		reloadAlive();
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
		myProperties = new HashMap<>(properties);
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

	public String getImagePath () {
		return myProperties.get(VoogaBundles.spriteProperties.getString("IMAGE_PATH")).getValue()
				.toString();
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

	public Property<Number> getZ () {
		return this.myZ;
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
			Bindings.bindBidirectional(myProperties
					.get(VoogaBundles.spriteProperties.getString("WIDTH")).getProperty(),
					myImage.fitWidthProperty());
			Bindings.bindBidirectional(myProperties
					.get(VoogaBundles.spriteProperties.getString("HEIGHT")).getProperty(),
					myImage.fitHeightProperty());
		}
	}

	public void setInitializationMap (Map<String, Object> ip) {
		initializationProperties = ip;
	}

	public Property<Boolean> isAlive () {
		return this.myAlive;
	}

	public void setHeight (Double height) {
		myProperties.get(VoogaBundles.spriteProperties.getString("HEIGHT")).setValue(height);
		myImage.setFitHeight(height);
	}

	public void setWidth (Double width) {
		myProperties.get(VoogaBundles.spriteProperties.getString("WIDTH")).setValue(width);
		myImage.setFitHeight(width);
	}
}
