package data;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import gameengine.Sprite;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tools.VoogaBoolean;


public class DataWrittingTest extends Application {

    public static void main (String[] args) {
        launch(args);
    }
    
    public void serialize(Node...nodes){
        XStream mySerializer = new XStream(new DomDriver());
        String xmlFile = mySerializer.toXML(nodes);
        System.out.println(xmlFile);
        
        Node[] texts = (Node[]) mySerializer.fromXML(xmlFile);
        for (Node i:texts){
            System.out.println(i.getClass());
        }
        
        System.out.println("");
        System.out.println(texts.getClass());
    }
    
    @Override
    public void start (Stage primaryStage) throws Exception {
        //Sprite sprite = new Sprite("/bricks.jpg","6",null);
        Node[] vts = new Node[500];
        
        for (int i =0;i<500;i++){
            TextField vt = new TextField(""+i);
            vt.setTranslateX(100);
            vt.setTranslateY(100);
            vt.setTranslateZ(1000);
            vts[i]=vt;
        }

        VoogaBoolean vb = new VoogaBoolean(true);
        
        serialize(vts);
        
        
    }
}
