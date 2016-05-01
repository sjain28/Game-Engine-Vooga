package authoring.model;

import java.util.HashMap;
import java.util.Map;


import authoring.interfaces.Elementable;
import javafx.scene.Node;
import resources.VoogaBundles;
import tools.VoogaBoolean;
import tools.VoogaException;
import tools.VoogaNumber;
import tools.VoogaString;
import tools.interfaces.VoogaData;


public class GlobalPropertiesManager implements Elementable {

    private Map<String, VoogaData> globalPropertiesMap;
    private String myName;

    /**
     * Controlls access to and stores all global properties
     */
    public GlobalPropertiesManager () {
        globalPropertiesMap = new HashMap<String, VoogaData>();
        initialize();
    }

    @Override
    public Map<String, VoogaData> getVoogaProperties () {
        return globalPropertiesMap;
    }

    @Override
    public void addProperty (String name, VoogaData data) {
        globalPropertiesMap.put(name, data);
    }

    @Override
    public void removeProperty (String name) {
        globalPropertiesMap.remove(name);
    }

    @Override
    public String getName () {
        return null;
    }

    @Override
    public String getId () {
        return null;
    }

    @Override
    public void setVoogaProperties (Map<String, VoogaData> newVoogaProperties) {
        globalPropertiesMap.putAll(newVoogaProperties);
    }

    @Override
    public void setName (String name) {
        this.myName = name;
    }

    /**
     * Stores all key values in properties
     */
    public void initialize () {
        addProperty(VoogaBundles.defaultglobalvars.getProperty("Time"), new VoogaNumber(0.0));
        addProperty(VoogaBundles.defaultglobalvars.getProperty("Score"), new VoogaNumber(0.0));
        addProperty(VoogaBundles.defaultglobalvars.getProperty("SaveProgress"),
                    new VoogaBoolean(false));
        addProperty(VoogaBundles.defaultglobalvars.getProperty("CenteredCharacter"),
                    new VoogaString(""));
    }

    @Override
    public Node getNodeObject () {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void init () throws VoogaException {
        // TODO Auto-generated method stub

    }

    @Override
    public void update () {
        // TODO Auto-generated method stub

    }

}
