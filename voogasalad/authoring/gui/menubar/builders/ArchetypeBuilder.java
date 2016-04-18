package authoring.gui.menubar.builders;

import java.util.HashMap;
import java.util.Map;
import authoring.gui.items.NumberTextField;
import authoring.interfaces.model.EditElementable;
import gameengine.Sprite;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import tools.VoogaAlert;
import tools.VoogaException;
import tools.VoogaFileChooser;
import tools.VoogaNumber;
import tools.interfaces.VoogaData;


public class ArchetypeBuilder extends Builder {
    private final int MASS_INDEX = 1;
    private String myImagePath;
    private Map<String, VoogaData> myProperties;
    private Button image;
    private ImageView iv;

    public ArchetypeBuilder (EditElementable editor, Stage popup) {
        super(editor, popup);
        myProperties = new HashMap<String, VoogaData>();
        makeInfo("Archetype");
        makeProperty();
        makeImagePicker();
        makeInfo("Mass", MASS_INDEX);
        makeCreate();
    }

    private void makeProperty () {
        HBox complete = new HBox();

        Text name = new Text("Property Name: ");
        name.setFill(Color.WHITE);
        TextField nameVal = new TextField();
        Text value = new Text("Value: ");
        value.setFill(Color.WHITE);
        NumberTextField valueVal = new NumberTextField();
        Button set = new Button("add");
        set.setOnAction(e -> {
            VoogaData val = new VoogaNumber();
            try {
                val.setValue(Double.parseDouble(valueVal.getText()));
                myProperties.put(nameVal.getText(), val);
            }
            catch (Exception NumberFormatException) {
                numberError("Property Value");
            }

            nameVal.clear();
            valueVal.clear();
        });
        complete.getChildren().addAll(name, nameVal, value, valueVal, set);
        this.getChildren().add(complete);

    }

    private void makeImagePicker () {
        HBox complete = new HBox();
        Text label = new Text("Image");
        label.setFill(Color.WHITE);
        image = new Button("Choose Image");
        iv = new ImageView();
        image.setOnAction(e -> {
            VoogaFileChooser fileChooser = new VoogaFileChooser();

            // Set extension filter
            FileChooser.ExtensionFilter extFilterJPG =
                    new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
            FileChooser.ExtensionFilter extFilterPNG =
                    new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
            fileChooser.addFilters(extFilterJPG, extFilterPNG);

            try {
                myImagePath = fileChooser.launch();
            }
            catch (VoogaException e1) {
                new VoogaAlert(e1.getMessage());
            }
            
            System.out.println(myImagePath);
            
            iv.setImage(new Image("file:"+myImagePath));
            iv.setPreserveRatio(true);
            iv.setFitWidth(80);
            
        });
        complete.getChildren().addAll(label, image, iv);
        this.getChildren().add(complete);
    }

    public void setImagePath (String path) {
        this.myImagePath = path;
        this.iv.setImage(new Image("file:///" + myImagePath));
        this.iv.setPreserveRatio(true);
        this.iv.setFitWidth(80);
        this.image.setDisable(true);
    }

    @Override
    public void compile () {
        VoogaNumber mass = new VoogaNumber();
        try {
            mass.setValue(Double.parseDouble(getInfo(MASS_INDEX)));

            getSpriteMaker().addArchetype(getInfo(),
                                          new Sprite("file:///" + myImagePath, getInfo(),
                                                     myProperties, mass));
            quit();
        }
        catch (Exception e) {
            e.printStackTrace();
            numberError("Please Input all Values");
        }
    }

}
