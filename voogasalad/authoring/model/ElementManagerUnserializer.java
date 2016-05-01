package authoring.model;

import java.util.ArrayList;
import java.util.List;

import authoring.interfaces.Elementable;
import data.DataContainerOfLists;
import data.FileReaderToGameObjects;
import events.AnimationFactory;
import gameengine.BackEndText;
import gameengine.Sprite;
import javafx.scene.Node;
import tools.VoogaException;

public class ElementManagerUnserializer {
    
    private String xmlPath;
    
    public ElementManagerUnserializer(String xmlPath){
        this.xmlPath=xmlPath;
    }
    
    /**
     * Unserialize data to create a new Element Manager
     * @return
     * @throws VoogaException
     */
    public ElementManager unserialize() throws VoogaException{
        ElementManager elementManager = new ElementManager();

        FileReaderToGameObjects reader = new FileReaderToGameObjects(xmlPath);
        DataContainerOfLists data = reader.getDataContainer();
                
        elementManager.setGameObjects(getNodeList(data.getElementableList()));
        elementManager.setSpriteFactory(data.getArchetypeMap());
        elementManager.setEventList(data.getEventList());
        elementManager.setGlobalProperties(data.getVariableMap());
        AnimationFactory.set(data.getAnimationFactory());
        
        return elementManager;
    }
    
    /**
     * Get list of nodes to add to node list to elementManager
     * @param elements: list of elementable to populate
     * @return
     * @throws VoogaException
     */
    private List<Node> getNodeList(List<Elementable> elements) throws VoogaException{
        List<Node> nodeList = new ArrayList<Node>();
        
        for (Elementable e : elements){
            if (e instanceof Sprite){
                Sprite sprite = (Sprite) e;
                sprite.init();
                nodeList.add(new GameObject(sprite, e.getName()));
            }
            if (e instanceof BackEndText){
                BackEndText text = (BackEndText) e;
                text.init();
                nodeList.add(new VoogaFrontEndText(text));
            }
        }
        
        return nodeList;
    }
   
    
}
