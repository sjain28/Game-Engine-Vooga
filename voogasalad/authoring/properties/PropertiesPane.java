package authoring.properties;

/**
 * Properties window to see all the current characteristics of a Sprite
 * 
 * @author Harry Guo, Arjun Desai, Aditya Srinivasan, Nick Lockett
 * 
 */

import java.util.ArrayList;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import authoring.interfaces.Elementable;
import authoring.interfaces.gui.Windowable;
import authoring.interfaces.model.CompleteAuthoringModelable;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import tools.interfaces.VoogaData;


public class PropertiesPane extends TabPane implements Windowable, Observer {

    private PropertiesTabManager ptm;

    /**
     * Constructor to instantiate the properties Pane
     */
    public PropertiesPane () {
        ptm = new PropertiesTabManager();
        populateTabPane();
    }

    /**
     * Populates the tabs of the current tabPane with tabs
     * 
     * @param tab
     */
    private void populateTabPane () {
        ArrayList<Tab> tabList = ptm.getTabList();
        tabList.stream().forEach(tab -> addTab(tab));
    }

    /**
     * Adds a new tab to properties pane.
     * @param tab
     */
    private void addTab (Tab tab) {
        this.getTabs().add(tab);
    }
    
    /**
     * Properties Pane getter
     * @return
     */
    public PropertiesTabManager getPropertiesTabManager () {
        return ptm;
    }

    /**
     * Properties Pane setter
     * @param ptm
     */
    public void setPropertiesTabManger (PropertiesTabManager ptm) {
        this.ptm = ptm;
        populateTabPane();
    }

    /**
     * Node in which to return the pane to display on UI grid.
     */
    @Override
    public Node getWindow () {
        return this;
    }

    /**
     * Observer interface method to keep track of global variables.
     */
    @Override
    public void update (Observable o, Object arg) {
        if ((o instanceof CompleteAuthoringModelable) && (arg instanceof Elementable)) {
            Elementable global = (Elementable) arg;
            ptm.getGlobalPropertiesTab().getPropertiesMap(global);
        }
    }

}
