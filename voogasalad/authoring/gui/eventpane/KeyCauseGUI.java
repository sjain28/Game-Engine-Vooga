package authoring.gui.eventpane;

import authoring.interfaces.model.EditEventable;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;

public class KeyCauseGUI implements EventGUI{
	
	private HBox holder;
    private TextField textField;
    
    public KeyCauseGUI(EditEventable manager){
    	holder = new HBox();
    	ImageView iv = new ImageView();
    	iv.setPreserveRatio(true);
    	iv.setFitWidth(30);
        textField = new TextField();
        textField.setPrefWidth(30);
        textField.setOnKeyPressed(e -> {
        	iv.setImage(new Image(this.getClass().getResourceAsStream("/" + e.getCode().toString() + ".png")));
        });
        textField.textProperty().addListener((obs, old, newVal) -> {
        	if(!newVal.isEmpty()) {
        		textField.setText("");
        	}
        });
    	holder.getChildren().addAll(textField, iv);
    }
    @Override
    public Node display () {
        return holder;
    }

    @Override
    public String getDetails () {
        return "KeyCause "+textField.getText();
    }
    
}
