package authoring.gui.toolbar.toolbaritems;

import authoring.UIManager;
import authoring.gui.cartography.LevelCartographer;
import authoring.gui.toolbar.ToolbarItemHandler;
import player.gamedisplay.Menuable;


public class Map extends ToolbarItemHandler {

    private UIManager model;

    public Map (Menuable model) {
        this.model = (UIManager) model;
    }
    
    /**
     * Defines what to do when the button is clicked
     */
    @Override
    public void handle () {
        LevelCartographer mapper = new LevelCartographer(model);
        mapper.show();
    }

}
