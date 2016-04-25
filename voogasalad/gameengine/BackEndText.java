package gameengine;

import java.util.HashMap;
import java.util.Map;
import authoring.interfaces.Elementable;
import data.Displayable;
import javafx.scene.Node;
import javafx.scene.text.Text;
import tools.Acceleration;
import tools.Position;
import tools.Velocity;
import tools.VoogaException;
import tools.VoogaString;
import tools.bindings.TextProperties;
import tools.interfaces.VoogaData;


public class BackEndText implements Elementable {

    private Map<String, VoogaData> myProperties;
    private Map<String,VoogaData> initializationMap;
    
    private String myId;
    private Position myLoc;
    private String myName;
    
    private transient Text text;
    
    private VoogaData displayedData;
    private Map<String, Object> initializationProperties;
    
    public BackEndText (String id) {
        myProperties = new HashMap<String,VoogaData>();
        myId = id;

    }

    @Override
    public Node getNodeObject () {
        return text;
    }

    @Override
    public void update () {
        text.setText(myName);
        if (displayedData == null) return;
        text.setText(myName + " : "+displayedData.getValue());
    }

    @Override
    public Map<String, VoogaData> getVoogaProperties () {
        return null;
    }

    @Override
    public void addProperty (String name, VoogaData data) {
        // TODO Auto-generated method stub

    }

    @Override
    public void removeProperty (String name) {
        // TODO Auto-generated method stub

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
        // TODO Auto-generated method stub

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
