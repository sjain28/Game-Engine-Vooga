package authoring.properties;

/**
 * Abstract Properties Tab that includes shared characteristics between tabs.
 * 
 * @author Harry Guo, Nick Lockett, Arjun Desai, Aditya Srinivasan
 */
import java.util.HashMap;
import java.util.Map;
import authoring.CustomText;

import authoring.gui.items.NumberTextField;
import authoring.gui.items.SwitchButton;
import authoring.gui.menubar.builders.PropertyBuilder;
import authoring.interfaces.Elementable;
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
import tools.VoogaBoolean;
import tools.VoogaNumber;
import tools.interfaces.VoogaData;


public class GenericPropertiesTab extends Tab {

    private static final double SPACING = 10;

    private VBox box = new VBox(SPACING);
    protected Map<String, VoogaData> propertiesMap;
    private HBox propertiesHBox = new HBox(10);
    protected ScrollPane myScrollPane = new ScrollPane();
    private Elementable myElementable;

    public GenericPropertiesTab (String tabName) {
        propertiesMap = new HashMap<String, VoogaData>();
        this.setText(tabName);
        this.setClosable(false); 
        box.getChildren().add(myScrollPane);
        this.setContent(box);
        createButtons();
    }
    
    /**
     * Gets the properties map based off the Elementable
     * 
     * @param o
     */
    public void getPropertiesMap(Elementable elem) {
    	//System.out.println("Setting Up Global Properties");
		myElementable = elem;
		propertiesMap = myElementable.getVoogaProperties();
		updateProperties();
	}

    public void updateProperties () {
        propertiesHBox.getChildren().clear();
     
        VBox properties = new VBox(SPACING);
        Text name = null;
        Node node = null;

        for (String property : propertiesMap.keySet()) {
            name = new CustomText(property);

            ContextMenu menu = new ContextMenu();
            MenuItem delete = new MenuItem("Delete");
            delete.setOnAction(e -> removeProperty(property));
            menu.getItems().add(delete);

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
    
    /**
     * Binds data to property map when adding/deleting/updating the map
     * @param property
     * @param node
     * @param data
     */
    public void bindDataToMap(String property, Node node, VoogaData data){
        if (data instanceof VoogaNumber){
            NumberTextField field = (NumberTextField) node;
            field.textProperty().addListener((obs,old,newVal)->{
            	data.setProperty(newVal);
                propertiesMap.put(property, data);
            });
        }
        if (data instanceof VoogaBoolean) {
        	SwitchButton field = (SwitchButton) node;
        	field.booleanProperty().addListener((obs,old,newVal)->{
        		data.setProperty(newVal);
        		propertiesMap.put(property, data);
            });
        }
    }
    
    /**
     * Creates the Add, Apply, and Cancel Buttons for the Properties Pane.
     */
    public void createButtons () {
        Button addProperty = new ButtonMaker().makeButton("Add Property", e -> addNewPropertyPrompt());
        Button apply = new ButtonMaker().makeButton("Apply Changes", e -> updateProperties());
        HBox buttonsPanel = new HBox(SPACING);
        buttonsPanel.getChildren().addAll(apply, addProperty);
        this.box.getChildren().addAll(buttonsPanel);
    }

	/**
     * Creates the Dialog Box that allows new Properties to be added
     */
    public void addNewPropertyPrompt () {
    	PropertyBuilder pBuilder = new PropertyBuilder();
        pBuilder.showAndWait();
        addNewProperty(pBuilder.getName(), pBuilder.getValue());
    }
  
    /**
     * Adds new property to properties map based on string and vooga Data
     * 
     * @param s
     * @param vgData
     */
	public void addNewProperty(String s, VoogaData vgData) {
		myElementable.addProperty(s, vgData);
		propertiesMap.put(s, vgData);
		updateProperties();
	}

    /**
     * Removes the property from the property map based on the string
     * 
     * @param s
     */
	public void removeProperty(String s) {
		myElementable.removeProperty(s);
		propertiesMap.remove(s);
		updateProperties();
	}

}