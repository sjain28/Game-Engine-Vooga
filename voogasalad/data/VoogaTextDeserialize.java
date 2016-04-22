package data;

import javafx.application.Application;
import javafx.stage.Stage;

public class VoogaTextDeserialize extends Application{

    @Override
    public void start (Stage primaryStage) throws Exception {
        Object o = Deserializer.deserialize(1, "levels/Test.xml").get(0);
        DataContainerOfLists vt2 = (DataContainerOfLists) o;
//        System.out.println("Unserialized");
//        System.out.println(vt2);
        
    }
    public static void main(String[] args){
        launch(args);
    }
}
