package tools;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

import com.thoughtworks.xstream.XStream;

import GameEngine.Sprite;
import events.Event;
import GameEngine.Variable;

public class FileManager {

	private List<Sprite> spriteList;
	private List<Event> eventList;
	private List<Variable> VariableList;
	
	public FileManager(XStream xStream){
		// TODO Auto-generated constructor stub	
		parse(xStream);
	}
	
	private void parse(XStream myUnSerializer){
		 try {
			 spriteList  = (List<Sprite>) myUnSerializer.fromXML("sprites",new FileOutputStream(new File("game_data/ExampleData.xml")));
			 eventList  = (List<Event>) myUnSerializer.fromXML("events",new FileOutputStream(new File("game_data/ExampleData.xml")));
			 VariableList  = (List<Variable>) myUnSerializer.fromXML("variables",new FileOutputStream(new File("game_data/ExampleData.xml")));
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
