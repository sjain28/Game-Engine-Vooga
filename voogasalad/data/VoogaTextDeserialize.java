package data;

import javafx.application.Application;
import javafx.stage.Stage;

public class VoogaTextDeserialize extends Application{

    @Override
    public void start (Stage primaryStage) throws Exception {
        Object o = DeSerializer.deserialize(1, "levels/Test.xml").get(0);
        LevelDataContainer vt2 = (LevelDataContainer) o;
        System.out.println("Unserialized");
        System.out.println(vt2);
        
    }
    public static void main(String[] args){
        launch(args);
    }
}
