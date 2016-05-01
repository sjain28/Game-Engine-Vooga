package gameengine;

import java.util.HashMap;
import java.util.Map;

import authoring.interfaces.Elementable;
import javafx.scene.Node;
import javafx.scene.text.Text;
import resources.VoogaBundles;
import tools.VoogaBoolean;
import tools.VoogaException;
import tools.bindings.TextProperties;
import tools.interfaces.VoogaData;


public class BackEndText implements Elementable {

    private Map<String, VoogaData> myProperties;
    private Map<String, Object> initializationProperties;
    
    private String myId;
    private String myName;
    
    private transient Text text;
    
    private VoogaData displayedData;
    
    
    public BackEndText (String id) {
        myProperties = new HashMap<>();
        myProperties.put(VoogaBundles.textMapProperties.getString("STATIC"), new VoogaBoolean(false));
        myId = id;

    }

    @Override
    public Node getNodeObject () {
        return text;
    }

    @Override
    public void update () {
        text.setText(myName);
        if (displayedData == null) {
        	return;
        }
        text.setText(myName+" "+displayedData.getValue());
    }

    @Override
    public Map<String, VoogaData> getVoogaProperties () {
        return myProperties;
    }

    @Override
    public void addProperty (String name, VoogaData data) {
        myProperties.put(name, data);
    }

    @Override
    public void removeProperty (String name) {
        if (!myProperties.keySet().contains(name)) {
        	return;
        }
        myProperties.remove(name);
    }

    @Override
    public String getName () {
        return myName;
    }

    @Override
    public String getId () {
        return myId;
    }

    @Override
    public void setVoogaProperties (Map<String, VoogaData> newVoogaProperties) {
        this.myProperties = newVoogaProperties;
    }

    @Override
    public void setName (String name) {
        myName = name;
    }
    
    public void setInitializationMap (Map<String, Object> ip) {
        initializationProperties = ip;
    }
    
    @Override
    public void init () throws VoogaException {
        text = new Text();
        TextProperties tp = new TextProperties();
        tp.loadData(text, initializationProperties);
    }
    
    public void setDisplayedData(VoogaData data){
        displayedData = data;
    }
    
    public VoogaData getDisplayedData(){
        return displayedData;
    }
    

}
