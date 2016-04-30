package tests;

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
import data.DataContainerOfLists;
import data.FileWriterFromGameObjects;
import events.VoogaEvent;
import gameengine.Sprite;
import tools.Position;
import tools.VoogaNumber;
import tools.interfaces.VoogaData;

public class DataWritingTest {
	private DataContainerOfLists manager;

	public DataWritingTest(){

	}

	@Before
	public void setup(){
		//manager = new DataContainerOfLists(generateSprites(), generateGlobalVariables(),generateEvents(), new HashMap<String,Sprite>());
//		manager.addButton(generateButtons());
	//	System.out.println("My Button list here is " + manager.getButtonList());
	}

	private List<Elementable> generateSprites(){
		List<Elementable> elements = new ArrayList<Elementable>();
		for (int i=1;i<2;i++){
			Map<String,VoogaData> properties = new HashMap<String,VoogaData>();
			properties.put("health", new VoogaNumber(10d));
			Sprite sprite = new Sprite("/mario.png","Mario",properties,new VoogaNumber(8.0));
			sprite.setPosition(new Position(0,0));
			elements.add(sprite);
			sprite.addProperty("gravity", new VoogaNumber(.04));
			sprite.isMainCharacter();
		}
		for (int i=1;i<2;i++){
			Map<String,VoogaData> properties = new HashMap<String,VoogaData>();
			properties.put("softness", new VoogaNumber(10d));
			Sprite sprite = new Sprite("/Grass.jpg","Grass Floor",properties,new VoogaNumber(1000000000000.0));
			sprite.setPosition(new Position(i*20,i*20));
			elements.add(sprite);
			sprite.addProperty("gravity", new VoogaNumber(0.0));
			sprite.isMainCharacter();
		}
		return elements;
	}

	private List<VoogaEvent> generateEvents(){
		List<VoogaEvent> elements = new ArrayList<VoogaEvent>();
		for (int i =0;i<10;i++){
			VoogaEvent testEvent = new VoogaEvent();
//			testEvent.addCause(new KeyCause("k", testEvent));
//			testEvent.addEffect(new VariableEffect("LevelIndex", "set", "Level2", testEvent));
//			elements.add(testEvent);
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

	@Test
	public void testWriting () throws ParserConfigurationException, TransformerException, IOException, SAXException {
		FileWriterFromGameObjects.saveGameObjects(manager, "games/levels/DataWritingTest.xml");
	}

	public DataContainerOfLists getData() {
		return manager;
	}

}
