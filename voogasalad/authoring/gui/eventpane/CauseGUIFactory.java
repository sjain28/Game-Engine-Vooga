package authoring.gui.eventpane;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import authoring.model.ElementManager;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;


public class CauseGUIFactory {
    private static final String causeTypesPath = "/events/CauseTypes.properties";
    private ResourceBundle eventsToGUIBundle;
    private ElementManager myElementManager;

    public CauseGUIFactory (ElementManager elementManager) {
        myElementManager = elementManager;
        eventsToGUIBundle = ResourceBundle.getBundle(causeTypesPath);
    }

    public Node generateGUI (String name) {
        Map<String, Node> nodeMap = new HashMap<String, Node>();

        Class<?> c = null;
        try {
            c = Class.forName(name);
        }
        catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Field[] allFields = c.getDeclaredFields();

        for (String key : eventsToGUIBundle.keySet()) {
            for (Field f : allFields) {
                if (f.getName().toLowerCase().contains(key)) {
                    Node node = loadData(key,generateNode(key));
                }
            }
        }

        return null;

    }

    public Node generateNode (String key) {
        String guiElement = eventsToGUIBundle.getString(key);
        Class<?> c = null;
        try {
            c = Class.forName(key);
        }
        catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            Node node = (Node) c.newInstance();
            return node;
        }
        catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }

    public Node loadData (String key, Node node) {
        if (node == null) return null;
        String lowerCaseKey = key.toLowerCase();

        if (lowerCaseKey.toLowerCase().contains("group")) {
            ComboBox<String> box = (ComboBox) node;
            box.getItems().addAll(myElementManager.getSpriteFactory().getAllArchetypeNames());
            box.getItems().addAll(myElementManager.getMySpriteNames());
            return box;
        }
        if (lowerCaseKey.contains("archetype")) {
            ComboBox<String> box = (ComboBox) node;
            box.getItems().addAll(myElementManager.getSpriteFactory().getAllArchetypeNames());
            return box;
        }
        if (lowerCaseKey.contains("sprite")) {
            ComboBox<String> box = (ComboBox) node;
            box.getItems().addAll(myElementManager.getMySpriteNames());
            return box;
        }
        if (lowerCaseKey.contains("physics")){
            
        }
        
       return node;
    }
}
