package authoring.model;

import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.SimpleIntegerProperty;


public class ElementTabManager {

    private List<ElementManager> myManagers;
    private SimpleIntegerProperty currentManagerIndex;

    private ElementManager myCurrentManager;

    /**
     * Constructor to create Element Tab Manager
     */
    public ElementTabManager () {
        this.myManagers = new ArrayList<ElementManager>();
        this.currentManagerIndex = new SimpleIntegerProperty(-1);
        this.currentManagerIndex.addListener( (obs, old, n) -> {
            myCurrentManager = myManagers.get((int) n);
        });
    }
    
    /**
     * Add manager to the element tab manager
     * @param manager: element manager to set to 
     */
    public void addManager (ElementManager manager) {
        this.myManagers.add(manager);
        this.currentManagerIndex.set(this.currentManagerIndex.get() + 1);
    }
    
    /**
     * Remove manager from this class
     * @param manager to remove
     */
    public void removeManager (ElementManager manager) {
        this.myManagers.remove(manager);
        this.currentManagerIndex.set(this.currentManagerIndex.get() - 1);
    }
    
    /**
     * 
     * @return current manager that the user is using for the specific tab
     */
    public ElementManager getCurrentManager () {
        return myCurrentManager;
    }
    
    /**
     * Reutnrs current manager index property
     * @return interger property for current manager
     */
    public SimpleIntegerProperty getCurrentManagerIndexProperty () {
        return this.currentManagerIndex;
    }
    
    /**
     * 
     * @return all managers
     */
    public List<ElementManager> getAllManagers () {
        return myManagers;
    }

}
