package authoring.model;

import java.util.ArrayList;
import java.util.List;
import authoring.interfaces.Elementable;
import data.DataContainerOfLists;
import data.Deserializer;
import data.FileReaderToGameObjects;
import gameengine.BackEndText;
import gameengine.Sprite;
import javafx.scene.Node;
import tools.VoogaException;

public class ElementManagerUnserializer {
    
    private String xmlPath;
    
    public ElementManagerUnserializer(String xmlPath){
        this.xmlPath=xmlPath;
    }
    
    public ElementManager unserialize() throws VoogaException{
        ElementManager elementManager = new ElementManager();
        
        FileReaderToGameObjects reader = new FileReaderToGameObjects(xmlPath);
        DataContainerOfLists data = reader.getDataContainer();
        
        System.out.println(elementManager.getElements().size());
        elementManager.setGameObjects(getNodeList(data.getElementableList()));
        elementManager.setSpriteFactory(data.getArchetypeMap());
        elementManager.setEventList(data.getEventList());
        elementManager.setGlobalProperties(data.getVariableMap());
        
        
        return elementManager;
    }
    
    private List<Node> getNodeList(List<Elementable> elements) throws VoogaException{
        List<Node> nodeList = new ArrayList<Node>();
        
        for (Elementable e : elements){
            if (e instanceof Sprite){
                Sprite sprite = (Sprite) e;
                sprite.init();
                nodeList.add(new GameObject((Sprite) e, e.getName()));
            }
            if (e instanceof BackEndText){
                
            }
        }
        
        return nodeList;
    }
   
    
}
