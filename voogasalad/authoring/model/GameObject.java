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
import tools.interfaces.VoogaData;


public class GameObject extends ImageView implements Moveable, Elementable {

    private Sprite mySprite;
    private String name;

    public GameObject (Sprite sprite, String name) {
        mySprite = sprite;
        this.setId(mySprite.getId());
        this.name = name;
        this.setImage(mySprite.getImage().getImage());
        this.setTranslateX(mySprite.getPosition().getX());
        this.setTranslateY(mySprite.getPosition().getY());
        this.setOnMouseClicked(e -> ElementSelectionModel.getInstance().setSelected(this));
        this.setOnDragDetected(e -> onDrag(e));
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

    public void setProperties (Map<String, VoogaData> map) {
        mySprite.setProperties(map);
    }

	@Override
	public void setVoogaProperties(Map<String, VoogaData> newVoogaProperties) {
		// TODO Auto-generated method stub
		
	}

}
