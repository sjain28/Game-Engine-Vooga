package data;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import authoring.interfaces.Elementable;
import events.VoogaEvent;
import javafx.scene.Node;
import tools.interfaces.VoogaData;

public class FileReaderToGameObjects {

	private DataContainerOfLists data;
	private List<Node> nodeList;
	private List<VoogaEvent> eventList;
	private Map<String,VoogaData> variableMap;
	
	private List<String> objectNames;
	
	public FileReaderToGameObjects(String fileName){
		// TODO Auto-generated constructor stub	
		loadGameObjects(fileName);
	}
	
	@SuppressWarnings("unchecked")
	private void loadGameObjects(String fileName){
		objectNames = new ArrayList<String>();
		objectNames.add("");
		
		data = (DataContainerOfLists) UnSerializer.deserialize(1, fileName).get(0);
//		DataContainerOfLists data2 = (DataContainerOfLists) UnSerializer.deserialize(2, fileName).get(0);
//		System.out.println("The current object being loaded is from the second container: " + data2.getVariableList());
//		System.out.println("The current list being loaded is from the second container: " + data2.getEventList());
	}
	
	public List<Elementable> createNodeList(){
		return data.getElementableList();
	}
	
	public List<VoogaEvent> createEventList(){
		return data.getEventList();
	}
	
	public Map<String,VoogaData> createVariableMap(){
		return data.getVariableMap();
	}
	
	public DataContainerOfLists getDataContainer(){
		return data;
	}

}
