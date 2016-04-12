package authoring.model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.xml.sax.SAXException;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import authoring.interfaces.Elementable;
import authoring.interfaces.gui.Saveable;
import authoring.interfaces.model.CompleteAuthoringModelable;
import auxiliary.VoogaException;
import data.DataContainerOfLists;
import data.FileWriterFromGameObjects;
import events.VoogaEvent;
import gameengine.SpriteFactory;
import javafx.scene.Node;
import tools.interfaces.VoogaData;


public class ElementManager implements Saveable, CompleteAuthoringModelable {
 
    private List<Node> myGameElements;
    private List<VoogaEvent> myEventList;
    private Map<String, VoogaData> myGlobalVariables;
    private File myXmlDataFile;
    private SpriteFactory spriteFactory;
    private Set<String> myIds;

    public ElementManager () {
        myGameElements = new ArrayList<Node>();
        myEventList = new ArrayList<VoogaEvent>();
        myGlobalVariables = new HashMap<String, VoogaData>();
        myXmlDataFile = null;
        myIds = new HashSet<String>();
        spriteFactory = new SpriteFactory();
    }

    public ElementManager (File xmlDataFile) {
        this();
        this.myXmlDataFile = xmlDataFile;
    }

    public void addGameElements (Node ... elements) {
        myGameElements.addAll(Arrays.asList(elements));
    }

    public void removeGameElements (Node ... elements) {
        myGameElements.removeAll(Arrays.asList(elements));
    }

    public List<Node> getElements () {
        return myGameElements;
    }

    public void addEvents (VoogaEvent ... events) {
        myEventList.addAll(Arrays.asList(events));
    }

    public void removeEvents (VoogaEvent ... events) {
        myEventList.removeAll(Arrays.asList(events));
    }

    public Node getElement (String id) {
        for (Node node : myGameElements) {

            if (node.getId().equals(id)) {
                return node;
            }
        }

        return null;
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
                elements.add(((GameObject) element).getSprite());
            }

            if (element instanceof VoogaText) {
                elements.add((VoogaText) element);
            }
        }

        DataContainerOfLists data = new DataContainerOfLists(elements,myGlobalVariables,myEventList);
        try {
            FileWriterFromGameObjects.saveGameObjects(data,myXmlDataFile.getPath());
        }
        catch (ParserConfigurationException | TransformerException | IOException | SAXException e) {
            throw new VoogaException();
        }
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

    public Map<String, VoogaData> getGlobalVariables () {
        return myGlobalVariables;
    }
}
