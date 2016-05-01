package authoring.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Set;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;
import authoring.interfaces.AuthoringElementable;
import authoring.interfaces.Elementable;
import authoring.interfaces.gui.Saveable;
import authoring.interfaces.model.CompleteAuthoringModelable;
import data.DataContainerOfLists;
import data.FileWriterFromGameObjects;
import events.AnimationFactory;
import events.VoogaEvent;
import gameengine.BackEndText;
import gameengine.Sprite;
import gameengine.SpriteFactory;
import javafx.scene.Node;
import resources.VoogaBundles;
import tools.VoogaException;
import tools.VoogaNumber;
import tools.bindings.ImageProperties;
import tools.bindings.TextProperties;
import tools.interfaces.VoogaData;

/**
 * This class is the backend memory of the authoring environment
 * 
 * @author Arjun Desai
 *
 */
public class ElementManager extends Observable implements Saveable, CompleteAuthoringModelable {

    private List<Node> myGameElements;
    private List<VoogaEvent> myEventList;
    private GlobalPropertiesManager GPM;

    private SpriteFactory spriteFactory;

    private Set<String> myIds;

    private String myManagerName;

    private String filePath;
    
    /**
     * Constructor for Element Manager
     * Initializes list for gameElements and events, global properties, spritefactory, and id map
     */
    public ElementManager () {
        myGameElements = new ArrayList<>();
        myEventList = new ArrayList<>();
        GPM = new GlobalPropertiesManager();
        myIds = new HashSet<>();
        spriteFactory = new SpriteFactory();
        
        initGlobalVariablesPane();
    }
    
    /**
     * Add game elemnts to the list of game elements
     * @param: elements to add to the game element list
     */
    public void addGameElements (Node ... elements) {
        for (Node n : elements){
            AuthoringElementableMenu menu = new AuthoringElementableMenu(this,(AuthoringElementable)n);
            ((AuthoringElementable) n).setMenu(menu);
        }
        
        myGameElements.addAll(Arrays.asList(elements));
        setChanged();
        notifyObservers(myGameElements);
    }
    
    /**
     * Remove Game Elements from the list of elements
     * @param: nodes to remove from element list
     */
    public void removeGameElements (Node ... elements) {
        System.out.println("Initial size: "+myGameElements.size());
        myGameElements.removeAll(Arrays.asList(elements));
        System.out.println("After removing size"+myGameElements.size());
        
        for (Node n: elements){
            myIds.remove(n.getId());
        }
        
        setChanged();
        notifyObservers(myGameElements);
    }
    
    /**
     * @return: list of nodes (elements) to display
     */
    public List<Node> getElements () {
        return myGameElements;
    }
    
    /**
     * Add Events to the the event list
     * @param: VoogaEvents to add to the list of events
     */
    public void addEvents (VoogaEvent ... events) {
        myEventList.addAll(Arrays.asList(events));
        setChanged();
        notifyObservers(myEventList);
    }
    
    /**
     * Add Events to the the event list
     * @param: VoogaEvents to add to the list of events
     */
    public void removeEvents (VoogaEvent ... events) {
        myEventList.removeAll(Arrays.asList(events));
        setChanged();
        notifyObservers();
    }
    
    /**
     * Return Element based on the id
     * @param: id of element to return
     */
    public Node getElement (String id) {
        System.out.println("id activated: "+id);
        for (Node node : myGameElements) {
            if (node.getId().equals(id)) {
                return node;
            }
        }

        return null;
    }
    
    /**
     * Returns list of ids of all elements in elementList
     */
    public Set<String> getIds () {
        return this.myIds;
    }
    
    /**
     * Add element id to list of ids
     */
    public void addElementId (String id) {
        myIds.add(id);
    }
    
    /**
     * check if element manager has element with id
     */
    public boolean hasElement (String id) {
        return myIds.contains(id);
    }

