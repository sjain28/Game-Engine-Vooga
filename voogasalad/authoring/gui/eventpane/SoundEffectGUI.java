package authoring.gui.eventpane;

import java.io.File;
import java.util.ResourceBundle;

import authoring.interfaces.model.EditEventable;
import authoring.resourceutility.ButtonMaker;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser.ExtensionFilter;
import resources.VoogaBundles;
import tools.VoogaAlert;
import tools.VoogaException;
import tools.VoogaFileChooser;


public class SoundEffectGUI implements EventGUI {
	
	private static final int PADDING = 15;
	
    private Text file;
    private Button browse;
    private VoogaFileChooser fileChooser;

    private EditEventable manager;
    private HBox node;
    
    public SoundEffectGUI (EditEventable manager) {
        this.manager = manager;
        initialize();
    }

    private void initialize () {
        fileChooser = new VoogaFileChooser();
        fileChooser.setInitialDirectory(new File("resources/sound/"));
        ResourceBundle extensions = VoogaBundles.extensionProperties;
        for (String key : extensions.keySet()) {
            if (extensions.getString(key).equalsIgnoreCase("AUDIO")) {
                fileChooser.addFilters(new ExtensionFilter("(" + key + ")", "*." + key));
            }
        }
        file = new Text();
        browse = new ButtonMaker().makeButton("Browse", e -> {
            try {
                file.setText(fileChooser.launch());
            }
            catch (Exception e1) {
                VoogaAlert alert = new VoogaAlert(e1.getMessage());
                alert.showAndWait();
            }
        });
        node = generateHBox(new Text("Sound Path"), browse, file);
    }

    @Override
    public Node display () {
        return node;
    }

    private HBox generateHBox (Node ... nodes) {
        HBox hbox = new HBox();
        hbox.setSpacing(PADDING);
        for (Node node : nodes) {
            hbox.getChildren().add(node);
        }
        return hbox;
    }

    @Override
    public String getDetails () throws VoogaException {
        return "events.SoundEffect," + file.getText();
    }

	/**
	 * @return the manager
	 */
	public EditEventable getManager() {
		return manager;
	}

}
