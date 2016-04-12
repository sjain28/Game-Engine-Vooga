package data;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import authoring.interfaces.Elementable;
import events.VoogaEvent;
import gameengine.SpriteFactory;
import tools.interfaces.VoogaData;


public class FileReaderToGameObjects {
	
    private DataContainerOfLists data;
    
    private List<String> objectNames;

    public FileReaderToGameObjects (String fileName) {
        // TODO Auto-generated constructor stub
        loadGameObjects(fileName);
    }

    private void loadGameObjects (String fileName) {
        objectNames = new ArrayList<String>();
        objectNames.add("");

        data = (DataContainerOfLists) DeSerializer.deserialize(1, fileName).get(0);
    }

    public List<Elementable> createNodeList () {
        return data.getElementableList();
    }

    public List<VoogaEvent> createEventList () {
        return data.getEventList();
    }

    public Map<String, VoogaData> createVariableMap () {
        return data.getVariableMap();
    }
    
    public SpriteFactory createSpriteFactory () {
        return data.getSpriteFactory();
    }

    public DataContainerOfLists getDataContainer () {
        return data;
    }

}
