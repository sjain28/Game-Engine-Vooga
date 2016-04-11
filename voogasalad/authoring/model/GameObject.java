package authoring.model;

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
    
    public GameObject(Sprite sprite){
        mySprite = sprite;
        this.setImage(mySprite.getImage().getImage());
        this.setTranslateX(mySprite.getPosition().getX());
        this.setTranslateY(mySprite.getPosition().getY());
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
    //public void setVelocity (Vector v) {
    // I CHANGED THIS TYPE TO VELOCITY INSTEAD OF VECTOR
    public void setVelocity (Velocity velocity) {
        // TODO Auto-generated method stub
    }

    void onDrag (MouseEvent event) {
        Dragboard db = this.startDragAndDrop(TransferMode.MOVE);
        ClipboardContent content = new ClipboardContent();
        // Store node ID in order to know what is dragged.
        content.putString(getId());
        db.setContent(content);
        event.consume();
    }

    @Override
    public Map<String, VoogaData> getVoogaProperties () {
        return null;
    }

    @Override
    public void update () {
        // TODO Auto-generated method stub

    }

    @Override
    public void addProperty (String name, VoogaData data) {
        mySprite.addProperty(name, data);

    }

    @Override
    public void removeProperty (String name) {
        
    }

	@Override
	public Node getNodeObject() {
		// TODO Auto-generated method stub
		return mySprite.getImage();
	}
    
    
    
}
