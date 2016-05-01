package authoring.gui.eventpane;

import authoring.interfaces.model.EditEventable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import resources.VoogaBundles;

public class KeyCauseGUI implements EventGUI{
	
    private HBox holder;
    private TextField textField;
    private KeyCode clicked;
    private ComboBox<String> keyOptions;
    
    private static final int KEY_CAUSE_GUI_WIDTH = 30;
    public KeyCauseGUI(EditEventable manager){
    	holder = new HBox();
    	ImageView iv = new ImageView();
    	iv.setPreserveRatio(true);
    	iv.setFitWidth(KEY_CAUSE_GUI_WIDTH);
        textField = new TextField();
        textField.setPrefWidth(KEY_CAUSE_GUI_WIDTH);
        textField.setOnKeyPressed(e -> {
        	iv.setImage(new Image(this.getClass().getResourceAsStream("/" + e.getCode().toString() + ".png")));
        	clicked = e.getCode();
        });
        textField.textProperty().addListener((obs, old, newVal) -> {
        	if(!newVal.isEmpty()) {
        		textField.setText("");
        	}
        });
        keyOptions = new ComboBox<>();
        keyOptions.getItems().addAll(VoogaBundles.EventMethods.getString("Press"),VoogaBundles.EventMethods.getString("Release"));
    	holder.getChildren().addAll(keyOptions,textField, iv);
    }
    @Override
    public Node display () {
        return holder;
    }

    @Override
    public String getDetails () {
        return "events.KeyCause,"+clicked.toString()+","+keyOptions.getValue();
    }
    
}
