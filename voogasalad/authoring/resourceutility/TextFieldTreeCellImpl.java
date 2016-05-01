package authoring.resourceutility;

import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeCell;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.Dragboard;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.input.TransferMode;

/**
 * This is a custom TreeCell implementation allowing renaming (and drag-and-drop soon to come)
 * 
 * @author DoovalSalad
 *
 */
public class TextFieldTreeCellImpl extends TreeCell<VoogaFile> {

	private TextField textField;
	private DataFormat voogaFormat;
	private ResourceTreeView structure;
	private boolean valid;
	
	public TextFieldTreeCellImpl(ResourceTreeView rtv) {
		this.structure = rtv;
		
		voogaFormat = VoogaFileFormat.getInstance();
		
		this.setOnMouseClicked(e -> {
			if(e.getButton() == MouseButton.SECONDARY && (getItem().getType() != VoogaFileType.FOLDER)) {
				setContextMenu(menu());
			}
		});
		
		this.setOnDragDetected(e -> {
            if (! isEmpty()) {
                Dragboard db = startDragAndDrop(TransferMode.COPY);
                ClipboardContent cc = new ClipboardContent();
                cc.put(voogaFormat, getItem());
                db.setContent(cc);
                Label label = new Label(String.format("%s", getItem().toString()));
                new Scene(label);
                db.setDragView(label.snapshot(null, null));
            }
            e.consume();
        });
		
		this.setOnDragOver(e -> {
			TextFieldTreeCellImpl cell = (TextFieldTreeCellImpl) e.getSource();
			if(cell != null) {
				cell.setStyle("-fx-background-color: #C6C6C6;");
			}
			e.acceptTransferModes(TransferMode.ANY);
			e.consume();
		});
		
		this.setOnDragExited(e -> {
			TextFieldTreeCellImpl cell = (TextFieldTreeCellImpl) e.getSource();
			if(cell != null) {
				cell.setStyle("-fx-background-color: transparent;");
			}
			e.acceptTransferModes(TransferMode.ANY);
			e.consume();
		});
		
		
		this.setOnDragDropped(e -> {
			Dragboard db = e.getDragboard();
	        boolean success = false;
	        if(db.hasContent(VoogaFileFormat.getInstance())) {
	        	VoogaFile file = ((TextFieldTreeCellImpl) e.getSource()).getItem();
	        	if(file.getType() == VoogaFileType.FOLDER) {
	        		rtv.reorder((VoogaFile) db.getContent(VoogaFileFormat.getInstance()), file);
	        	}
	            success = true;
	        }

	        e.setDropCompleted(success);
		});
		
		
	}
	
	/**
	 * Starts the editing action of a TreeCell item
	 */
	@Override
	public void startEdit() {
		super.startEdit();

		if (textField == null) {
			createTextField();
		}
		setText(null);
		setGraphic(textField);
		textField.selectAll();
	}

	/**
	 * Cancels the editing action of a TreeCell item
	 */
	@Override
	public void cancelEdit() {
		super.cancelEdit();
		setText(getItem().toString());
		setGraphic(getTreeItem().getGraphic());
	}

	/**
	 * Updates the name of the TreeCell item
	 */
	@Override
	public void updateItem(VoogaFile item, boolean empty) {
		if(getTreeItem() != null) {
		}
		super.updateItem(item, empty);
		if (empty) {
			setText(null);
			setGraphic(null);
		} else {
			if (isEditing()) {
				if (textField != null) {
					textField.setText(getString());
				}
				setText(null);
				setGraphic(textField);
			} else {
				setText(getString());
				setGraphic(getTreeItem().getGraphic());
			}
		}
	}
	
	/**
	 * Creates the context menu that allows items to be previewed.
	 * @return
	 */
	private ContextMenu menu() {
		ContextMenu menu = new ContextMenu();
		MenuItem preview = new MenuItem("Preview...");
		MenuItem delete = new MenuItem("Delete");
		preview.setOnAction(e -> {
			new PreviewMirror(getItem());
		});
		delete.setOnAction(e -> {
			structure.remove(this.getTreeItem());
		});
		menu.getItems().addAll(preview, delete);
		return menu;
	}

	/**
	 * Creates the text field with which to edit the TreeCell item
	 */
	private void createTextField() {
		textField = new TextField(getString());
		valid = true;
		textField.textProperty().addListener((obs, old, newVal) -> {
			if(structure.getFileNamesOfType(VoogaFileType.FOLDER).contains(newVal)) {
				textField.setStyle("-fx-border-color: red");
				valid = false;
			}
		});
		textField.setOnKeyReleased(e -> {
			if (e.getCode() == KeyCode.ENTER && valid) {
				VoogaFile file = getTreeItem().getValue();
				file.setName(textField.getText());
				commitEdit(file);
			} else if (e.getCode() == KeyCode.ESCAPE) {
				cancelEdit();
			}
		});
	}

	/**
	 * Returns the TreeCell item as a String
	 * @return
	 */
	private String getString() {
		return getItem() == null ? "" : getItem().toString();
	}
}
