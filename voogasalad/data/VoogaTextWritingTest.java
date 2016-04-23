package data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import authoring.interfaces.Elementable;
import events.KeyCause;
import events.VariableEffect;
import events.VoogaEvent;
import gameengine.Sprite;
import gameengine.SpriteFactory;
import javafx.application.Application;
import javafx.stage.Stage;
import tools.Position;
import tools.VoogaNumber;
import tools.interfaces.VoogaData;

public class VoogaTextWritingTest extends Application {

	private static final String TEST_STRING= "levels/VoogaTextWriting.xml";
	
    @Override
    public void start (Stage primaryStage) throws Exception {
        DataContainerOfLists manager = new DataContainerOfLists(generateSprites(),
        			generateGlobalVariables(),generateEvents(), null);
        

        Serializer.serialize(manager, "levels/Test.xml");
        Object o = Deserializer.deserialize(1, "levels/Test.xml").get(0);
        DataContainerOfLists vt2 = (DataContainerOfLists) o;
//        System.out.println("Unserialized");
//        System.out.println(vt2);
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
        
//        for (int i =0;i<10;i++){
//            VoogaText vt = new VoogaText();
//            vt.setText("DASFASAA");
//            vt.setId("ADSFASDFASF");
//            elements.add(vt);
//        }
//        
        return elements;
    }
    
    private List<VoogaEvent> generateEvents(){
        List<VoogaEvent> elements = new ArrayList<VoogaEvent>();
        for (int i =0;i<10;i++){
                VoogaEvent testEvent = new VoogaEvent();
                testEvent.addCause(new KeyCause("k", testEvent));
//                testEvent.addEffect(new VariableEffect("LevelIndex", "set", "Level2", testEvent));
            elements.add(testEvent);
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
    
    public static void main (String[] args){
        launch(args);
    }

}
