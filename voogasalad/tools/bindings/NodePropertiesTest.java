package tools.bindings;

import java.lang.reflect.Method;
import javafx.application.Application;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import resources.VoogaBundles;
import tools.VoogaException;

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
        ImageProperties np = new ImageProperties(VoogaBundles.imageProperties);
        np.storeData(image);
        
        ImageView loadedImage = new ImageView();
        np.loadData(loadedImage);
    }
    
    public static void main(String[] args){
        launch(args);
    }


}
