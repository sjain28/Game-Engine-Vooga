package authoring.model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import GameEngine.Sprite;
import authoring.interfaces.Elementable;
import authoring.interfaces.gui.Saveable;
import events.Event;
import tools.interfaces.VoogaData;

public class ElementManager implements Saveable{
    private List<Elementable> gameElements;
    private List<Event> eventList;
    private Map<String,VoogaData> globalVariables;
    private File xmlDataFile;
    
    public ElementManager(){
        gameElements = new ArrayList<Elementable>();
        eventList = new ArrayList<Event>();
        globalVariables = new HashMap<String,VoogaData> ();
        xmlDataFile = null;
    }
    
    public ElementManager(File xmlDataFile){
        this();
        this.xmlDataFile=xmlDataFile;
    }
    
    void addGameElements(Elementable... elements){
        gameElements.addAll(Arrays.asList(elements));
    }
    
    void removeGameElements(Elementable...elements){
        gameElements.removeAll(Arrays.asList(elements));
    }
    
    List<Elementable> getElements(){
        return gameElements;
    }
    
    void addEvents(Event... events){
        eventList.addAll(Arrays.asList(events));
    }
    
    void removeEvents(Event...events){
        eventList.removeAll(Arrays.asList(events));
    }
    
    /**
     * Write Data to XML using XStream
     */
    @Override
    public void onSave () {
        XStream mySerializer = new XStream(new DomDriver());
        StringBuilder content= new StringBuilder();
        
        List<Sprite> sprites = new ArrayList<Sprite>();
        List<VoogaText> text = new ArrayList<VoogaText>();
        
        for (Elementable element : gameElements){
            if (element instanceof GameObject){
                sprites.add(((GameObject) element).getSprite());
            }
            
            if (element instanceof VoogaText){
                text.add((VoogaText) element);
            }
        }
        
        content.append(mySerializer.toXML(sprites)+"\n");
        content.append(mySerializer.toXML(text)+"\n");
        content.append(eventList+"\n");
        content.append(globalVariables);
        
        writeToFile(content.toString());
    }
    
    private void writeToFile(String content){
        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter(xmlDataFile,true);
            fileWriter.write(content.toString());
            fileWriter.flush();
            fileWriter.close();
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
}
