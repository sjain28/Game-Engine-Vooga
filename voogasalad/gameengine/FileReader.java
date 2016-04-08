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
	public LevelHandler createLevelObjects(String fileName){
		XStream myUnSerializer = new XStream(new StaxDriver());
		try {
			 List<Sprite> spriteList  = (List<Sprite>) myUnSerializer.fromXML("sprites",new FileInputStream(new File(fileName)));
			 List<VoogaEvent> eventList  = (List<VoogaEvent>) myUnSerializer.fromXML("events",new FileInputStream(new File(fileName)));
			 List<Variable> VariableList  = (List<Variable>) myUnSerializer.fromXML("variables",new FileInputStream(new File(fileName)));
			 //TODO: What about global variables, etc.
			 
			 //DETERMINE EXACTLY WHAT DATA IS BEING PASSED AND WHAT THE CONSTRUCTORS OF
			 //ALL THESE OBJECTS SHOULD BE
			 EngineManager objectmanager = new EngineManager(spriteList, null, null);
			 EventManager eventmanager = new EventManager(objectmanager);
			 
			 return new LevelHandler(objectmanager,eventmanager);

		}
	    catch (FileNotFoundException e) {
	         e.printStackTrace();
	    }
		return null;
	}

}
