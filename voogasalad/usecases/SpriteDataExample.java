package usecases;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.*;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import gameengine.Sprite;

public class SpriteDataExample {
    public static void main (String[] args){
        Sprite a = new Sprite("bricks.jpg","DA",null);
        Sprite b = new Sprite("bricks.jpg","eA",null);
        
        List<Sprite> sprites = new ArrayList<Sprite>();
        sprites.add(a);
        sprites.add(b);
        
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
