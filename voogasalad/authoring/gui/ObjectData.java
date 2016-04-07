package authoring.gui;

import javafx.scene.image.Image;

public class ObjectData {
    private String myArchetype = "Default";
    private double myX = 0;
    private double myY = 0;
    private String myImagePath;
    
    public ObjectData(){
        myArchetype = "Default";
        myX = 0;
        myY = 0;
    }
    
    public String getArchetype(){
        return myArchetype;
    }
    
    public void setArchetype(String archetype){
        myArchetype = archetype;
    }
    
    public double getX(){
        return myX;
    }
    
    public void setX(double x){
        myX = x;
    }
    
    public double getY(){
        return myY;
    }
    
    public void setY(double y){
        myY = y;
    }
    
    public String getImagePath(){
        return myImagePath;
    }
    
    public void setImagePath(String imagepath){
        myImagePath = imagepath;
    }
    
}