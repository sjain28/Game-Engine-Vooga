package authoring.model;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import authoring.interfaces.Elementable;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Background;
import tools.VoogaDataText;
import tools.interfaces.VoogaData;


public class VoogaFrontEndText extends TextField implements Elementable {

    Map<String, VoogaData> myProperties = new HashMap<String, VoogaData>();

    public VoogaFrontEndText () {
        init();
    }

    // stroke, color, font, text, size, name/group, position (x,y,z),
    public VoogaFrontEndText (double x, double y, String text) {
        
        init();
        this.setTranslateX(x);
        this.setTranslateY(y);
        this.setText(text);
    }

    public VoogaFrontEndText (String text) {
        this(0, 0, text);
    }

    private void init () {
        setId(UUID.randomUUID().toString());
        setBackground(Background.EMPTY);
        this.setOnDragDetected( (MouseEvent e) -> onDrag(e));
    }

    // TODO:
    // This method is repeated in all Elements, we should use some form of inheritance
    // hierarchy to determine this

    void onDrag (MouseEvent event) {
        Dragboard db = this.startDragAndDrop(TransferMode.MOVE);
        ClipboardContent content = new ClipboardContent();
        System.out.println("Picked up");
        content.putString(getId());
        db.setContent(content);
        event.consume();
    }

    @Override
    public Map<String, VoogaData> getVoogaProperties () {
        myProperties.put("text", new VoogaDataText(getText()));
        return myProperties;
    }

    @Override
    public void update () {

    }

    @Override
    public void addProperty (String name, VoogaData data) {
        myProperties.put(name, data);
    }

    @Override
    public void removeProperty (String name) {
        // TODO Auto-generated method stub
    }

    @Override
    public Node getNodeObject () {
        // TODO Auto-generated method stub
        return this;
    }
    
    @Override
    public String getName () {
        // TODO Auto-generated method stub
        return null;
    }

	@Override
	public void setVoogaProperties(Map<String, VoogaData> newVoogaProperties) {
		// TODO Auto-generated method stub
		
	}

}
