package authoring.gui.toolbar.toolbaritems;

import java.io.File;
import authoring.VoogaScene;
import authoring.gui.toolbar.ToolbarItemHandler;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class Open extends ToolbarItemHandler{
    
    public Open(){
        handle();
    }
    
    @Override
    public void handle () {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(
                new ExtensionFilter("XML Files", "*.xml")
                );
        Stage stage = new Stage();
        File selectedFile = fileChooser.showOpenDialog(stage);
    }

}
