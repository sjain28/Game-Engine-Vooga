package tools.bindings;

import java.util.Map;
import javafx.application.Application;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * 'Test' for handling text operations
 */
public class NodePropertiesTest extends Application{

    @Override
    public void start (Stage primaryStage) throws Exception {
        ImageView image = new ImageView();
        image.setFitHeight(10000);
        image.setFitWidth(1000);
        image.setOpacity(0.6);
        image.setTranslateX(1);
        image.setTranslateY(2);
        image.setTranslateZ(3);
        ImageProperties np = new ImageProperties();
        Map<String,Object> storedData=np.storeData(image);
        
        ImageView loadedImage = new ImageView();
        np.loadData(loadedImage,storedData);
        System.out.println(loadedImage.getFitHeight()+" "+loadedImage.getFitWidth());
    }
    
    public static void main(String[] args){
        launch(args);
    }


}
