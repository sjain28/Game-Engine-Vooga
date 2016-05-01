package authoring;

import java.util.ResourceBundle;

import authoring.gui.DesignBoardHousing;
import authoring.gui.EventsWindow;
import authoring.interfaces.model.CompleteAuthoringModelable;
import authoring.model.ElementManager;
import authoring.properties.PropertiesPane;
import javafx.beans.binding.Bindings;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Tab;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import resources.VoogaBundles;
import tools.VoogaAlert;
import tools.VoogaException;


/**
 * The UIGrid assembles the window components into a grid.
 * 
 * Specifically: The DesignBoardHousing, EventWindow, PropertiesPane, and Explorer
 * 
 * @author Aditya Srinivasan, Arjun Desai, Nick Lockett, Harry Guo
 *
 */

public class UIGrid extends GridPane{

	private PropertiesPane propertiesPane;
	private DesignBoardHousing designBoard;
	private Explorer explorer;
	private CompleteAuthoringModelable myManager;

	private ResourceBundle gameDisplayProperties;

	private transient SimpleStringProperty mySceneName;

	/**
	 * Initialized the UIGrid
	 * 
	 * @param elem: Interface to Manager for the backend
	 */
	@Deprecated
	public UIGrid (CompleteAuthoringModelable elem) {
		myManager = elem;
		gameDisplayProperties = VoogaBundles.GameDisplayProperties;
		this.mySceneName = new SimpleStringProperty();
		this.mySceneName.addListener((obs, old, n) -> {
			System.out.println(n);
		});
		sector();
		try {
			populate(false);
		}
		catch (VoogaException e) {
			VoogaAlert alert = new VoogaAlert(e.getMessage());
			alert.showAndWait();
		}

	}

	/**
	 * Initializes the UI grid.
	 * 
	 * @param elem: Interface to Manager for the backend
	 * @param container: tab that contains the scene
	 * @param bypass
	 */
	public UIGrid (CompleteAuthoringModelable elem, Tab container, boolean bypass) {
		myManager = elem;
		this.mySceneName = new SimpleStringProperty();
		this.mySceneName.addListener((obs, old, n) -> {
			container.setText(n);
		});
		sector();
		try {
			populate(bypass);
		}
		catch (VoogaException e) {
			VoogaAlert alert = new VoogaAlert(e.getMessage());
			alert.showAndWait();
		}

	}

	/**
	 * Sets constraints of the columns and rows of the grid pane.
	 */
	private void sector () {
		gameDisplayProperties = VoogaBundles.GameDisplayProperties;
		ColumnConstraints leftColumn = new ColumnConstraints();
		leftColumn.setPercentWidth(getConstraint("leftColConstraints"));
		ColumnConstraints rightColumn = new ColumnConstraints();
		rightColumn.setPercentWidth(getConstraint("rightColConstraints"));
		RowConstraints topRow = new RowConstraints();
		topRow.setPercentHeight(getConstraint("topRowConstraints"));
		RowConstraints middleRow = new RowConstraints();
		middleRow.setPercentHeight(getConstraint("middleRowConstraints"));
		RowConstraints bottomRow = new RowConstraints();
		bottomRow.setPercentHeight(getConstraint("bottomRowConstraints"));
		this.getColumnConstraints().addAll(leftColumn, rightColumn);
		this.getRowConstraints().addAll(topRow, middleRow, bottomRow);
	}

	/**
	 * Helper method to help parse integer from resource file
	 * @param s: property name from resource file
	 * @return integer parsed from resource file
	 */
	private int getConstraint(String s){
		return Integer.parseInt(gameDisplayProperties.getString(s));
	}

	/**
	 * Populates the grid pane with the appropriate windows, including the resource explorer,
	 * the design board, properties pane, and events window.
	 * @param bypass: whether or not to bypass to the designboard preferences display.
	 * @throws VoogaException
	 */
	private void populate (boolean bypass) throws VoogaException {
		explorer = new Explorer(myManager);
		this.add(explorer, 0, 0);

		designBoard = new DesignBoardHousing(myManager, bypass);
		Bindings.bindBidirectional(this.mySceneName, designBoard.getName());
		this.add(designBoard, 1, 0);
		GridPane.setRowSpan(designBoard, REMAINING);

		propertiesPane = new PropertiesPane(myManager);
		myManager.addObserver(propertiesPane.getPropertiesTabManager());
		ElementManager em = ((ElementManager) myManager);
		em.initGlobalVariablesPane();

		this.add(propertiesPane, 0, 1);
		EventsWindow events = new EventsWindow(myManager);
		this.add(events, 0, 2);
	}

	/**
	 * This function adds a new Scene, and thus tab in the design board housing, to the design
	 * board.
	 * 
	 * @param elem Interface for manager to backend model
	 */
	public void addScene (CompleteAuthoringModelable elem) {
		designBoard.addScene(elem);
	}

	/**
	 * @return current authoring model
	 */
	public CompleteAuthoringModelable getModel(){
		return myManager;
	}

	/**
	 * @return the name of the current scene
	 */
	public Property<String> getName() {
		return this.mySceneName;
	}



}
