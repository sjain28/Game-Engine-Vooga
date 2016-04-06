package authoring.model;

import java.util.Map;
import authoring.interfaces.Elementable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Background;
import tools.interfaces.VoogaData;

public class VoogaText extends TextField implements Elementable{
    
    // stroke, color, font, text, size, name/group, position (x,y,z), 
    public VoogaText(Object id){
        this.setId(id.toString());
        setBackground(Background.EMPTY);
        this.setOnDragDetected((MouseEvent e) -> onDrag(e));
    }
    
    
    //TODO: 
    //This method is repeated in all Elements, we should use some form of inheritance 
    //hierarchy to determine this
    
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
        return null;
    }
    
    

}
