package authoring.model;

import java.util.HashMap;
import java.util.Map;

import authoring.interfaces.Elementable;
import javafx.scene.Node;
import tools.VoogaNumber;
import tools.VoogaString;
import tools.interfaces.VoogaData;


public class GlobalPropertiesManager implements Elementable {

    private Map<String, VoogaData> globalPropertiesMap;
    private String myName;
    
    public GlobalPropertiesManager () {
        globalPropertiesMap = new HashMap<String, VoogaData>();
        
        //add in default global properties
        addProperty("LevelIndex", new VoogaString(""));
        addProperty("Score", new VoogaNumber(0.0));
        addProperty("MainCharacterID", new VoogaNumber(0.0));
        addProperty("Clock", new VoogaNumber(0.0));
    }

    @Override
    public void update () {}

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
        return myName;
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


}