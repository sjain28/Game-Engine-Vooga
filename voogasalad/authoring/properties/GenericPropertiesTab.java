package authoring.properties;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import authoring.CustomText;
import authoring.gui.items.NumberTextField;
import authoring.gui.items.SwitchButton;
import authoring.gui.menubar.builders.PropertyBuilder;
import authoring.gui.menubar.builders.TextObjectBuilder;
import authoring.interfaces.Elementable;
import authoring.interfaces.model.CompleteAuthoringModelable;
import authoring.model.VoogaFrontEndText;
import authoring.resourceutility.ButtonMaker;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import resources.VoogaBundles;
import tools.VoogaAlert;
import tools.VoogaBoolean;
import tools.VoogaNumber;
import tools.interfaces.VoogaData;


/**
 * Generic Properties Tab to display properties on the GUI.
 * 
 * @author Harry Guo, Nick Lockett, Arjun Desai, Aditya Srinivasan
 */

public class GenericPropertiesTab extends Tab {

    private double SPACING;

    private VBox box;
    private HBox propertiesHBox;
    protected Map<String, VoogaData> propertiesMap;
    protected ScrollPane myScrollPane;
    private Elementable myElementable;
    private CompleteAuthoringModelable manager;

    private ResourceBundle ppProperties;

    public GenericPropertiesTab (String tabName, CompleteAuthoringModelable manager) {
        this.manager = manager;
        ppProperties = VoogaBundles.propertiesPaneProperties;
        SPACING = Double.parseDouble(ppProperties.getString("Spacing"));
        box = new VBox(SPACING);
        propertiesHBox = new HBox(SPACING);
        propertiesMap = new HashMap<String, VoogaData>();
        myScrollPane = new ScrollPane();
        this.setText(tabName);
        this.setClosable(false);
        box.getChildren().add(myScrollPane);
        this.setContent(box);
        createButtons();
    }

    /**
     * Gets the properties map based off the Elementable
     * 
     * @param elem: elmenta
     */
    public void getPropertiesMap (Elementable elem) {
        myElementable = elem;
        propertiesMap = myElementable.getVoogaProperties();
        updateProperties();
    }

    /**
     * Updates the property scrollpane based on the properties of the elementable
     */
    public void updateProperties () {
        if (elementablePresent()) {
            propertiesHBox.getChildren().clear();

            VBox properties = new VBox(SPACING);
            Text name = null;
            Node node = null;

            for (String property : propertiesMap.keySet()) {
                name = new CustomText(property);

                ContextMenu menu = new ContextMenu();
                MenuItem delete = new MenuItem(ppProperties.getString("Delete"));
                delete.setOnAction(e -> removeProperty(property));
                MenuItem display = new MenuItem(ppProperties.getString("DisplayOnScreen"));
                display.setOnAction(e -> createDisplayedText(property));

                menu.getItems().addAll(delete, display);

                name.setOnMouseClicked(e -> {
                    if (e.getButton() == MouseButton.SECONDARY) {
                        menu.show(myScrollPane, e.getScreenX(), e.getScreenY());
                    }
                });
                node = propertiesMap.get(property).display();
                bindDataToMap(property, node, propertiesMap.get(property));
                properties.getChildren().add(new PropertyBox(name, node));
            }
            propertiesHBox.getChildren().addAll(properties);
            myScrollPane.setContent(propertiesHBox);
        }
    }

    /**
     * Create VoogaFrontEnd formatted text to display on the GUI
     * @param s: string to put in
     */
    private void createDisplayedText (String s) {
        VoogaFrontEndText text = new VoogaFrontEndText(s);
        text.setVoogaData(propertiesMap.get(s));
        TextObjectBuilder builder = new TextObjectBuilder(manager, text);
        builder.showAndWait();
    }

    /**
     * Binds data to property map when adding/deleting/updating the map
     * 
     * @param property
     * @param node
     * @param data
     */
    public void bindDataToMap (String property, Node node, VoogaData data) {
        if (data instanceof VoogaNumber) {
            NumberTextField field = (NumberTextField) node;
            field.textProperty().addListener( (obs, old, newVal) -> {
                data.setProperty(newVal);
                propertiesMap.put(property, data);
            });
        }
        if (data instanceof VoogaBoolean) {
            SwitchButton field = (SwitchButton) node;
            field.booleanProperty().addListener( (obs, old, newVal) -> {
                data.setProperty(newVal);
                propertiesMap.put(property, data);
            });
        }
    }

    /**
     * Creates the Add, Apply, and Cancel Buttons for the Properties Pane.
     */
    public void createButtons () {
        Button addProperty =
                new ButtonMaker().makeButton(ppProperties.getString("Add"),
                                             e -> addNewPropertyPrompt());
        Button apply =
                new ButtonMaker().makeButton(ppProperties.getString("Apply"),
                                             e -> updateProperties());
        HBox buttonsPanel = new HBox(SPACING);
        buttonsPanel.getChildren().addAll(apply, addProperty);
        this.box.getChildren().addAll(buttonsPanel);
    }

    /**
     * Creates the Dialog Box that allows new Properties to be added
     */
    public void addNewPropertyPrompt () {
        if (elementablePresent()) {
            PropertyBuilder pBuilder = new PropertyBuilder();
            pBuilder.showAndWait();
            if (pBuilder.compileStatus() && pBuilder.getName() != null &&
                pBuilder.getValue() != null) {
                addNewProperty(pBuilder.getName(), pBuilder.getValue());
            }
        }
    }

    /**
     * Adds new property to properties map based on string and vooga Data
     * 
     * @param s
     * @param vgData
     */
    public void addNewProperty (String s, VoogaData vgData) {
        myElementable.addProperty(s, vgData);
        propertiesMap.put(s, vgData);
        updateProperties();
    }

    /**
     * Removes the property from the property map based on the string
     * 
     * @param s
     */
    public void removeProperty (String s) {
        myElementable.removeProperty(s);
        propertiesMap.remove(s);
        updateProperties();
    }

    /**
     * Checks if there is a sprite selected.
     * 
     * @return
     */
    private boolean elementablePresent () {
        if (myElementable == null) {
            VoogaAlert alert = new VoogaAlert(ppProperties.getString("NoSpriteError"));
            alert.showAndWait();
            return false;
        }
        return true;
    }

}
