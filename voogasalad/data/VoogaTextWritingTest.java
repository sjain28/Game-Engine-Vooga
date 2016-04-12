package data;

import authoring.model.VoogaText;
import javafx.application.Application;
import javafx.stage.Stage;

public class VoogaTextWritingTest extends Application {

    @Override
    public void start (Stage primaryStage) throws Exception {
        VoogaText vt = new VoogaText();
        vt.setText("DASFASFAF");
        
        Serializer.serialize(vt, "levels/Test.xml");
        Object o = DeSerializer.deserialize(1, "levels/Test.xml").get(0);
        VoogaText vt2 = (VoogaText) o;
        System.out.println("VoogaText");
        System.out.println(vt2);
    }
    
    public static void main (String[] args){
        launch(args);
    }

}
