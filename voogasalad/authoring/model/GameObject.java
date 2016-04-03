package authoring.model;

import java.util.HashMap;
import java.util.Map;
import GameEngine.Sprite;
import authoring.interfaces.Elementable;
import authoring.interfaces.IDable;
import authoring.interfaces.Moveable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import tools.Vector;
import tools.interfaces.VoogaData;

public class GameObject extends ImageView implements Moveable,IDable, Elementable{

    private Sprite sprite;
    public GameObject (String imageID, String id) {
        super(imageID);
        this.setId(id);
        sprite = new Sprite(imageID,id);
    }

    public String getID () {
        return "";
    }
    
    //TODO: Send back immutable sprite
    Sprite getSprite(){
        return sprite;
    }


    @Override
    public Vector getVelocity () {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    public void setVelocity (Vector v) {
        // TODO Auto-generated method stub
        
    }
    
    void onDrag(MouseEvent event){
        Dragboard db = this.startDragAndDrop(TransferMode.MOVE);
        ClipboardContent content = new ClipboardContent();
        // Store node ID in order to know what is dragged.
        content.putString(getID());
        db.setContent(content);
        event.consume();
    }
}
