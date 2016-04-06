package authoring.model;

import java.util.Map;
import authoring.interfaces.Elementable;
import javafx.scene.control.Button;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import tools.interfaces.VoogaData;

public class VoogaButton extends Button implements Elementable {
    
    Map<String,VoogaData> myProperties;
    
    void onDrag(MouseEvent event){
        Dragboard db = this.startDragAndDrop(TransferMode.MOVE);
        ClipboardContent content = new ClipboardContent();
        System.out.println("Picked up");
        content.putString(getId());
        db.setContent(content);
        event.consume();
    }

    @Override
    public Map<String, VoogaData> getVoogaProperties () {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void update () {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void addProperty (String name, VoogaData data) {
        myProperties.put(name, data);
        
    }
    
    
}
