package authoring.gui.eventpane;

import java.lang.reflect.InvocationTargetException;

import authoring.CustomText;
import authoring.interfaces.model.EditEventable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import tools.VoogaException;


public class EventTitledPane extends TitledPane {
    private BorderPane pane;
    private EditEventable manager;
    private ComboBox<String> options;
    private EventGUI eg;

    public EventTitledPane (EditEventable manager) {
        this.manager = manager;
        initialize();
    }

    protected void initialize () {
        options = new ComboBox<String>();
        options.setOnAction(e -> constructGUI("authoring.gui.eventpane." + options.getValue() +
                                              "GUI"));
        pane = new BorderPane();
        pane.setTop(options);
        this.setContent(pane);
    }

    private void constructGUI (String className) {
        if (className == null)
            return;
        Class<?> c = null;
        try {
            c = Class.forName(className);
        }
        catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {

            try {
                Object o = c.getConstructor(EditEventable.class).newInstance(manager);
                eg = (EventGUI) o;
                Node display = eg.display();
                pane.setCenter(display);
            }
            catch (IllegalArgumentException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            catch (InvocationTargetException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            catch (NoSuchMethodException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            catch (SecurityException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
        catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    protected void addOptions (String ... optionNames) {
        options.getItems().addAll(optionNames);
    }

    public String getDetails () throws VoogaException {
        System.out.println(eg.getClass());
        return eg.getDetails();
    }

}
