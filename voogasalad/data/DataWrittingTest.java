package data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import gameengine.Sprite;
import authoring.interfaces.Elementable;
import authoring.model.VoogaButton;
import authoring.model.VoogaText;
import gameengine.Sprite;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import player.runner.GameRunner;
import tools.VoogaBoolean;


public class DataWrittingTest extends Application {

    public static void main (String[] args) {
        launch(args);
        
    }
    
    public void serialize(List<Node> nodeList, String fileName) throws ParserConfigurationException, TransformerException, IOException, SAXException{
        XStream mySerializer = new XStream(new DomDriver());
        String sprites = mySerializer.toXML(nodeList);
       // System.out.println(sprites);
        //mySerializer.createObjectOutputStream(out)(nodes,new FileOutputStream("HIHI.txt"))
        
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.newDocument();
      
        
        InputSource is = new InputSource();
        is.setCharacterStream(new StringReader(sprites));
        doc = dBuilder.parse(is);

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
//        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
//        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        DOMSource source = new DOMSource(doc);
        File myFile = new File(fileName);
        
        StreamResult result = new StreamResult(myFile);
        transformer.transform(source, result);

        System.out.println("Attempting to read from file in: "+myFile.getCanonicalPath());
        
//        Node[] texts = (Node[]) mySerializer.fromXML(xmlFile);
//        for (Node i:texts){
//            System.out.println(i.getClass());
//        }
//        
//        System.out.println("");
//        System.out.println(texts.getClass());
    }
    
    @Override
    public void start (Stage primaryStage) throws Exception {
        Sprite sprite = new Sprite("/bricks.jpg","6");
        Node[] vts = new Node[10];
        for (int i =0;i<2;i++){
            VoogaText vt = new VoogaText(""+i);
            vt.setTranslateX(100);
            vt.setTranslateY(100);
            vt.setTranslateZ(1000);
            vts[i]=vt;
        }
        for (int i =3; i<5;i++){
            VoogaButton vt = new VoogaButton();
            vt.setTranslateX(100);
            vt.setTranslateY(100);
            vt.setTranslateZ(1000);
            vts[i]=vt;
        }
        List<Node> nodeList = new ArrayList<Node>();
        for (int i =3; i<4;i++){
        	nodeList.add(vts[i]);
        }
     //   File myFile = new File("voogasalad/data/YOYOYO.txt");
   //     System.out.println("Attempting to read from file in: "+myFile.getCanonicalPath());
    //    GameRunner gameRunner = new GameRunner(myFile);

        VoogaBoolean vb = new VoogaBoolean(true);
        
      //  serialize(nodeList, "yo.xml");   
        InputStream in = getClass().getClassLoader().getResourceAsStream("");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(new File("ece 250 notes 4-1.txt"));
   		System.out.println(doc.toString());
        
       FileReaderToObjects fileReader = new FileReaderToObjects("yo.xml");
        
        System.out.println("DONE");
        
    }
}
