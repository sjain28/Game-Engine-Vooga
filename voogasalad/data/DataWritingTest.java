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
import authoring.model.ElementManager;
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
        manager = new DataContainerOfLists(generateSprites(),generateGlobalVariables(),generateEvents());
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
        return elements;
    }
    
    private List<VoogaEvent> generateEvents(){
        List<VoogaEvent> elements = new ArrayList<VoogaEvent>();
        for (int i =0;i<10;i++){
            elements.add(new VoogaEvent());
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
    
    @Test
    public void testWriting () {
        try {
            FileWriterFromGameObjects.saveGameObjects(manager, "TestWriting.xml");
        }
        catch (ParserConfigurationException | TransformerException | IOException | SAXException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    
    public DataContainerOfLists getData() {
        return manager;
    }

}
