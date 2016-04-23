package authoring.model;

import java.util.HashMap;
import java.util.Map;
import authoring.gui.Selector;
import authoring.interfaces.Elementable;
import authoring.interfaces.Moveable;
import gameengine.Sprite;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.Node;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import tools.Vector;
import tools.Velocity;
import tools.interfaces.VoogaData;


public class GameObject extends ImageView implements Moveable, Elementable {

    private Sprite mySprite;
    private String name;

    private transient SimpleStringProperty imagePath;

    public GameObject (Sprite sprite, String name) {
        imagePath = new SimpleStringProperty(sprite.getImagePath());
        initializeSprite(sprite);
        sprite.setName(name);
        this.name = name;
        this.setId(mySprite.getId());
        this.setImage(new Image(imagePath.get()));
        this.setOnMouseClicked(e -> ElementSelectionModel.getInstance().setSelected(this));
        this.setOnDragDetected(e -> onDrag(e));
    }

    private void initializeSprite (Sprite sprite) {
        mySprite = sprite;

        Bindings.bindBidirectional(this.translateXProperty(), mySprite.getX());
        Bindings.bindBidirectional(this.translateYProperty(), mySprite.getY());
        Bindings.bindBidirectional(this.fitWidthProperty(), mySprite.getWidth());
        Bindings.bindBidirectional(this.fitHeightProperty(), mySprite.getHeight());
        Bindings.bindBidirectional(imagePath, mySprite.getImagePathProperty());

        mySprite.getImagePathProperty().addListener( (obs, old, n) -> {
            this.setImage(new Image(n));
        });

        this.translateXProperty().addListener( (obs, old, n) -> {
            mySprite.getX().setValue(n);
            ElementSelectionModel.getInstance().setSelected(this);
        });
        this.translateYProperty().addListener( (obs, old, n) -> {
            mySprite.getY().setValue(n);
            ElementSelectionModel.getInstance().setSelected(this);
        });
    }

    // TODO: Send back immutable sprite
    public Sprite getSprite () {
        return mySprite;
    }

    @Override
    public Vector getVelocity () {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setVelocity (Velocity velocity) {
        // TODO Auto-generated method stub
    }

    void onDrag (MouseEvent event) {
        ElementSelectionModel.getInstance().setSelected(this);
        Dragboard db = this.startDragAndDrop(TransferMode.ANY);
        ClipboardContent content = new ClipboardContent();
        content.putString(getId());
        db.setContent(content);
        if (!this.imagePath.get().contains(".gif"))
            db.setDragView(this.getImage());
        event.consume();
    }

    @Override
    public Map<String, VoogaData> getVoogaProperties () {
        Map<String, VoogaData> propertiesMap = new HashMap<String, VoogaData>();
        propertiesMap.putAll(mySprite.getParameterMap());
        return propertiesMap;
    }

    @Override
    public void addProperty (String name, VoogaData data) {
        mySprite.addProperty(name, data);

    }

    @Override
    public void removeProperty (String name) {
        mySprite.removeProperty(name);
    }

    @Override
    public Node getNodeObject () {
        return mySprite.getImage();
    }

    public String getName () {
        return name;
    }

    @Override
    public void update () {

    }

    public void select (Selector selector) {
        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setBrightness(selector.getLightness());

        this.setEffect(colorAdjust);
        this.setEffect(new Glow(selector.getGlow()));
    }

    public void setProperties (Map<String, VoogaData> map) {
        mySprite.setProperties(map);
    }

    @Override
    public void setVoogaProperties (Map<String, VoogaData> newVoogaProperties) {
        // TODO Auto-generated method stub

    }

    @Override
    public void setName (String name) {
        // TODO Auto-generated method stub

    }

}
