package authoring.model;


import authoring.interfaces.Elementable;
import authoring.interfaces.Moveable;
import gameengine.Sprite;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import tools.Vector;

public class GameObject extends ImageView implements Moveable, Elementable{

    private Sprite mySprite;

    public GameObject (String imagePath, Object id) {
        super(imagePath);
        this.setId(id.toString());
        mySprite = new Sprite(imagePath,getId(),this.getTranslateX(),this.getTranslateY());
    }
    
    //TODO: Send back immutable sprite
    Sprite getSprite(){
        return mySprite;
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
        content.putString(getId());
        db.setContent(content);
        event.consume();
    }
}
