package authoring.model;

import java.util.HashMap;
import java.util.Map;
import authoring.interfaces.Elementable;
import authoring.interfaces.Moveable;
import gameengine.Sprite;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import tools.Vector;
import tools.Velocity;
import tools.VoogaNumber;
import tools.interfaces.VoogaData;


public class GameObject extends ImageView implements Moveable, Elementable {

    private Sprite mySprite;
    private String name;

    public GameObject (Sprite sprite) {
        mySprite = sprite;
        this.setId(mySprite.getID());
        this.name = mySprite.getID();
        this.setImage(mySprite.getImage().getImage());
        this.setTranslateX(mySprite.getPosition().getX());
        this.setTranslateY(mySprite.getPosition().getY());
        this.setOnMouseClicked(e -> ElementSelectionModel.getInstance().setSelected(this));
        this.setOnDragDetected(e -> onDrag(e));
    }

    // TODO: Send back immutable sprite
    Sprite getSprite () {
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
        Dragboard db = this.startDragAndDrop(TransferMode.ANY);
        ClipboardContent content = new ClipboardContent();
        content.putString(getId());
        db.setContent(content);
        db.setDragView(this.getImage());
        event.consume();
    }

    @Override
    public Map<String, VoogaData> getVoogaProperties () {
        Map<String, VoogaData> propertiesMap = new HashMap<String, VoogaData>();
        propertiesMap.putAll(mySprite.getParameterMap());
        
//        propertiesMap.put("width", new VoogaNumber(this.getFitWidth()));
//        propertiesMap.put("height", new VoogaNumber(this.getFitHeight()));
//        propertiesMap.put("positionX", new VoogaNumber(mySprite.getPosition().getX()));
//        propertiesMap.put("positionY", new VoogaNumber(mySprite.getPosition().getX()));

        return propertiesMap;
    }

    @Override
    public void addProperty (String name, VoogaData data) {
        mySprite.addProperty(name, data);
    }

    @Override
    public void removeProperty (String name) {

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

    @Override
    public String getID () {
        // TODO Auto-generated method stub
        return this.mySprite.getID();
    }
    
    public void setProperties (Map<String,VoogaData> map){
        mySprite.setProperties(map);
    }

}
