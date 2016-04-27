package authoring.gui;

import authoring.VoogaScene;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DataBaseDisplay extends Stage {
    
    public DataBaseDisplay(){
        VBox content = new VBox();
        HBox header = makeHeader();
        HBox data = makeData();
        Scene scene = new VoogaScene(content);
        content.getChildren().addAll(header, data);
        this.setScene(scene);
    }

    private HBox makeHeader () {
        HBox ans = new HBox();
        ImageView pict = new ImageView();
        pict.setImage();
        
        return ans;
    }

    private HBox makeData () {
        HBox ans = new HBox();
        return ans;
    }
}
