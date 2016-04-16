package data;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import authoring.interfaces.Elementable;
import auxiliary.VoogaAlert;
import auxiliary.VoogaException;
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
        System.out.println("The  file checked here is" + fileName);
        try {
            data = (DataContainerOfLists) DeSerializer.deserialize(1, fileName).get(0);
        }
        catch (RuntimeException e) {
            System.out.println("error came from the filereadertogameobjects");
            e.printStackTrace();
        }
        catch (VoogaException e) {
            new VoogaAlert(e.getMessage());
        }
        System.out.println("What is the data here" + data);
        // DataContainerOfLists data2 = (DataContainerOfLists) UnSerializer.deserialize(2,
        // fileName).get(0);
        // System.out.println("The current object being loaded is from the second container: " +
        // data2.getVariableList());
        // System.out.println("The current list being loaded is from the second container: " +
        // data2.getEventList());
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
