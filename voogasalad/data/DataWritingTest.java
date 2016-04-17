package data;

//import static org.junit.Assert.*;
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
import authoring.model.DumbVoogaText;
import authoring.model.ElementManager;
import authoring.model.VoogaButton;
import events.Cause;
import events.KeyCause;
import events.VariableEffect;
import events.VoogaEvent;
import gameengine.Sprite;
import gameengine.SpriteFactory;
import javafx.scene.control.Button;
import javafx.scene.shape.Rectangle;
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
				generateEvents(), new SpriteFactory());
		manager.addButton(generateButtons());
		System.out.println("My Button list here is " + manager.getButtonList());
	}

	private List<Elementable> generateSprites(){
		List<Elementable> elements = new ArrayList<Elementable>();
		for (int i=0;i<10;i++){
			Map<String,VoogaData> properties = new HashMap<String,VoogaData>();
			properties.put("health", new VoogaNumber(10d));
			Sprite sprite = new Sprite("/image.jpeg","healthy",properties,new VoogaNumber(8d));
			sprite.setPosition(new Position(i*7,i*2));
			elements.add(sprite);
		}
		DumbVoogaText vt = new DumbVoogaText();
		vt.setText("DASFASFAF");
		VoogaButton vb = new VoogaButton();
		elements.add(vt);
		elements.add(vb);
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

	private List<Button> generateButtons(){
		List<Button> elements = new ArrayList<Button>();
		for (int i =0;i<10;i++){
			Button Button = new Button("hi");
			elements.add(Button);
		}
		return elements;
	}

	private Map<String,VoogaData> generateGlobalVariables(){
		Map<String,VoogaData> map = new HashMap<String,VoogaData>();
		for (int i=0;i<10;i++){
			map.put(""+i, new VoogaNumber((double) i));
		}
		return map;
	}

	private SpriteFactory generateSpriteFactory(){
		SpriteFactory sf = new SpriteFactory();
		return sf;
	}

	//    @Test
	//    public void testWriting () {
	//        try {
	//            FileWriterFromGameObjects.saveGameObjects(manager, "/levels/TestWriting.xml");
	//        }
	//        catch (ParserConfigurationException | TransformerException | IOException | SAXException e) {
	//            // TODO Auto-generated catch block
	//            e.printStackTrace();
	//        }
	//        
	//    }

	public DataContainerOfLists getData() {
		return manager;
	}

}
