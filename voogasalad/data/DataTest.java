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
import gameengine.Variable;
import authoring.interfaces.Elementable;
import authoring.model.VoogaButton;
import authoring.model.VoogaText;
import events.Event;
import gameengine.Sprite;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import player.runner.GameRunner;
import tools.VoogaBoolean;


public class DataTest extends Application {

	
	
    public static void main (String[] args) {
        launch(args);
        
    }
    
//    public void serialize(DataContainerOfLists lists, String fileName) throws ParserConfigurationException, TransformerException, IOException, SAXException{
//    	mySerializer = new XStream(new DomDriver());
//       // String sprites = mySerializer.toXML(nodeList);
//       // System.out.println(sprites);
//        //mySerializer.createObjectOutputStream(out)(nodes,new FileOutputStream("HIHI.txt"))
//        
//        FileOutputStream fos = null;
//        try{            
//            String xml = mySerializer.toXML(lists);
//            fos = new FileOutputStream(fileName + ".xml");
//            fos.write("<?xml version=\"1.0\"?>".getBytes("UTF-8"));
//            byte[] bytes = xml.getBytes("UTF-8");
//            fos.write(bytes);
//
//        }catch (Exception e){
//            System.err.println("Error in XML Write: " + e.getMessage());
//        }
//        finally{
//            if(fos != null){
//                try{
//                    fos.close();
//                }catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }       
//    }
    
    @Override
    public void start (Stage primaryStage) throws Exception {
        Sprite sprite = new Sprite("/bricks.jpg","6");
        Node[] vts = new Node[10];
        Variable[] variables = new Variable[10];
        Event[] events = new Event[10];
        for (int i =0;i<3;i++){
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
        
        for (int i =0; i<10;i++){
           Variable variable = new Variable();
           variables[i] = variable;
        }
        
        for (int i =0; i<10;i++){
            Event event = new Event(null, null);
            events[i] = event;
         }
        
        List<Node> nodeList = new ArrayList<Node>();
        for (int i =0; i<4;i++){
        	nodeList.add(vts[i]);
        }
        
        List<Variable> variableArrayList = new ArrayList<Variable>();
        for (int i =0; i<4;i++){
        	variableArrayList.add(variables[i]);
        }
        
        List<Event> eventList = new ArrayList<Event>();
        for (int i =0; i<4;i++){
        	eventList.add(events[i]);
        }
        String fileName = "test_4-9";
        DataContainerOfLists data = new DataContainerOfLists(nodeList,variableArrayList, eventList);
        System.out.println(data.getVariableList());
        System.out.println(data.getEventList());
        System.out.println(data.getEventList());
        VoogaBoolean vb = new VoogaBoolean(true);
        FileWriterFromObjects fileWriter = new FileWriterFromObjects();
        fileWriter.serialize(data, fileName);
        FileReaderToObjects fileReader = new FileReaderToObjects(fileName);
        DataContainerOfLists deserializedList = fileReader.getDataContainer();
        System.out.println("DONE");
        System.out.println(deserializedList);
        System.out.println(deserializedList.getEventList());
        
    }
    
//    public DataContainerOfLists deserialize(String fileName) {
//    	XStream mySerializer = new XStream(new DomDriver());
//    	System.out.println("read person");
//        DataContainerOfLists data = new DataContainerOfLists();
//        try{
//        	File xmlFile = new File(fileName+ ".xml");
//            data = (DataContainerOfLists) mySerializer.fromXML(xmlFile);     
//            System.out.println(data);
//        }catch(Exception e){
//            System.err.println("Error in XML Read: " + e.getMessage());
//        }
//        return data;
//    }
    
}