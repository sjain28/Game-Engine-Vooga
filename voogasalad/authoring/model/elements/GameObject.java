package authoring.model.elements;

import GameEngine.Sprite;
import authoring.interfaces.IDable;
import javafx.scene.image.Image;
import tools.interfaces.VoogaData;

public class GameObject implements IDable{

    private Sprite sprite;
    private static int NUMBER_OF_INSTANCES=0;
    
    GameObject(){
        
        NUMBER_OF_INSTANCES++;
    }
    
    @Override
    public String getID(){
        return sprite.getID();
    }
    
    public void addProperty(String name, VoogaData data){
        sprite.addProperty(name, data);
    }
    
    
}
