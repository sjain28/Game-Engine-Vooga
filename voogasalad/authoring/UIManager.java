package authoring;

import java.util.ArrayList;
import java.util.List;

import authoring.gui.menubar.MenuPanel;
import authoring.gui.menubar.MenuPanelHandlingMirror;
import authoring.gui.toolbar.ToolPanel;
import authoring.gui.toolbar.ToolPanelHandlingMirror;
import authoring.interfaces.model.CompleteAuthoringModelable;
import authoring.model.ElementManager;
import authoring.model.ElementTabManager;
import authoring.model.Preferences;
import data.Serializer;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.layout.VBox;
import player.gamedisplay.Menuable;
import resources.VoogaBundles;
import resources.VoogaPaths;

/**
 * The UIManager is responsible for assembling view components, such as the
 * menubar, toolbar, and grid of windows
 * 
 * @author Aditya Srinivasan, Arjun Desai, Nick Lockett, Harry Guo
 * 
 */
public class UIManager extends VBox implements Menuable {

	private UIGridHousing grid;
	private SimpleIntegerProperty currentTabIndex;
	private ElementTabManager elementTabManager;
	private String gameName;

	/**
	 * Initializes the UI Manager given multiple models.
	 * 
	 * @param model
	 *            Interface to mediate interactions with back-end
	 */
	public UIManager(List<CompleteAuthoringModelable> models) {
		initializeTabs();
		ElementManager temp = new ElementManager();
		this.elementTabManager.addManager(temp);
		Bindings.bindBidirectional(this.currentTabIndex, elementTabManager.getCurrentManagerIndexProperty());
		initializeComponents();
		for(int i = 0; i < models.size(); i++) {
			openScene((ElementManager) models.get(i));
		}
		this.elementTabManager.removeManager(temp);
		grid.removeFirstTab();
	}
	/**
	 * Initializes the UI Manager given a singular model
	 * 
	 * @param model
	 *            Interface to mediate interactions with backend
	 */
	public UIManager(CompleteAuthoringModelable model) {
		initializeTabs();
		this.elementTabManager.addManager((ElementManager) model);
		Bindings.bindBidirectional(this.currentTabIndex, elementTabManager.getCurrentManagerIndexProperty());
		initializeComponents();
	}

	private void initializeTabs() {
		gameName = VoogaBundles.preferences.getProperty("GameName");
		this.currentTabIndex = new SimpleIntegerProperty(-1);
		this.elementTabManager = new ElementTabManager();
	}

	/**
	 * Initializes all the pieces of the authoring environment
	 */
	void initializeComponents() {
		grid = new UIGridHousing(elementTabManager.getCurrentManager());
		this.getChildren().addAll(new MenuPanel(this, e -> {
			new MenuPanelHandlingMirror(e, this);
		}, VoogaBundles.menubarProperties), new ToolPanel(e -> {
			new ToolPanelHandlingMirror(e, this);
		}), grid);

		grid.getSelectionModel().selectedIndexProperty().addListener((obs, old, n) -> {
			this.currentTabIndex.set((int) n);
		});
	}
	/**
	 * Adds a new scene.
	 */
	public void addScene() {
		addScene(new ElementManager());
	}
	/**
	 * Adds a new scene given a manager.
	 */
	public void addScene(CompleteAuthoringModelable manager) {
		elementTabManager.addManager((ElementManager) manager);
		grid.addScene(elementTabManager.getCurrentManager());
	}
	/**
	 * Gets the current authoring model.
	 */
	public CompleteAuthoringModelable getManager() {
		return elementTabManager.getCurrentManager();
	}
	/**
	 * @return the list of names of each tab manager
	 */
	public List<String> getAllManagerNames() {
		List<String> names = new ArrayList<>();
		elementTabManager.getAllManagers().stream()
		.map(ElementManager::getName)
		.forEach(names::add);
		return names;
	}

	/**
	 * Saves the current through serialization.
	 */
	public void saveAll () {
		try {
			Preferences preferences = new Preferences(gameName,
					VoogaBundles.preferences
					.getProperty("GameDescription"),
					VoogaBundles.preferences
					.getProperty("GameWidth"),
					VoogaBundles.preferences
					.getProperty("GameHeight"),
					getAllManagerNames());
			Serializer.serializeLevel(preferences,
					VoogaPaths.GAME_FOLDER + gameName + "/" + gameName + ".xml");
			for (CompleteAuthoringModelable m : elementTabManager.getAllManagers()) {
				m.onSave();
			}
		}
		catch (Exception e) {
		}
	}

	/**
	 * Opens scene dependent on current manager
	 * 
	 * @param manager: manager for selected scene
	 */
	public void openScene (ElementManager manager) {
		elementTabManager.addManager(manager);
		grid.openScene(manager);
	}

}
