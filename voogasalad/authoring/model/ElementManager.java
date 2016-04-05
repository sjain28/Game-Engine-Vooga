package authoring.model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import GameEngine.Sprite;
import authoring.interfaces.Elementable;
import authoring.interfaces.gui.Saveable;
import events.Event;
import javafx.scene.Node;
import tools.interfaces.VoogaData;


public class ElementManager implements Saveable {
    private List<Node> gameElements;
    private List<Event> eventList;
    private Map<String, VoogaData> globalVariables;
    private File xmlDataFile;
    private Set<String> currentIds;

    public ElementManager () {
        gameElements = new ArrayList<Node>();
        eventList = new ArrayList<Event>();
        globalVariables = new HashMap<String, VoogaData>();
        xmlDataFile = null;
        currentIds = new HashSet<String>();
    }

    public ElementManager (File xmlDataFile) {
        this();
        this.xmlDataFile = xmlDataFile;
    }

    public void addGameElements (Node ... elements) {
        for (Node e : elements) {
            System.out.println(e.getId());
            while (currentIds.contains(e.getId())) {
                e.setId(UUID.randomUUID().toString());
            }
            currentIds.add(e.getId());
        }
        gameElements.addAll(Arrays.asList(elements));
    }

    public void removeGameElements (Node ... elements) {
        for (Node e:elements){
            if (currentIds.contains(e.getId())){
                currentIds.remove(e.getId());
            }
        }
        gameElements.removeAll(Arrays.asList(elements));
    }

    public List<Node> getElements () {
        return gameElements;
    }

    public void addEvents (Event ... events) {
        eventList.addAll(Arrays.asList(events));
    }

    public void removeEvents (Event ... events) {
        eventList.removeAll(Arrays.asList(events));
    }
    
    public Node getElement(String id){
        for (Node node : gameElements){
            if (node.getId().equals(id)){
                return node;
            }
        }
        
        return null;
    }
    
    public boolean hasElement(String id){
        return currentIds.contains(id);
    }
    
    /**
     * Write Data to XML using XStream
     */
    @Override
    public void onSave () {
        XStream mySerializer = new XStream(new DomDriver());
        StringBuilder content = new StringBuilder();

        List<Sprite> sprites = new ArrayList<Sprite>();
        List<VoogaText> text = new ArrayList<VoogaText>();

        for (Node element : gameElements) {
            if (element instanceof GameObject) {
                sprites.add(((GameObject) element).getSprite());
            }

            if (element instanceof VoogaText) {
                text.add((VoogaText) element);
            }
        }

        content.append(mySerializer.toXML(sprites) + "\n");
        content.append(mySerializer.toXML(text) + "\n");
        content.append(eventList + "\n");
        content.append(globalVariables);

        writeToFile(content.toString());
    }

    private void writeToFile (String content) {
        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter(xmlDataFile, true);
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
