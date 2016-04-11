package authoring;

import java.util.UUID;

import authoring.Properties.PropertiesPane;
import authoring.gui.DesignBoardHousing;
import authoring.gui.EventsWindow;
import authoring.model.GameObject;
import authoring.resourceutility.ResourceDecipherer;
import authoring.resourceutility.ResourceUI;
import authoring.resourceutility.VoogaFile;
import javafx.scene.Node;
import javafx.scene.control.TreeItem;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

/**
 * The UIGrid assembles the window components into a grid
 *
 */
public class UIGrid extends GridPane {

	private DesignBoardHousing designBoard;
	private ResourceUI explorer;
	
	public UIGrid() {
		sector();
		populate();
	}

	private void sector() {
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

	private void populate() {
		explorer = new ResourceUI();
		this.add(explorer, 0, 0);
		designBoard = new DesignBoardHousing();
		this.add(designBoard, 1, 0);
		GridPane.setRowSpan(designBoard, REMAINING);
		PropertiesPane properties = new PropertiesPane();
		this.add(properties, 0, 1);
		EventsWindow events = new EventsWindow();
		this.add(events, 0, 2);
	}

    public void addScene () {
        designBoard.addScene();
    }

}
