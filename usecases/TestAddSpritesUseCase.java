package usecases;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import javafx.scene.Node;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class TestAddSpritesUseCase {
	
    public static void main (String[] args){
       
        Rectangle r1 = new Rectangle(3,5);
        Line l2 = new Line(3,5,6,9);
        Rectangle r2 = new Rectangle(7,19);
        Rectangle r3 = new Rectangle(3,9);
        List<Node> sprites = new ArrayList<Node>();
        
        sprites.add(r1);
        sprites.add(l2);
        sprites.add(r2);
        sprites.add(r3);
        		
        
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