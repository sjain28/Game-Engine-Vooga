package authoring.model;

import authoring.interfaces.Elementable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Background;

public class VoogaText extends TextField implements Elementable{
    
    
    public VoogaText(Object id){
        this.setId(id.toString());
        //setBackground(Background.EMPTY);
        this.setOnDragDetected((MouseEvent e) -> onDrag(e));
    }
    
    void onDrag(MouseEvent event){
        Dragboard db = this.startDragAndDrop(TransferMode.MOVE);
        ClipboardContent content = new ClipboardContent();
        System.out.println("Picked up");
        content.putString(getId());
        db.setContent(content);
        event.consume();
    }
    
    

}
