package authoring.gui.eventpane;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import authoring.interfaces.model.EditEventable;
import events.VoogaEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TitledPane;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import resources.VoogaBundles;

public class EventAccoridion extends Tab {
    private List<TitledPane> tiles;
    private EditEventable manager;
    
    private BorderPane pane;
    private Accordion accordion;
    private HBox buttons;
    
    private String name="";
    
    public EventAccoridion (EditEventable manager,String name,Button... addedButtons) {
        this.name=name;
        this.manager=manager;
        pane = new BorderPane();
        accordion = new Accordion();

        initializeButtons(addedButtons);
        pane.setBottom(buttons);
        pane.setCenter(accordion);
        
        tiles = new ArrayList<TitledPane>();
        generateTiles(1);
        
        this.setText(name);
        this.setContent(pane);
        this.setClosable(false);
    }

    private void generateTiles (int count) {
        for (int i = 0; i < count; i++) {
            tiles.add(populateTiles(name+" " + (tiles.size() + 1)));
        }
    }
    
    private TitledPane populateTiles (String name) {
        TitledPane tile = createTile();
        tile.setText(name);
        tile.setOnMouseClicked((MouseEvent e)->{
            if (e.getButton() == MouseButton.SECONDARY){
                tiles.remove(tile);
                accordion.getPanes().remove(tile);
            }
        });
        accordion.getPanes().add(tile);
        return tile;
    }
    
    private TitledPane createTile(){
        if (name == null) return null;
        String className = VoogaBundles.backendToGUIProperties.getString(name);
        System.out.println(className);
        Class<?> c = null;
        try {
            c = Class.forName(className);
        }
        catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            TitledPane titledPane;
            try {
                Object o = c.getConstructor(EditEventable.class).newInstance(manager);
                titledPane = (TitledPane) o;
                System.out.println("TitledPane created in EventAccoridion.java");
                return titledPane;
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
        return null;
    }
    
    private void initializeButtons(Button... addedButtons){
        buttons = new HBox();
        buttons.setPadding(new Insets(10,10,10,10));
        buttons.getChildren().addAll(buttonFactory("Add "+name,e->generateTiles(1)));
        buttons.getChildren().addAll(addedButtons);
    }
    
    private Button buttonFactory(String name,EventHandler e){
        Button button = new Button(name);
        button.setOnAction(e);
        button.setAlignment(Pos.CENTER);
        return button;
    }
}

