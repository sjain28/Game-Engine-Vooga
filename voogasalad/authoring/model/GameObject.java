package authoring.model;


import java.util.Map;
import authoring.interfaces.Elementable;
import authoring.interfaces.Moveable;
import gameengine.Sprite;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import tools.Vector;
import tools.interfaces.VoogaData;

public class GameObject extends ImageView implements Moveable, Elementable{

    private Sprite mySprite;

    public GameObject (String imagePath, String Archtype) {
        super(imagePath);
        mySprite = new Sprite(imagePath,getId(),null);
        mySprite.setPosition(new Vector(this.getTranslateX(),this.getTranslateY()));
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
	public void removeProperty(String name) {
		// TODO Auto-generated method stub
		
	}
}
