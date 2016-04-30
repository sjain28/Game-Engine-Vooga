package authoring.properties;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import authoring.interfaces.Elementable;
import authoring.interfaces.model.CompleteAuthoringModelable;
import authoring.model.ElementSelectionModel;
import javafx.scene.control.Tab;
import resources.VoogaBundles;


/**
 * Tab Manager class to handle all the tabs in the properties TabPane
 * 
 * @author Harry Guo, Arjun Desai, Aditya Srinivasan, Nick Lockett
 */

public class PropertiesTabManager implements Observer {

    private ArrayList<Tab> myPropertyTabs = new ArrayList<Tab>();
    private GenericPropertiesTab SPT;
    private GenericPropertiesTab GPT;
    private ElementSelectionModel selector;

    private ResourceBundle ppProperties;

    /**
     * Constructor to instantiate the Singleton class Element Selection Model
     * for Sprite properties tab
     */
    public PropertiesTabManager (CompleteAuthoringModelable manager) {
        ppProperties = VoogaBundles.propertiesPaneProperties;
        SPT = new GenericPropertiesTab(ppProperties.getString("SpritePropertiesTab"), manager);
        GPT = new GenericPropertiesTab(ppProperties.getString("GlobalVariablesTab"), manager);
        selector = ElementSelectionModel.getInstance();
        selector.addObserver(this);
        populateTabList();
    }

    /**
     * Returns list of tabs managed by the properties tab manager.
     * 
     * @return
     */
    public ArrayList<Tab> getTabList () {
        return myPropertyTabs;
    }

    /**
     * Populates the tab list.
     */
    private void populateTabList () {

        myPropertyTabs.add(SPT);
        myPropertyTabs.add(GPT);
    }

    /**
     * Returns the Sprite Properties Tab.
     * 
     * @return
     */
    public GenericPropertiesTab getSpritePropertiesTab () {
        return SPT;
    }

    /**
     * Returns the Global Properties Tab.
     * 
     * @return
     */
    public GenericPropertiesTab getGlobalPropertiesTab () {
        return GPT;
    }

    /**
     * Updates the sprite properties tab based on observing elementals in the
     * Design Board.
     */
    @Override
    public void update (Observable o, Object arg) {

        if (arg instanceof Elementable) {
            Elementable tabInfo = (Elementable) arg;
            if ((o instanceof CompleteAuthoringModelable)) {
                GPT.getPropertiesMap(tabInfo);
            }
            else {
                SPT.getPropertiesMap(tabInfo);
            }
        }
    }

}
