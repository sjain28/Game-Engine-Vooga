package authoring;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import com.sun.glass.events.MouseEvent;
import authoring.gui.menubar.MenuPanel;
import authoring.gui.menubar.MenuPanelHandlingMirror;
import authoring.gui.toolbar.ToolPanel;
import authoring.gui.toolbar.ToolPanelHandlingMirror;
import authoring.interfaces.model.CompleteAuthoringModelable;
import authoring.interfaces.model.Sceneable;
import authoring.model.ElementManager;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.InputEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.VBox;
import player.gamedisplay.Menuable;
import tools.VoogaAlert;
import tools.VoogaException;
import resources.VoogaBundles;

/**
 * The UIManager is responsible for assembling view components, such as the
 * menubar, toolbar, and grid of windows
 * 
 */
// Temporarily extending GridPane, eventually will use Mosaic to display
// components
public class UIManager extends VBox implements Menuable{
	private UIGridHousing grid;
	private List<CompleteAuthoringModelable> myModels;

	/**
	 * Initializes the UI Manager
	 * 
	 * @param model Interface to mediate interactions with backend
	 */
	public UIManager(CompleteAuthoringModelable model) {
	        myModels = new ArrayList<CompleteAuthoringModelable>();
		myModels.add(model);
		initializeComponents();
	}

	/**
	 * Initializes all the pieces of the authoring environment
	 */
	private void initializeComponents() {
		this.getChildren().addAll(new MenuPanel(this, e -> {
			new MenuPanelHandlingMirror(e, this);
		}, VoogaBundles.menubarProperties), new ToolPanel(e -> {
			new ToolPanelHandlingMirror(e, this);
		}), grid = new UIGridHousing(myModels.get(0)));
	}
	
	public void addScene(){
	    myModels.add(new ElementManager());
	    //initializes a new scene using the most recently added model;
	    grid.addScene(myModels.get(myModels.size() - 1));
	}
	
	public CompleteAuthoringModelable getManager(){
	    return grid.getManager();
	}
	
	
	//TODO: Format output correctly
	public void saveAll(){
	    for(CompleteAuthoringModelable m: myModels){
                try {
                    m.onSave();
                }
                catch (VoogaException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
	    }
	}
	
}
