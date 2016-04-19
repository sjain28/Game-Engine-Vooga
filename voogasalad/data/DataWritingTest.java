package data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import authoring.interfaces.Elementable;
import events.KeyCause;
import events.VariableEffect;
import events.VoogaEvent;
import gameengine.Sprite;
import gameengine.SpriteFactory;
import tools.Position;
import tools.VoogaNumber;
import tools.interfaces.VoogaData;

public class DataWritingTest {
	private DataContainerOfLists manager;

	public DataWritingTest(){

	}

	@Before
	public void setup(){
		manager = new DataContainerOfLists(generateSprites(), generateGlobalVariables(),
				generateEvents(), generateSpriteFactory());
		System.out.println("My Button list here is " + manager.getButtonList());
	}

	private List<Elementable> generateSprites(){
		List<Elementable> elements = new ArrayList<Elementable>();
		for (int i=0;i<1;i++){
			Map<String,VoogaData> properties = new HashMap<String,VoogaData>();
			properties.put("health", new VoogaNumber(10d));
			Sprite sprite = new Sprite("/mario.png","healthy",properties,new VoogaNumber(8d));
			sprite.setPosition(new Position(i*20,i*10));
			elements.add(sprite);
			sprite.addProperty("gravity", new VoogaNumber(9.0));
		}
		
		return elements;
	}

	private List<VoogaEvent> generateEvents(){
		List<VoogaEvent> elements = new ArrayList<VoogaEvent>();
		for (int i =0;i<10;i++){
			VoogaEvent testEvent = new VoogaEvent();
			testEvent.addCause(new KeyCause("k", testEvent));
			testEvent.addEffect(new VariableEffect("GameWon", "toggle", testEvent));
			elements.add(testEvent);
		}
		return elements;
	}

	private Map<String,VoogaData> generateGlobalVariables(){
		Map<String,VoogaData> map = new HashMap<String,VoogaData>();
		for (int i=0;i<10;i++){
			map.put(""+i, new VoogaNumber((double) i));
		}
		
		map.put("LevelIndex",new VoogaNumber((double) -5));
		
		return map;
	}

	private SpriteFactory generateSpriteFactory(){
		SpriteFactory sf = new SpriteFactory();
		return sf;
	}

	@Test
	public void testWriting () throws ParserConfigurationException, TransformerException, IOException, SAXException {
		FileWriterFromGameObjects.saveGameObjects(manager, "TestWriting.xml");
	}

	public DataContainerOfLists getData() {
		return manager;
	}

}
