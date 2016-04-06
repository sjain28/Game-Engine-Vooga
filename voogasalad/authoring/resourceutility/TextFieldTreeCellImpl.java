package authoring.resourceutility;

import javafx.scene.control.TextField;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.KeyCode;
import javafx.scene.input.TransferMode;

/**
 * This is a custom TreeCell implementation allowing renaming and drag-and-drop
 * functionality
 *
 */
public class TextFieldTreeCellImpl extends TreeCell<VoogaFile> {

	private TextField textField;
	private TreeItem<VoogaFile> draggedFile;
	private TreeItem<VoogaFile> dragDestination;

	public TextFieldTreeCellImpl() {
		this.setOnDragDetected(e -> {
			System.out.println("Detected");
	        Dragboard db = startDragAndDrop(TransferMode.MOVE);
	        ClipboardContent content = new ClipboardContent();
	        content.putString("placeholder");
	        db.setContent(content);
	        if(e.getSource() instanceof TextFieldTreeCellImpl) {
	    		TextFieldTreeCellImpl cell = (TextFieldTreeCellImpl) e.getSource();
	    		System.out.println(e.getSource());
	    		draggedFile = cell.getTreeItem();
	    	}
	        e.consume();
		});
	    setOnDragOver(e -> {
	    	if(e.getTarget() instanceof TextFieldTreeCellImpl) {
	    		TextFieldTreeCellImpl cell = (TextFieldTreeCellImpl) e.getTarget();
	    		dragDestination = cell.getTreeItem();
	    		System.out.println(dragDestination);
	    	}
	    	
	        e.consume();
	    });
	    setOnDragDone(e -> {
	        if(dragDestination != null) {
	        	System.out.println(dragDestination);
	        }
	        e.consume();
		});
		

		/*
		setOnDragEntered(e -> {
            System.out.println(" Entered ");
            e.consume();
        });
		setOnDragDropped(e -> {
			System.out.println(" Dropped ");
	        e.setDropCompleted(true);
	        e.consume();
	    });
	    setOnDragExited(e -> {
	    	System.out.println(" Exited ");
	        e.consume();
	    });
	    */
	    
	    
	}
	
	protected boolean isDraggableToParent() {
        return getTreeItem().getValue().getType() == VoogaFileType.FOLDER;
    }
	
	protected boolean isNotAlreadyChildOfTarget(TreeItem<VoogaFile> treeItemParent) {
        if(draggedFile == treeItemParent)
            return false;
        
        if(treeItemParent.getParent() != null)
            return isNotAlreadyChildOfTarget(treeItemParent.getParent());
        else
            return true;
    }
	
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

	@Override
	public void cancelEdit() {
		super.cancelEdit();
		setText(getItem().toString());
		setGraphic(getTreeItem().getGraphic());
	}

	@Override
	public void updateItem(VoogaFile item, boolean empty) {
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

	private void createTextField() {
		textField = new TextField(getString());
		textField.setOnKeyReleased(e -> {
			if (e.getCode() == KeyCode.ENTER) {
				commitEdit(new VoogaFile(getItem().getType(), textField.getText()));
			} else if (e.getCode() == KeyCode.ESCAPE) {
				cancelEdit();
			}
		});
	}

	private String getString() {
		return getItem() == null ? "" : getItem().toString();
	}
}
