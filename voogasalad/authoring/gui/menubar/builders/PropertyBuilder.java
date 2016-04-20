package authoring.gui.menubar.builders;

import authoring.CustomText;
import authoring.gui.items.SwitchButton;
import authoring.gui.menubar.MenuItemHandler;
import authoring.interfaces.model.EditElementable;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import player.gamedisplay.Menuable;
import tools.VoogaException;
import tools.VoogaNumber;
import tools.interfaces.VoogaData;


public class PropertyBuilder extends Builder {

    private static final String VOOGA_NUMBER = "VoogaNumber";
    private static final String VOOGA_BOOLEAN = "VoogaBoolean";

    private VBox container;
    private PropertiesTable pTable;
    private TextField variableName;
    private TextField variableValue;
    private RadioButton number;
    private HBox numberSelector;
    private HBox boolSelector;
    private HBox buttons;
    private SwitchButton swtch;
    private String chosenData;

    public PropertyBuilder () {
        this(null);
    }

    public PropertyBuilder (EditElementable editor) {
        super(editor);
        this.variableName = new TextField();
        this.variableValue = new TextField();
        this.swtch = new SwitchButton(true);
        this.numberSelector = makeInfo("Value:", "Enter a value...", variableValue);
        this.boolSelector = makeRow(new CustomText("Value:"), swtch);
        this.buttons = makeButtons();
        this.chosenData = VOOGA_NUMBER;
        populate();
        load(this.container);
    }

    private void populate () {
        this.container = new VBox();
        this.container.setSpacing(SPACING);
        this.container.setPadding(new Insets(SPACING));
        container.getChildren().addAll(makeDataSelector(),
                                       makeInfo("Property Name:", "Enter a name...", variableName),
                                       numberSelector, buttons);
    }

    private HBox makeDataSelector () {
        ToggleGroup group = new ToggleGroup();
        RadioButton number = new RadioButton("Number");
        number.setToggleGroup(group);
        number.setSelected(true);
        RadioButton bool = new RadioButton("Boolean");
        bool.setToggleGroup(group);
        number.selectedProperty().addListener( (obs, old, newVal) -> {
            container.getChildren().remove(buttons);
            if (newVal) {
                container.getChildren().add(numberSelector);
                container.getChildren().remove(boolSelector);
                chosenData = VOOGA_NUMBER;
            }
            else {
                container.getChildren().remove(numberSelector);
                container.getChildren().add(boolSelector);
                chosenData = VOOGA_BOOLEAN;
            }
            container.getChildren().add(buttons);
        });
        return makeRow(number, bool);
    }

    public String getName () {
        return this.variableName.getText();
    }

    public VoogaData getValue () {
        VoogaData data;
        Class<?> clazz;
        try {
            clazz = Class.forName("tools." + chosenData);
            data = (VoogaData) clazz.getConstructor().newInstance();
            try {
                data.setValue(Double.parseDouble(variableValue.getText()));
            }
            catch (NumberFormatException e) {

            }
            data.setValue(swtch.switchOnProperty());
            return data;
        }
        catch (Exception ee) {
            ee.printStackTrace();
            return null;
        }
    }

    @Override
    public void compile () {
        try {

        }
        catch (Exception e) {
            numberError("Please input a valid number");
        }
        quit();
    }

}
