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
import javafx.scene.control.Tooltip;
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

/**
 * Authoring representation of the sprite in the engine
 * @author arjunaashna
 *
 */
public class GameObject extends ImageView implements Moveable, AuthoringElementable {

    private Sprite mySprite;
    private String name;
    private ContextMenu menu;

    private transient SimpleStringProperty imagePath;
    
    /**
     * Authoring representation of the sprite
     * @param sprite
     * @param name
     */
    public GameObject (Sprite sprite, String name) {
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
        
        Tooltip tp = new Tooltip();
        tp.setText("Name: "+name);
        
        Tooltip.install(this, tp);
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
        Bindings.bindBidirectional(this.imageProperty(), mySprite.getImage().imageProperty());
        
        imagePath.addListener((obs,old,n)->{
            try{
                mySprite.setImagePath(n);
                this.setImage(new Image(n));
            } catch (Exception e){
                
            }
        });
        
        this.translateZProperty().addListener( (obs, old, n) -> {
            ElementSelectionModel.getInstance().setSelected(this);
        });

    }
    public Sprite getSprite () {
        return mySprite;
    }
    
    /**
     * return velocity 
     */
    @Override
    public Vector getVelocity () {
        return null;
    }

    @Override
    public void setVelocity (Velocity velocity) {
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
    
    /**
     * get the vooga properties
     */
    @Override
    public Map<String, VoogaData> getVoogaProperties () {
        return mySprite.getParameterMap();
    }
    
    /**
     * add proeprtis to the changes
     */
    @Override
    public void addProperty (String name, VoogaData data) {
        mySprite.addProperty(name, data);

    }
    
    /**
     * remove properties basedo nthe name of the property (name)
     */
    @Override
    public void removeProperty (String name) {
        mySprite.removeProperty(name);
    }
    
    /**
     * set the name of hte object
     */
    public String getName () {
        return name;
    }
    
    /**
     * determine selector for the object
     */
    public void select (Selector selector) {
        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setBrightness(selector.getLightness());

        this.setEffect(colorAdjust);
        this.setEffect(new Glow(selector.getGlow()));
    }
    
    /**
     * Set the properties basedo n the map
     * @param map
     */
    public void setProperties (Map<String, VoogaData> map) {
        mySprite.setProperties(map);
    }
    
    /**
     * return sprite
     */
    @Override
    public Elementable getElementable () {
        return mySprite;
    }
    
    @Override
    public void setVoogaProperties (Map<String, VoogaData> newVoogaProperties) {
    }

    @Override
    public void setName (String name) {
    }

    @Override
    public Node getNodeObject () {
        return null;
    }

    @Override
    public void init () throws VoogaException {
    }

    @Override
    public void update () {
    }

    @Override
    public void setMenu (AuthoringElementableMenu menu) {
        this.menu = menu;
    }

}
