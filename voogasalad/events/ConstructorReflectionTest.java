package events;

import java.lang.reflect.Constructor;
import javafx.application.Application;
import javafx.stage.Stage;

public class ConstructorReflectionTest extends Application{

    @Override
    public void start (Stage primaryStage) throws Exception {
        Constructor[] cs= SpriteEffect.class.getConstructors(); 
        for (Constructor c:cs ){
            System.out.println(c);
        }
    }
    
    public static void main(String[] args){
        launch(args);
    }
    
}
