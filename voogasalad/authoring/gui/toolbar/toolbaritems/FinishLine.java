package authoring.gui.toolbar.toolbaritems;

import authoring.gui.levelpreferences.FinishLinePrompt;
import authoring.gui.toolbar.ToolbarItemHandler;
import player.gamedisplay.Menuable;

public class FinishLine extends ToolbarItemHandler {
    
    public FinishLine(Menuable model){
    	
    }
    
    @Override
    public void handle () {
    	new FinishLinePrompt().show();
    }

}
