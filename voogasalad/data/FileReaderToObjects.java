package data;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

import events.Event;
import gameengine.Variable;
import javafx.scene.Node;

public class FileReaderToObjects {

	private List<Node> nodeList;
	private List<Event> eventList;
	private List<Variable> VariableList;
	
	public FileReaderToObjects(String fileName){
		// TODO Auto-generated constructor stub	
		createObjects(fileName);
	}
	
	@SuppressWarnings("unchecked")
	private void createObjects(String fileName){
		XStream myUnSerializer = new XStream(new StaxDriver());
		try {
			 nodeList  = (List<Node>) myUnSerializer.fromXML("nodes",new FileInputStream(new File(fileName)));
			 eventList  = (List<Event>) myUnSerializer.fromXML("events",new FileInputStream(new File(fileName)));
			 VariableList  = (List<Variable>) myUnSerializer.fromXML("variables",new FileInputStream(new File(fileName)));
	    }
	    catch (FileNotFoundException e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	    }
	}
	
	public List<Node> createNodeList(){
		return nodeList;
	}
	
	public List<Event> createEventList(){
		return eventList;
	}
	
	public List<Variable> createVariableList(){
		return VariableList;
	}

}
