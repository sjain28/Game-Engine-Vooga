package authoring.model;

import java.util.Map;
import authoring.interfaces.Elementable;
import javafx.scene.control.Button;

import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;

public class VoogaButton extends Button implements Elementable {

    void onDrag(MouseEvent event){
        Dragboard db = this.startDragAndDrop(TransferMode.MOVE);
        ClipboardContent content = new ClipboardContent();
        System.out.println("Picked up");
        content.putString(getId());
        db.setContent(content);
        event.consume();
    }

    @Override
    public Map<Object, Object> getVoogaProperties () {
        // TODO Auto-generated method stub
        return null;
    }
}
