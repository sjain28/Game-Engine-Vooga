package data;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import events.AnimationEvent;
import tools.VoogaAlert;
import tools.VoogaException;

/* This class is a custom tool that utilizes the Deserializer to allow a user to obtain game-specific objects 
 * an XML file. Because the game objects are currently serialized in a DataContainer, the deserialize method 
 * will return all the data of different game objects in a custom DataContainerOfLists class. 
 */


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
        try{
        data = (DataContainerOfLists) Deserializer.deserialize(1, fileName).get(0);
        }
        catch (RuntimeException e) {
            e.printStackTrace();
        }
        catch (VoogaException e) {
            new VoogaAlert(e.getMessage());
        }
    }

    public DataContainerOfLists getDataContainer () {
        return data;
    }
}
