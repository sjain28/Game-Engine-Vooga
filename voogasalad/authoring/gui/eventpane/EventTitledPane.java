package authoring.gui.eventpane;

import java.lang.reflect.InvocationTargetException;
import java.util.ResourceBundle;

import authoring.interfaces.model.EditEventable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.BorderPane;
import tools.VoogaException;


public class EventTitledPane extends TitledPane {
    private BorderPane pane;
    private EditEventable manager;
    private ComboBox<String> options;
    private EventGUI eg;
    private ResourceBundle bundle;
    
    public EventTitledPane (EditEventable manager,ResourceBundle bundle) {
        this.manager = manager;
        this.bundle=bundle;
        initialize();
    }

    protected void initialize () {
        options = new ComboBox<>();
        options.getItems().addAll(bundle.keySet().toArray(new String[bundle.keySet().size()]));
        options.setOnAction(e -> constructGUI("authoring.gui.eventpane." + bundle.getString(options.getValue()) +
                                              "GUI"));
        pane = new BorderPane();
        pane.setTop(options);
        this.setContent(pane);
    }

    private void constructGUI (String className) {
        if (className == null){
            return;
        }
        
        Class<?> c = null;
        try {
            c = Class.forName(className);
            Object o = c.getConstructor(EditEventable.class).newInstance(manager);
            eg = (EventGUI) o;
            Node display = eg.display();
            pane.setCenter(display);
        }
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException | 
                IllegalArgumentException | InvocationTargetException | 
                NoSuchMethodException | SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    protected void addOptions (String ... optionNames) {
        options.getItems().addAll(optionNames);
    }

    public String getDetails () throws VoogaException {
        return eg.getDetails();
    }

}
