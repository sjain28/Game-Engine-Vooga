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
import java.util.Observable;
import java.util.Observer;
import java.util.Set;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.xml.sax.SAXException;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import authoring.interfaces.Elementable;
import authoring.interfaces.gui.Saveable;
import authoring.interfaces.model.CompleteAuthoringModelable;
import data.DataContainerOfLists;
import data.FileWriterFromGameObjects;
import events.VoogaEvent;
import gameengine.Sprite;
import gameengine.SpriteFactory;
import javafx.scene.Node;
import tools.VoogaException;
import tools.interfaces.VoogaData;

public class ElementManager extends Observable implements Saveable, CompleteAuthoringModelable {

	private List<Node> myGameElements;
	private List<VoogaEvent> myEventList;

	private GlobalPropertiesManager GPM;
	private Map<String, VoogaData> myGlobalVariables;

	private File myXmlDataFile;
	private SpriteFactory spriteFactory;
	private Set<String> myIds;
	private String filePath = "levels/Test.xml";

	public ElementManager() {
		myGameElements = new ArrayList<Node>();
		myEventList = new ArrayList<VoogaEvent>();
		GPM = new GlobalPropertiesManager();
		myGlobalVariables = GPM.getVoogaProperties();
		myXmlDataFile = null;
		myIds = new HashSet<String>();
		spriteFactory = new SpriteFactory();
		myXmlDataFile = new File("/levels/Test.xml");
	}

	public ElementManager(File xmlDataFile) {
		this();
		this.myXmlDataFile = xmlDataFile;
	}

	public void addGameElements(Node... elements) {
		myGameElements.addAll(Arrays.asList(elements));
		setChanged();
		notifyObservers(myGameElements);
	}

	public void removeGameElements(Node... elements) {
		myGameElements.removeAll(Arrays.asList(elements));
		setChanged();
		notifyObservers(myGameElements);
	}

	public List<Node> getElements() {
		return myGameElements;
	}

	public void addEvents(VoogaEvent... events) {
		myEventList.addAll(Arrays.asList(events));
	}

	public void removeEvents(VoogaEvent... events) {
		myEventList.removeAll(Arrays.asList(events));
	}

	public Node getElement(String id) {
		for (Node node : myGameElements) {

			if (node.getId().equals(id)) {
				return node;
			}
		}

		return null;
	}

	public Set<String> getIds() {
		return this.myIds;
	}

	public void addElementId(String id) {
		myIds.add(id);
	}

	public boolean hasElement(String id) {
		return myIds.contains(id);
	}

	/**
	 * Write Data to XML using XStream
	 */
	@Override
	public void onSave() throws VoogaException {

		updateGlobalPropertiesMap();

		List<Elementable> elements = new ArrayList<Elementable>();

		for (Node element : myGameElements) {
			if (element instanceof GameObject) {
				Sprite sprite = ((GameObject) element).getSprite();
				elements.add(sprite);
			}

			if (element instanceof VoogaFrontEndText) {
				elements.add((VoogaFrontEndText) element);
			}
		}

		DataContainerOfLists data = new DataContainerOfLists(elements, myGlobalVariables, myEventList, spriteFactory);
		System.out.println("I'm done saving in element manager");
		try {
			System.out.println(myXmlDataFile.getPath());
			FileWriterFromGameObjects.saveGameObjects(data, filePath);
		} catch (ParserConfigurationException | TransformerException | IOException | SAXException e) {
			throw new VoogaException();
		}
	}

	public SpriteFactory getSpriteFactory() {
		return spriteFactory;
	}

	public Collection<String> getMySpriteNames() {
		Collection<String> mySpriteNames = new HashSet<String>();
		for (Node e : myGameElements) {
			mySpriteNames.add(((Elementable) e).getName());
		}
		return mySpriteNames;
	}

	public Map<String, VoogaData> getGlobalVariables() {
		updateGlobalPropertiesMap();
		return myGlobalVariables;
	}

	public void updateGlobalPropertiesMap() {
		myGlobalVariables = GPM.getVoogaProperties();
	}

	public GlobalPropertiesManager getGlobalPropertiesManager() {
		return GPM;
	}

	@Override
	public void addGlobalVariable(String name, VoogaData value) {
		myGlobalVariables.put(name, value);
		setChanged();
		notifyObservers(myGlobalVariables);
	}

	@Override
	public Elementable getVoogaElement(String id) {
		for (Node node : myGameElements) {
			if (node instanceof Elementable) {
				Elementable e = (Elementable) node;
				if (e.getID().equals(id)) {
					return e;
				}
			}
		}
		return null;
	}


}
