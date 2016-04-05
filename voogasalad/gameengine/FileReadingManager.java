package gameengine;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

import events.Event;

public class FileReadingManager {

	private List<Sprite> spriteList;
	private List<Event> eventList;
	private List<Variable> VariableList;
	
	public FileReadingManager(String fileName){
		// TODO Auto-generated constructor stub	
		createObjects(fileName);
	}
	
	@SuppressWarnings("unchecked")
	private void createObjects(String fileName){
		XStream myUnSerializer = new XStream(new StaxDriver());
		try {
			 spriteList  = (List<Sprite>) myUnSerializer.fromXML("sprites",new FileInputStream(new File(fileName)));
			 eventList  = (List<Event>) myUnSerializer.fromXML("events",new FileInputStream(new File(fileName)));
			 VariableList  = (List<Variable>) myUnSerializer.fromXML("variables",new FileInputStream(new File(fileName)));
	    }
	    catch (FileNotFoundException e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	    }
	}
	
	public List<Sprite> createSpriteList(){
		return spriteList;
	}
	
	public List<Event> createEventList(){
		return eventList;
	}
	
	public List<Variable> createVariableList(){
		return VariableList;
	}

}
