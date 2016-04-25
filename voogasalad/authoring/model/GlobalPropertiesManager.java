package authoring.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import authoring.interfaces.Elementable;
import javafx.beans.property.SimpleMapProperty;
import javafx.scene.Node;
import tools.VoogaBoolean;
import tools.VoogaException;
import tools.interfaces.VoogaData;


public class GlobalPropertiesManager implements Elementable {

    private Map<String, VoogaData> globalPropertiesMap;
    private String myName;

    public GlobalPropertiesManager () {
        globalPropertiesMap = new HashMap<String, VoogaData>();
    }

    @Override
    public void update () {
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
    public Node getNodeObject () {
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

    @Override
    public void init () throws VoogaException {
        // TODO Auto-generated method stub

    }

}
