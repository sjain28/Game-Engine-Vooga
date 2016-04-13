package authoring.model;

import java.util.Observable;
import authoring.interfaces.Elementable;

public class SelectionModel extends Observable{
    private static SelectionModel selectionModel;
    private Elementable elementable;
    
    private SelectionModel(){

    }
    
    public void setElementable(Elementable e){
        elementable = e;
        notifyObservers();
    }
    
    public Elementable getElementable(){
        return elementable;
    }
    
    public static SelectionModel getSelectionModel(){
        if (selectionModel==null){
            selectionModel = new SelectionModel();
        }
        
        return selectionModel;
    }
}
