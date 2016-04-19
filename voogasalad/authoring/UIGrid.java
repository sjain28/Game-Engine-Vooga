package authoring;

import authoring.gui.DesignBoardHousing;
import authoring.properties.PropertiesPane;
import authoring.gui.EventsWindow;
import authoring.interfaces.model.CompleteAuthoringModelable;
import authoring.resourceutility.ResourceUI;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import tools.VoogaAlert;
import tools.VoogaException;


/**
 * The UIGrid assembles the window components into a grid
 * 
 * Specifically: The DesignBoardHousing, EventWindow, PropertiesPane, and Explorer
 *
 */
public class UIGrid extends GridPane{

    private PropertiesPane propertiesPane;
    private DesignBoardHousing designBoard;
    private Explorer explorer;
    private CompleteAuthoringModelable myManager;

    /**
     * Initialized the UIGrid
     * 
     * TODO: Implement with Mosaic
     * 
     * @param elem: Interface to Manager for the backend
     */
    public UIGrid (CompleteAuthoringModelable elem) {
        myManager = elem;
        sector();
        try {
            populate();
        }
        catch (VoogaException e) {
            new VoogaAlert(e.getMessage());
        }
    }

    private void sector () {
        ColumnConstraints leftColumn = new ColumnConstraints();
        leftColumn.setPercentWidth(25);
        ColumnConstraints rightColumn = new ColumnConstraints();
        rightColumn.setPercentWidth(75);
        RowConstraints topRow = new RowConstraints();
        topRow.setPercentHeight(33);
        RowConstraints middleRow = new RowConstraints();
        middleRow.setPercentHeight(33);
        RowConstraints bottomRow = new RowConstraints();
        bottomRow.setPercentHeight(33);
        this.getColumnConstraints().addAll(leftColumn, rightColumn);
        this.getRowConstraints().addAll(topRow, middleRow, bottomRow);
    }

    private void populate () throws VoogaException {
        explorer = new Explorer(myManager);
        this.add(explorer, 0, 0);
        designBoard = new DesignBoardHousing(myManager);
        this.add(designBoard, 1, 0);
        GridPane.setRowSpan(designBoard, REMAINING);
        propertiesPane = new PropertiesPane();
        myManager.addObserver(propertiesPane);
        // this looks like a bad piece of code

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

}
