package authoring;

import authoring.gui.DesignBoard;
import authoring.gui.EventsWindow;
import authoring.gui.PropertiesWindow;
import authoring.resourceutility.ResourceUI;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

/**
 * The UIGrid assembles the window components into a grid
 *
 */
public class UIGrid extends GridPane {
	
	public UIGrid() {
		sector();
		populate();
	}

	private void sector() {
		ColumnConstraints leftColumn = new ColumnConstraints();
		leftColumn.setPercentWidth(30);
		ColumnConstraints rightColumn = new ColumnConstraints();
		rightColumn.setPercentWidth(70);
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
	    ResourceUI explorer = new ResourceUI();
            this.add(explorer, 0, 0);
            DesignBoard designBoard = new DesignBoard();
            this.add(designBoard, 1, 0);
            GridPane.setRowSpan(designBoard, REMAINING);
            PropertiesWindow properties = new PropertiesWindow();
            this.add(properties, 0, 1);
            EventsWindow events = new EventsWindow();
            this.add(events, 0, 2);
	}
	
	
}
