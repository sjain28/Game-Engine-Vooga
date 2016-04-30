package authoring.gui.menubar.builders;

import java.util.HashMap;
import java.util.Map;

import authoring.CustomText;
import authoring.interfaces.model.EditElementable;
import authoring.resourceutility.ButtonMaker;
import gameengine.Sprite;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import tools.VoogaAlert;
import tools.VoogaFileChooser;
import tools.VoogaNumber;
import tools.interfaces.VoogaData;


public class ArchetypeBuilder extends Builder {

    private static final double WIDTH = 400;
    private static final double HEIGHT = 250;
    private String myImagePath;
    private Map<String, VoogaData> myProperties;
    private Button image;
    private ImageView iv;
    private TabPane tabpane;
    private Tab createTab;
    private Tab propertiesTab;
    private TextField archetypeName;
    private TextField mass;
    private VBox creation;
    private CheckBox infiniteMass;

    private EditElementable editor;

    public ArchetypeBuilder (EditElementable editor) {
        super(editor);
        this.editor = editor;

        this.setMinWidth(WIDTH);
        this.setMinHeight(HEIGHT);
        this.tabpane = new TabPane();
        this.myProperties = new HashMap<String, VoogaData>();
        this.createTab = new Tab("Create");
        this.propertiesTab = new Tab("Properties");
        this.creation = new VBox();
        this.tabpane.getTabs().addAll(createTab, propertiesTab);
        this.archetypeName = new TextField();
        this.mass = new TextField();
        populate();
        load(this.tabpane);
    }

    private void populate () {
        createTab.setContent(creation);
        creation.setSpacing(SPACING);
        creation.setPadding(new Insets(SPACING));
        creation.getChildren()
                .addAll(makeInfo("Archetype:", "Enter an archetype...", archetypeName),
                        makeImagePicker(),
                        makeMassPicker(), makeButtons());
        PropertiesTab pt = new PropertiesTab(myProperties);
        propertiesTab.setContent(pt);
    }

    private HBox makeImagePicker () {
        iv = new ImageView();
        image = new ButtonMaker().makeButton("Choose Image", e -> {
            VoogaFileChooser fileChooser = new VoogaFileChooser();
            String path;
            try {
                path = fileChooser.launch();
                loadImage(path);
            }
            catch (Exception e1) {
                VoogaAlert alert = new VoogaAlert(e1.getMessage());
                alert.showAndWait();
            }

        });
        return makeRow(new CustomText("Image"), image, iv);
    }

    private HBox makeMassPicker () {
        mass = new TextField();
        mass.setPromptText("Enter a mass...");
        infiniteMass = new CheckBox("Static (infinite mass)");
        infiniteMass.selectedProperty().addListener( (obs, old, newVal) -> {
            mass.setDisable(newVal);
            if (newVal) {
                mass.clear();
            }
        });
        return makeRow(new CustomText("Mass:"), mass, infiniteMass);
    }

    public void setImagePath (String path) {
        loadImage(path);
        this.image.setDisable(true);
    }

    private void loadImage (String path) {
        this.myImagePath = path;
        this.iv.setImage(new Image("file:" + myImagePath));
        this.iv.setPreserveRatio(true);
        this.iv.setFitWidth(50);
    }

    public String getArchetypeName () {
        return this.archetypeName.getText();
    }

    @Override
    public void compile () {

        if (editor.getSpriteFactory().getAllArchetypeNames().contains(archetypeName.getText()) ||
            editor.getMySpriteNames().contains(archetypeName.getText())) {
            new VoogaAlert("This name already exists");
            return;
        }
        compile = true;
        VoogaNumber mass = new VoogaNumber();
        try {
            if (infiniteMass.isSelected()) {
                mass.setValue(Double.POSITIVE_INFINITY);
            }
            else {
                mass.setValue(Double.parseDouble(this.mass.getText()));
            }

            mySpriteFactory.addArchetype(archetypeName.getText(),
                                         new Sprite("file:" + myImagePath,
                                                    archetypeName.getText(), myProperties, mass));
            quit();
        }
        catch (Exception e) {
            compile = false;
            e.printStackTrace();
            numberError("Please input all values");
        }
    }

    @Override
    public boolean compileStatus () {
        return compile;
    }

}
