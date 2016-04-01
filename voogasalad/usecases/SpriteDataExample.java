package usecases;

import GameEngine.Sprite;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.*;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class SpriteDataExample {
    public static void main (String[] args){
        Sprite a = new Sprite("C://Documents/workspace/voogasalad_Doovalsalad/design_resources/IMG_2097.JPG","DA");
        Sprite b = new Sprite("/design_resources/IMG_2097.JPG","DA");
        List<Sprite> sprites = new ArrayList<Sprite>();
        
        XStream mySerializer = new XStream(new DomDriver());
        try {
            mySerializer.toXML(sprites,new FileOutputStream(new File("game_data/ExampleData.xml")));
        }
        catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        
    }
}
