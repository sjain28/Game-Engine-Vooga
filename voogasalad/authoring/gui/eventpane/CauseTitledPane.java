package authoring.gui.eventpane;

import javafx.scene.control.ComboBox;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.StackPane;

public class CauseTitledPane extends TitledPane{
    private ComboBox<String> causeOptions;
    private StackPane pane;
    public CauseTitledPane(){
        initialize();
    }
    
    private void initialize(){
        pane = new StackPane();
        causeOptions = new ComboBox<String>();
        causeOptions.getItems().addAll("VariableCause");
        pane.getChildren().add(causeOptions);
        causeOptions.setOnAction(e->constructGUI(causeOptions.getValue()+"GUI"));
        this.setContent(pane);
    }
    
    private void constructGUI(String className){
        if (className == null) return;
        Class<?> c = null;
        try {
            c = Class.forName(className);
        }
        catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            EventGUI eg = (EventGUI) c.newInstance();
            pane.getChildren().add(eg.display());
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
}
