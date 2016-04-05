package data;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import GameEngine.Sprite;
import authoring.model.VoogaText;
import javafx.application.Application;
import javafx.stage.Stage;
import tools.VoogaBoolean;


public class DataWrittingTest extends Application {

    public static void main (String[] args) {
        launch(args);
    }

    @Override
    public void start (Stage primaryStage) throws Exception {
        //Sprite sprite = new Sprite("images/bricks.jpg","6");
        VoogaText[] vts = new VoogaText[500];
        for (int i =0;i<500;i++){
            VoogaText vt = new VoogaText(""+i);
            vt.setTranslateX(100);
            vt.setTranslateY(100);
            vt.setTranslateZ(1000);
            vts[i]=vt;
        }

        VoogaBoolean vb = new VoogaBoolean(true);
        XStream mySerializer = new XStream(new DomDriver());
        String xmlFile = mySerializer.toXML(vts);
        System.out.println(xmlFile);
        VoogaText[] texts = (VoogaText[]) mySerializer.fromXML(xmlFile);
        System.out.println(texts.length);

    }
}
