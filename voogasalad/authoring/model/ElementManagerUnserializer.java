package authoring.model;

import java.util.ArrayList;
import java.util.List;
import authoring.interfaces.Elementable;
import data.DataContainerOfLists;
import data.Deserializer;
import gameengine.BackEndText;
import gameengine.Sprite;
import javafx.scene.Node;
import tools.VoogaException;

public class ElementManagerUnserializer {
    
    private String xmlPath;
    private ElementManager elementManager;
    
    public ElementManagerUnserializer(String xmlPath){
        this.xmlPath=xmlPath;
        this.elementManager = new ElementManager();
    }
    
    public ElementManager unserialize() throws VoogaException{
        Object o = Deserializer.deserialize(1, xmlPath);
        System.out.println(o.getClass());
        if (!(((List) o).get(0) instanceof DataContainerOfLists)){
            throw new VoogaException("Failed to load level");
        }
        
        DataContainerOfLists data = (DataContainerOfLists) ((List)o).get(0);
        
        elementManager.setSpriteFactory(data.getArchetypeMap());
        elementManager.setEventList(data.getEventList());
        elementManager.setGlobalProperties(data.getVariableMap());
        elementManager.setGameObjects(getNodeList(data.getElementableList()));
        
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
