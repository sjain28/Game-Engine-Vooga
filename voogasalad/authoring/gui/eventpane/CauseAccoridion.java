package authoring.gui.eventpane;

import java.util.ArrayList;
import java.util.List;
import events.VoogaEvent;
import javafx.scene.control.Accordion;
import javafx.scene.control.Tab;
import javafx.scene.control.TitledPane;


public class CauseAccoridion extends Tab {
    private VoogaEvent event;
    private Accordion accordion;
    private List<TitledPane> tiles;

    public CauseAccoridion () {
        accordion = new Accordion();
        List<TitledPane> tiles = new ArrayList<TitledPane>();
        generateTiles(3);
        this.setContent(accordion);
        this.setClosable(false);
    }

    private void generateTiles (int count) {
        for (int i = 0; i < count; i++) {
            tiles.add(createTile("Cause " + (tiles.size() + 1)));
        }
    }

    private TitledPane createTile (String name) {
        TitledPane tile = new CauseTitledPane();
        tile.setText(name);
        
        return tile;
    }
}
