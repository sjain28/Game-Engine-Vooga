package authoring.model;

import java.util.Map;
import authoring.gui.Selector;
import authoring.interfaces.AuthoringElementable;
import authoring.interfaces.Elementable;
import authoring.interfaces.Moveable;
import gameengine.Sprite;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import tools.Vector;
import tools.Velocity;
import tools.VoogaException;
import tools.interfaces.VoogaData;


public class GameObject extends ImageView implements Moveable, AuthoringElementable {

    private Sprite mySprite;
    private String name;
    private ContextMenu menu;

    private transient SimpleStringProperty imagePath;

    public GameObject (Sprite sprite, String name) {
        System.out.println("image path property: " + sprite.getImagePathProperty());
        initializeSprite(sprite);
        sprite.setName(name);
        this.name = name;
        this.setId(mySprite.getId());
        this.setImage(new Image(imagePath.get()));
        this.setOnMouseClicked(e -> {
            if (e.getButton().equals(MouseButton.PRIMARY)) {
                ElementSelectionModel.getInstance().setSelected(this);
            }
            if (e.getButton().equals(MouseButton.SECONDARY)) {
                menu.show(this, e.getScreenX(), e.getScreenY());
            }
        });
        this.setOnDragDetected(e -> onDrag(e));
    }

    private void initializeSprite (Sprite sprite) {
        mySprite = sprite;
        imagePath = new SimpleStringProperty();

        Bindings.bindBidirectional(this.translateXProperty(), mySprite.getX());
        Bindings.bindBidirectional(this.translateYProperty(), mySprite.getY());
        Bindings.bindBidirectional(this.translateZProperty(), mySprite.getZ());
        Bindings.bindBidirectional(this.fitWidthProperty(), mySprite.getWidth());
        Bindings.bindBidirectional(this.fitHeightProperty(), mySprite.getHeight());
        Bindings.bindBidirectional(this.imagePath, mySprite.getImagePathProperty());
        Bindings.bindBidirectional(this.visibleProperty(), mySprite.isAlive());
        Bindings.bindBidirectional(this.imageProperty(), sprite.getImage().imageProperty());

        this.translateZProperty().addListener( (obs, old, n) -> {
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

    private void onDrag (MouseEvent event) {
        ElementSelectionModel.getInstance().setSelected(this);
        Dragboard db = this.startDragAndDrop(TransferMode.ANY);
        ClipboardContent content = new ClipboardContent();
        content.putString(getId());
        db.setContent(content);
        if (!this.imagePath.get().contains(".gif")) {
            db.setDragView(this.getImage());
        }
        event.consume();
    }

    @Override
    public Map<String, VoogaData> getVoogaProperties () {
        return mySprite.getParameterMap();
    }

    @Override
    public void addProperty (String name, VoogaData data) {
        mySprite.addProperty(name, data);

    }

    @Override
    public void removeProperty (String name) {
        mySprite.removeProperty(name);
    }

    public String getName () {
        return name;
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
    public Elementable getElementable () {
        return mySprite;
    }

    @Override
    public void setVoogaProperties (Map<String, VoogaData> newVoogaProperties) {
        // TODO Auto-generated method stub

    }

    @Override
    public void setName (String name) {
        // TODO Auto-generated method stub

    }

    @Override
    public Node getNodeObject () {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void init () throws VoogaException {
        // TODO Auto-generated method stub

    }

    @Override
    public void update () {
        // TODO Auto-generated method stub

    }

    @Override
    public void setMenu (AuthoringElementableMenu menu) {
        this.menu = menu;
    }

}
