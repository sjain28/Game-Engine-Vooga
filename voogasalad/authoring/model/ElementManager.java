package authoring.model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.xml.sax.SAXException;
import authoring.interfaces.Elementable;
import authoring.interfaces.gui.Saveable;
import authoring.interfaces.model.CompleteAuthoringModelable;
import data.DataContainerOfLists;
import data.FileWriterFromGameObjects;
import events.VoogaEvent;
import gameengine.Sprite;
import gameengine.SpriteFactory;
import javafx.scene.Node;
import resources.VoogaBundles;
import tools.VoogaException;
import tools.bindings.ImageProperties;
import tools.interfaces.VoogaData;


public class ElementManager extends Observable implements Saveable, CompleteAuthoringModelable {

    private List<Node> myGameElements;
    private List<VoogaEvent> myEventList;

    private GlobalPropertiesManager GPM;

    private File myXmlDataFile;
    private SpriteFactory spriteFactory;

    private Set<String> myIds;

    private String filePath = "games/levels/Test.xml";

    public ElementManager () {
        myGameElements = new ArrayList<Node>();
        myEventList = new ArrayList<VoogaEvent>();
        GPM = new GlobalPropertiesManager();
        myXmlDataFile = null;
        myIds = new HashSet<String>();
        spriteFactory = new SpriteFactory();
        myXmlDataFile = new File(filePath);
    }

    public ElementManager (File xmlDataFile) {
        this();
        this.myXmlDataFile = xmlDataFile;
    }

    public void addGameElements (Node ... elements) {
        System.out.println("ADDED");
        myGameElements.addAll(Arrays.asList(elements));
        setChanged();
        notifyObservers(myGameElements);
    }

    public void removeGameElements (Node ... elements) {
        myGameElements.removeAll(Arrays.asList(elements));
        setChanged();
        notifyObservers(myGameElements);
    }

    public List<Node> getElements () {
        return myGameElements;
    }

    public void addEvents (VoogaEvent ... events) {
        System.out.println("ADDED");
        
        myEventList.addAll(Arrays.asList(events));
        setChanged();
        notifyObservers(myEventList);
    }

    public void removeEvents (VoogaEvent ... events) {
        myEventList.removeAll(Arrays.asList(events));
        setChanged();
        notifyObservers();
    }
    public Node getElement (String id) {
        for (Node node : myGameElements) {

            if (node.getId().equals(id)) {
                return node;
            }
        }

        return null;
    }

    public Set<String> getIds () {
        return this.myIds;
    }

    public void addElementId (String id) {
        myIds.add(id);
    }

    public boolean hasElement (String id) {
        return myIds.contains(id);
    }

    /**
     * Write Data to XML using XStream
     */
    @Override
    public void onSave () throws VoogaException {
        List<Elementable> elements = new ArrayList<Elementable>();

        for (Node element : myGameElements) {
            if (element instanceof GameObject) {
                GameObject object = (GameObject) element;
                ImageProperties ip = new ImageProperties();
                Sprite sprite = ((GameObject) element).getSprite();
                sprite.setInitializationMap(ip.storeData(object));
                elements.add(sprite);
            }

            // if (element instanceof VoogaFrontEndText) {
            // elements.add((VoogaFrontEndText) element);
            // }
        }

        try {
            DataContainerOfLists data =
                    new DataContainerOfLists(elements, GPM.getVoogaProperties(), myEventList,
                                             spriteFactory.getArchetypeMap());
            System.out.println(myXmlDataFile.getPath());
            FileWriterFromGameObjects.saveGameObjects(data, filePath);
            System.out.println("I'm done saving in element manager");
        }
        catch (ParserConfigurationException | TransformerException | IOException | SAXException e) {
            e.printStackTrace();
            throw new VoogaException();
        }

        System.out.println("The save file location here is " + filePath);
    }

    public SpriteFactory getSpriteFactory () {
        return spriteFactory;
    }

    public Collection<String> getMySpriteNames () {
        Collection<String> mySpriteNames = new HashSet<String>();
        for (Node e : myGameElements) {
            mySpriteNames.add(((Elementable) e).getName());
        }
        return mySpriteNames;
    }

    public String getSpriteIdFromName (String name) throws VoogaException {
        for (Node e : myGameElements) {
            if (((Elementable) e).getName().equals(name)) {
                return ((Elementable) e).getId();
            }
        }
        throw new VoogaException("Can't get Sprite from the name");
    }

    public Map<String, VoogaData> getGlobalVariables () {
        return GPM.getVoogaProperties();
    }

    public GlobalPropertiesManager getGlobalPropertiesManager () {
        return GPM;
    }

    @Override
    public void addGlobalVariable (String name, VoogaData value) {
        GPM.addProperty(name, value);
        setChanged();
        notifyObservers(GPM.getVoogaProperties());
    }

    @Override
    public Elementable getVoogaElement (String id) {
        for (Node node : myGameElements) {
            if (node instanceof Elementable) {
                Elementable e = (Elementable) node;
                if (e.getId().equals(id)) {
                    return e;
                }
            }
        }
        return null;
    }

    @Override
    public List<VoogaEvent> getEvents () {
        return myEventList;
    }

    public void setSpriteFactory (Map<String, Sprite> archetypes) throws VoogaException {
        if (spriteFactory.getArchetypeMap().keySet().size() > 0) {
            throw new VoogaException();
        }

        spriteFactory = new SpriteFactory(archetypes);
    }

    public void setGameObjects (List<Node> elementableList) throws VoogaException {
        if (!myGameElements.isEmpty()){
            throw new VoogaException();
        }
        
        this.myGameElements = elementableList;
        myIds = new HashSet<String>();

        for (Node e : elementableList) {
            myIds.add(((Elementable) e).getId());
        }
    }
    
    public void setEventList(List<VoogaEvent> eventList) throws VoogaException{
        if (!myEventList.isEmpty()) throw new VoogaException();
        this.myEventList = eventList;
    }
    
    public void setGlobalProperties(Map<String,VoogaData> globalPropertiesMap) throws VoogaException{
        if (!GPM.getVoogaProperties().isEmpty()) {
            throw new VoogaException();
        }
        
        GPM.setVoogaProperties(globalPropertiesMap);
    }

}
