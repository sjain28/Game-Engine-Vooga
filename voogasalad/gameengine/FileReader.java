package gameengine;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

import events.VoogaEvent;

/**File reader uses singleton design pattern**/

public class FileReader {
    private static FileReader fileReaderInstance = null;
	private List<Sprite> spriteList;
	private List<VoogaEvent> eventList;
	private List<Variable> VariableList;
	
	/**
     * Singleton Design Pattern Implementation
     * 
     * Only one instance of FileReadingManager can exist in the project
     * 
     * @return private instance of filereader
     */
    public static FileReader getFileReaderInstance () {
        if (fileReaderInstance == null) {
        	fileReaderInstance = new FileReader();
        }
        return fileReaderInstance;
    }
        
	@SuppressWarnings("unchecked")
	public void createLevelObjects(String fileName){
		XStream myUnSerializer = new XStream(new StaxDriver());
		try {
			 spriteList  = (List<Sprite>) myUnSerializer.fromXML("sprites",new FileInputStream(new File(fileName)));
			 eventList  = (List<VoogaEvent>) myUnSerializer.fromXML("events",new FileInputStream(new File(fileName)));
			 VariableList  = (List<Variable>) myUnSerializer.fromXML("variables",new FileInputStream(new File(fileName)));
	    }
	    catch (FileNotFoundException e) {
	         e.printStackTrace();
	    }
	}
	
	public List<Sprite> extractSpriteList(){
		return spriteList;
	}
	
	public List<VoogaEvent> extractEventList(){
		return eventList;
	}
	
	public List<Variable> extractVariableList(){
		return VariableList;
	}

}