    /**
     * Write Data to XML using XStream
     */
    @Override
    public void onSave () throws VoogaException {
        List<Elementable> elements = new ArrayList<>();
        saveLoop(elements);
        try {
            DataContainerOfLists data =
                    new DataContainerOfLists(elements, GPM.getVoogaProperties(), myEventList,
                                             spriteFactory.getArchetypeMap(), 
                                             AnimationFactory.getInstance());
            FileWriterFromGameObjects.saveGameObjects(data, getPath() + getName() + ".xml");
        }
        catch (ParserConfigurationException | TransformerException | IOException | SAXException e) {
            throw new VoogaException();
        }
    }
    
    
    private void saveLoop(List<Elementable> elements) throws VoogaException{
        for (Node element : myGameElements) {
            if (element instanceof GameObject) {
                GameObject object = (GameObject) element;
                ImageProperties ip = new ImageProperties();
                Sprite sprite = ((GameObject) element).getSprite();
                sprite.setInitializationMap(ip.storeData(object));
                elements.add(sprite);
            }
            if (element instanceof VoogaFrontEndText) {
                VoogaFrontEndText frontText = (VoogaFrontEndText) element;
                BackEndText text = (BackEndText) frontText.getElementable();
                System.out.println("Text:" +frontText.getText());
                TextProperties tp = new TextProperties();
                Map<String,Object> map = tp.storeData(frontText);
                for (String key : map.keySet()){
                    System.out.println("map entry: "+key+" "+map.get(key));
                }
                text.setInitializationMap(map);
                elements.add(text);
            }
        }
    }

    /**
     * Returns spritefactory
     */
    public SpriteFactory getSpriteFactory () {
        return spriteFactory;
    }
    
    /**
     * Returns list of sprite names
     */
    public Collection<String> getMySpriteNames () {
        Collection<String> mySpriteNames = new HashSet<String>();
        
        for (Node e : myGameElements) {
            mySpriteNames.add(((Elementable) e).getName());
        }
        return mySpriteNames;
    }
    
    /**
     * Returns id of sprite from name
     * @param: String- name of the sprite
     */
    public String getSpriteIdFromName (String name) throws VoogaException {
        for (Node e : myGameElements) {
            if (((Elementable) e).getName().equals(name)) {
                return ((Elementable) e).getId();
            }
        }
        throw new VoogaException("Can't get Sprite from the name");
    }
    
    /**
     * Get Sprite name from the id
     * @param: id of sprite
     */
    public String getSpriteNameFromId (String id) throws VoogaException {
        for (Node e : myGameElements) {
            if (((Elementable) e).getId().equals(id)) {
                return ((Elementable) e).getName();
            }
        }
        throw new VoogaException("Can't get Sprite from the id");
    }
    
    /**
     * Get list of all global variables
     */
    public Map<String, VoogaData> getGlobalVariables () {
        return GPM.getVoogaProperties();
    }
    
    /**
     * initialize global variables pane
     */
    public void initGlobalVariablesPane () {
        setChanged();
        notifyObservers(GPM);
    }
    
    /**
     * Get vooga element based on id
     */
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
    
    /**
     * Returns list of events
     */
    @Override
    public List<VoogaEvent> getEvents () {
        return myEventList;
    }
    
    /**
     * Set sprite factory based on map
     * @param archetypes: list of archetypes
     * @throws VoogaException
     */
    public void setSpriteFactory (Map<String, Sprite> archetypes) throws VoogaException {
        if (spriteFactory.getArchetypeMap().keySet().size() > 0) {
            throw new VoogaException();
        }

        spriteFactory = new SpriteFactory(archetypes);
    }
    
    /**
     * 
     * @param elementableList: list of elementable list to use
     * @throws VoogaException
     */
    public void setGameObjects (List<Node> elementableList) throws VoogaException {
        if (!myGameElements.isEmpty()) {
            throw new VoogaException();
        }

        addGameElements(elementableList.toArray(new Node [elementableList.size()]));
        myIds = new HashSet<String>();

        for (Node e : elementableList) {
            myIds.add(((Elementable) e).getId());
        }
    }
    
    /**
     * 
     * @param eventList: list of events to load 
     * @throws VoogaException
     */
    public void setEventList (List<VoogaEvent> eventList) throws VoogaException {
        if (!myEventList.isEmpty())
            throw new VoogaException();
        this.myEventList = eventList;
    }
    
    /**
     * 
     * @param globalPropertiesMap: map of global properties to set
     * @throws VoogaException
     */
    public void setGlobalProperties (Map<String, VoogaData> globalPropertiesMap) throws VoogaException {
        GPM.setVoogaProperties(globalPropertiesMap);
    }

    /**
     * Used to populate preferences
     */

    @Override
    public String getName () {
        return this.myManagerName;
    }
    
    /**
     * Sets file name to save to
     */
    @Override
    public void setName (String name) {
        this.myManagerName = name;
        this.filePath =
                getPath() + myManagerName + ".xml";
        System.out.println("The file path here is " + filePath);
    }
    
    /**
     * 
     * @return path of the current level
     */
    private String getPath(){
        return "games/" + VoogaBundles.preferences.getProperty("GameName") + "/levels/";
    }

}
