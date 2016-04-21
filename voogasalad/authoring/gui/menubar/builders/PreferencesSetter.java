package authoring.gui.menubar.builders;

import authoring.VoogaScene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PreferencesSetter extends Stage{
    private VBox contents;
    
    public PreferencesSetter(){
        contents = new VBox();
        VoogaScene scene = new VoogaScene(contents);
        this.setScene(scene);
    }
    
    public void initialize(){
        
    }

}
