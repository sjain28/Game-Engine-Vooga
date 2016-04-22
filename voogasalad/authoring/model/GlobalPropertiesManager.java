package authoring.model;

import java.util.HashMap;
import java.util.Map;
import authoring.interfaces.Elementable;
import javafx.beans.property.SimpleMapProperty;
import javafx.scene.Node;
import tools.VoogaBoolean;
import tools.interfaces.VoogaData;


public class GlobalPropertiesManager implements Elementable {

    private Map<String, VoogaData> globalPropertiesMap;

    public GlobalPropertiesManager () {
        globalPropertiesMap = new HashMap<String, VoogaData>();
    }

    @Override
    public void update () {
        // TODO Auto-generated method stub
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
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Node getNodeObject () {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getId () {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setVoogaProperties (Map<String, VoogaData> newVoogaProperties) {
        // TODO Auto-generated method stub

    }

    @Override
    public void setName (String name) {
        // TODO Auto-generated method stub

    }

}
