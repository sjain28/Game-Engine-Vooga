package authoring.resourceutility;
import authoring.VoogaScene;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ResourceUI extends VBox {
	
	private HBox buttonContainer;
	private ResourceTreeView rtv;
	
	public ResourceUI(Stage primaryStage) {
		buttonContainer = new HBox();
		
		rtv = new ResourceTreeView(new VoogaFile(VoogaFileType.FOLDER, "My Project"));
	
		this.getChildren().addAll(rtv, buttonContainer);
		
		Scene scene = new VoogaScene(this);
	
		primaryStage.setScene(scene);
		primaryStage.show();
		
		makeAddButtons();
		
	}
	
	private void makeAddButtons() {
		ButtonMaker maker = new ButtonMaker();
		this.buttonContainer.getChildren().addAll(maker.makeButton("Add Folder", e -> promptFolderName()),
												  maker.makeButton("Import File", e -> importFile()));
		
	}
	
	private void promptFolderName() {
		new NamePrompter(this);
	}
	
	private void importFile() {
		new FileImporter(this);
	}
	
	public void addFolder(String folderName) {
		rtv.addItem(new VoogaFile(VoogaFileType.FOLDER, folderName));
	}

	public void addItem(VoogaFile voogaFile) {
		rtv.addItem(voogaFile);
	}
	
}
