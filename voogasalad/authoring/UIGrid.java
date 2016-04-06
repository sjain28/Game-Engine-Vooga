package authoring;

import java.util.UUID;

import authoring.gui.DesignBoard;
import authoring.gui.EventsWindow;
import authoring.gui.PropertiesWindow;
import authoring.model.GameObject;
import authoring.resourceutility.ResourceDecipherer;
import authoring.resourceutility.ResourceUI;
import javafx.scene.Node;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

/**
 * The UIGrid assembles the window components into a grid
 *
 */
public class UIGrid extends GridPane {

	private DesignBoard designBoard;
	private ResourceUI explorer;
	
	public UIGrid() {
		sector();
		populate();
		initializeDrag();
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
		explorer = new ResourceUI();
		this.add(explorer, 0, 0);
		designBoard = new DesignBoard();
		this.add(designBoard, 1, 0);
		GridPane.setRowSpan(designBoard, REMAINING);
		PropertiesWindow properties = new PropertiesWindow();
		this.add(properties, 0, 1);
		EventsWindow events = new EventsWindow();
		this.add(events, 0, 2);
	}

	private void initializeDrag() {
		designBoard.setOnDragOver((DragEvent e) -> mouseDragOver(e));
		designBoard.setOnDragDropped((DragEvent e) -> mouseDragDropped(e));
		designBoard.setOnDragExited((DragEvent e) -> designBoard.setStyle("-fx-border-color: #C6C6C6;"));
	}

	private void mouseDragDropped(final DragEvent event) {
		Dragboard db = event.getDragboard();
		boolean success = false;
		if (db.hasString()) {
			addElement(db.getString());
			success = true;
		}

		event.setDropCompleted(success);
		event.consume();
	}

	private void mouseDragOver(final DragEvent event) {

		if (event.getGestureSource() != explorer && event.getDragboard().hasString()) {
			event.acceptTransferModes(TransferMode.MOVE);
		}
		event.consume();
	}

	private void addElement(String elementPath) {
		Node node;
		if (ResourceDecipherer.isImage(elementPath)) {
			node = new GameObject(elementPath, UUID.randomUUID().toString());
			addElement(node);
		}
	}

	public void addElement(Node node) {
		designBoard.getContent().getChildren().add(node);
	}

	private void moveElement(String id, Dragboard db) {
		//Node element = elementManager.getElement(id);
		//System.out.println("" + db.getDragViewOffsetX() + " " + db.getDragViewOffsetY());
		//element.setTranslateX(db.getDragViewOffsetX());
		//element.setTranslateY(db.getDragViewOffsetY());

	}

}
