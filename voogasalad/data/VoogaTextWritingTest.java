package data;

import authoring.model.VoogaButton;
import authoring.model.VoogaText;
import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class VoogaTextWritingTest extends Application {

	private static final String testString= "levels/VoogaTextWriting.xml";
	
    @Override
    public void start (Stage primaryStage) throws Exception {
        VoogaText vt = new VoogaText();
        vt.setText("DASFASFAF");
        
        Rectangle vb = new Rectangle(3,4);

        Serializer.serialize(vb, testString);
        Object o = DeSerializer.deserialize(1, "levels/VoogaTextWriting.xml").get(0);
        Rectangle vt2 = (Rectangle) o;
        System.out.println("VoogaText");
        System.out.println(vt2);
    }
    
    public static void main (String[] args){
        launch(args);
    }

}
