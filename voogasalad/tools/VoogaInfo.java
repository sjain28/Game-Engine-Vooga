package tools;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class VoogaInfo{
    
    public VoogaInfo(String message){
        info(message);
    }
    
    /**
     * Display alert message to the user
     * @param message
     */
    private void info(String message) {
            Alert info = new Alert(AlertType.CONFIRMATION);
            info.setTitle("Done!");
            info.setContentText(message);
            info.showAndWait();
    }
}
