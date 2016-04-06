package data;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import gameengine.Sprite;
import authoring.interfaces.Elementable;
import authoring.model.VoogaButton;
import authoring.model.VoogaText;
import gameengine.Sprite;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.stage.Stage;
import tools.VoogaBoolean;


public class DataWrittingTest extends Application {

    public static void main (String[] args) {
        launch(args);
        
    }

    @Override
    public void start (Stage primaryStage) throws Exception {
        //Sprite sprite = new Sprite("images/bricks.jpg","6");
        Elementable[] vts = new Elementable[1000];
        Sprite sprite = new Sprite("images/bricks.jpg","6",0,0);
        Node[] vtsd = new Node[500];
        for (int i =0;i<500;i++){
            VoogaText vt = new VoogaText(""+i);
            vt.setTranslateX(100);
            vt.setTranslateY(100);
            vt.setTranslateZ(1000);
            vts[i]=vt;
        }
        for (int i =501;i<1000;i++){
            VoogaButton vt = new VoogaButton();
            vt.setTranslateX(100);
            vt.setTranslateY(100);
            vt.setTranslateZ(1000);
            vts[i]=vt;
        }

        VoogaBoolean vb = new VoogaBoolean(true);
        XStream mySerializer = new XStream(new DomDriver());
        String xmlFile = mySerializer.toXML(vts);
        System.out.println(xmlFile);
        
        Node[] texts = (Node[]) mySerializer.fromXML(xmlFile);
        for (Node i:texts){
            System.out.println(i.getClass());
        }
        
        System.out.println("");
        System.out.println(texts.getClass());
        
        
    }
}
