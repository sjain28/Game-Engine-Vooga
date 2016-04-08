package data;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.StaxDriver;

import events.VoogaEvent;
import gameengine.Variable;
import javafx.scene.Node;

public class FileReaderToObjects {

	private DataContainerOfLists data;
	
	private Object deSerializedObject;
	
	private List<Node> nodeList;
	private List<VoogaEvent> eventList;
	private List<Variable> VariableList;
	
	/*public FileReaderToObjects(){
		// TODO Auto-generated constructor stub	
		//createObjects(fileName);
	}*/
	
	@SuppressWarnings("unchecked")
	/*public void createObjects(String fileName){
		
//		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
//        try{
//        	 DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
//        	 File file = new File(fileName);
//        	 Document doc = dBuilder.parse(file);
//        	 System.out.println(file.length());
//	         String sprite = doc.toString();
//	         System.out.println(sprite);
//     		XStream myUnSerializer = new XStream(new DomDriver());
//    		try {
//    			 nodeList  = (List<Node>) myUnSerializer.fromXML(sprite);
//    			 eventList  = (List<Event>) myUnSerializer.fromXML("events",new FileInputStream(new File(fileName)));
//    			 VariableList  = (List<Variable>) myUnSerializer.fromXML("variables",new FileInputStream(new File(fileName)));
//    	    }
//    	    catch (FileNotFoundException e) {
//    	         // TODO Auto-generated catch block
//    	         e.printStackTrace();
//    	    }
//        }
//        catch (Exception e) {
//	         // TODO Auto-generated catch block
//	         e.printStackTrace();
//	    }
		
		deserializeObject(fileName);

	}
	
	/*private DataContainerOfLists deserialize(String fileName) {
    	XStream mySerializer = new XStream(new DomDriver());
    	System.out.println("read person");
        data = new DataContainerOfLists();
        try{
        	File xmlFile = new File(fileName+ ".xml");
            data = (DataContainerOfLists) mySerializer.fromXML(xmlFile);     
            System.out.println(data);
        }catch(Exception e){
            System.err.println("Error in XML Read: " + e.getMessage());
        }
        return data;
    }*/
	
	public Object deserializeObject(String fileName) {
    	XStream mySerializer = new XStream(new DomDriver());
        try{
        	File xmlFile = new File(fileName+ ".xml");
        	deSerializedObject = mySerializer.fromXML(xmlFile);     
        }catch(Exception e){
            System.err.println("Error in XML Read: " + e.getMessage());
        }
        return deSerializedObject;
    }
	
	/*public List<Node> createNodeList(){
		return data.getNodeList();
	}
	
	public List<VoogaEvent> createEventList(){
		return data.getEventList();
	}
	
	public List<Variable> createVariableList(){
		return data.getVariableList();
	}
	
	public DataContainerOfLists getDataContainer(){
		return (DataContainerOfLists) deSerializedObject;
	}*/

}
