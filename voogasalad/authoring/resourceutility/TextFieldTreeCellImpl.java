package authoring.resourceutility;

import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.KeyCode;
import javafx.scene.input.TransferMode;

public class TextFieldTreeCellImpl extends TreeCell<VoogaFile> {

	private TextField textField;
	private TreeItem<VoogaFile> draggedFile;

	public TextFieldTreeCellImpl() {
		this.setOnDragDetected(e -> {
                ClipboardContent content = new ClipboardContent();
                
                Dragboard dragboard = getTreeView().startDragAndDrop(TransferMode.MOVE);
                dragboard.setContent(content);
                
                draggedFile = getTreeItem();
                
                System.out.println("start");
                
                e.consume();
		});
		
		setOnDragDone(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent dragEvent) {
                System.out.println("Drag dropped on ");
                dragEvent.consume();
            }
        });
		
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
