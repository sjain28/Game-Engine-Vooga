package authoring.gui.eventpane;

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


public class CauseAccoridion extends Tab {
    private VoogaEvent event;
    
    private List<TitledPane> tiles;
    private EditEventable manager;
    
    private BorderPane pane;
    private Accordion accordion;
    private HBox buttons;
    
    public CauseAccoridion (EditEventable manager) {
        
        this.manager=manager;
        pane = new BorderPane();
        accordion = new Accordion();

        initializeButtons();
        pane.setBottom(buttons);
        pane.setCenter(accordion);
        
        tiles = new ArrayList<TitledPane>();
        generateTiles(1);
        
        this.setText("Cause");
        this.setContent(pane);
        this.setClosable(false);
    }

    private void generateTiles (int count) {
        for (int i = 0; i < count; i++) {
            tiles.add(createTile("Cause " + (tiles.size() + 1)));
        }
    }
    
    private TitledPane createTile (String name) {
        TitledPane tile = new CauseTitledPane(manager);
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
    
    private void initializeButtons(){
        buttons = new HBox();
        buttons.setPadding(new Insets(10,10,10,10));
        buttons.getChildren().add(buttonFactory("Add Cause",e->generateTiles(1)));
    }
    
    private Button buttonFactory(String name,EventHandler e){
        Button button = new Button(name);
        button.setOnAction(e);
        button.setAlignment(Pos.CENTER);
        return button;
    }
}
