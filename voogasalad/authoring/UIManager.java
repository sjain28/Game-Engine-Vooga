package authoring;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import com.sun.glass.events.MouseEvent;
import authoring.gui.menubar.MenuPanel;
import authoring.gui.menubar.MenuPanelHandlingMirror;
import authoring.gui.toolbar.ToolPanel;
import authoring.gui.toolbar.ToolPanelHandlingMirror;
import authoring.interfaces.model.CompleteAuthoringModelable;
import authoring.interfaces.model.Sceneable;
import authoring.model.ElementManager;
import authoring.model.ElementTabManager;
import authoring.model.Preferences;
import data.Serializer;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
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
import resources.VoogaPaths;

/**
 * The UIManager is responsible for assembling view components, such as the
 * menubar, toolbar, and grid of windows
 * 
 */
public class UIManager extends VBox implements Menuable {

	private UIGridHousing grid;
	private SimpleIntegerProperty currentTabIndex;
	private ElementTabManager elementTabManager;

	/**
	 * Initializes the UI Manager
	 * 
	 * @param model
	 *            Interface to mediate interactions with backend
	 */
	
	public UIManager(List<CompleteAuthoringModelable> models) {
		this.currentTabIndex = new SimpleIntegerProperty(-1);
		this.elementTabManager = new ElementTabManager();
		ElementManager temp = new ElementManager();
		this.elementTabManager.addManager(temp);
		Bindings.bindBidirectional(this.currentTabIndex, elementTabManager.getCurrentManagerIndexProperty());
		initializeComponents();
		for(int i = 0; i < models.size(); i++) {
			openScene((ElementManager) models.get(i));
		}
		this.elementTabManager.removeManager(temp);
		grid.removeFirstTab();
		System.out.println(elementTabManager.getAllManagers().size());
	}
	
	public UIManager(CompleteAuthoringModelable model) {
		this.currentTabIndex = new SimpleIntegerProperty(-1);
		this.elementTabManager = new ElementTabManager();
		this.elementTabManager.addManager((ElementManager) model);
		Bindings.bindBidirectional(this.currentTabIndex, elementTabManager.getCurrentManagerIndexProperty());

		initializeComponents();
	}

	/**
	 * Initializes all the pieces of the authoring environment
	 */
	void initializeComponents() {
		this.getChildren().addAll(new MenuPanel(this, e -> {
			new MenuPanelHandlingMirror(e, this);
		}, VoogaBundles.menubarProperties), new ToolPanel(e -> {
			new ToolPanelHandlingMirror(e, this);
		}), grid = new UIGridHousing(elementTabManager.getCurrentManager()));

		grid.getSelectionModel().selectedIndexProperty().addListener((obs, old, n) -> {
			this.currentTabIndex.set((int) n);
		});
	}

	public void addScene() {
		addScene(new ElementManager());
	}

	public void addScene(CompleteAuthoringModelable manager) {
		elementTabManager.addManager((ElementManager) manager);
		grid.addScene(elementTabManager.getCurrentManager());
	}

	public CompleteAuthoringModelable getManager() {
		return elementTabManager.getCurrentManager();
	}

	public List<String> getAllManagerNames() {
		List<String> names = new ArrayList<String>();
		elementTabManager.getAllManagers().stream().map(ElementManager::getName).forEach(names::add);
		return names;
	}

	// TODO: Format output correctly
	public void saveAll() {
		try {
			Preferences preferences = new Preferences(VoogaBundles.preferences.getProperty("GameName"),
													  VoogaBundles.preferences.getProperty("GameDescription"),
													  VoogaBundles.preferences.getProperty("GameWidth"),
													  VoogaBundles.preferences.getProperty("GameHeight"),
													  getAllManagerNames());
			Serializer.serializeLevel(preferences, VoogaPaths.GAME_FOLDER + VoogaBundles.preferences.getProperty("GameName") + "/" + VoogaBundles.preferences.getProperty("GameName") + ".xml");
			for (CompleteAuthoringModelable m : elementTabManager.getAllManagers()) {
				m.onSave();
			}} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void openScene(ElementManager manager) {
		elementTabManager.addManager(manager);
		grid.openScene(manager);
	}

}
