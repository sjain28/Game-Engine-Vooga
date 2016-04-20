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


public abstract class AbstractPropertiesTab extends Tab {

    private static final double SPACING = 10;

    private VBox box = new VBox(SPACING);
    protected Map<String, VoogaData> propertiesMap;
    private HBox propertiesHBox = new HBox(10);
    protected ScrollPane myScrollPane = new ScrollPane();
    private Elementable myElementable;

    public AbstractPropertiesTab () {
        propertiesMap = new HashMap<String, VoogaData>();
        
//        propertiesMap.addListener(new MapChangeListener<String,VoogaData>(){
//    		@Override
//    		public void onChanged(
//    				javafx.collections.MapChangeListener.Change<? extends String, ? extends VoogaData> change) {
//    			onMapChange();
//    		}
//        });
        
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
		myElementable = elem;
		propertiesMap = myElementable.getVoogaProperties();
		updateProperties();
	}

    public void updateProperties () {
        propertiesHBox.getChildren().clear();
     
        VBox properties = new VBox(SPACING);

        Text name = null;
        Node data = null;

      
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

            data = propertiesMap.get(property).display();
            
            bindDataToMap(property,data);
            
            properties.getChildren().add(new PropertyBox(name, data));
        }

        propertiesHBox.getChildren().addAll(properties);
        myScrollPane.setContent(propertiesHBox);
    }
    
    public void bindDataToMap(String property, Node node){
        if (node instanceof NumberTextField){
            NumberTextField field = (NumberTextField) node;
            field.textProperty().addListener((obs,old,newVal)->{
                propertiesMap.put(property, new VoogaNumber(Double.parseDouble(newVal)));
            });
        }
        if (node instanceof SwitchButton) {
        	SwitchButton field = (SwitchButton) node;
        	System.out.println(field.booleanProperty().toString());
        	field.booleanProperty().addListener((obs,old,newVal)->{
        		propertiesMap.put(property, new VoogaBoolean(newVal));
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
		for (String property:myElementable.getVoogaProperties().keySet()){
	        System.out.println(property+" "+myElementable.getVoogaProperties().get(property).toString());
	    }
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
		for (String property:myElementable.getVoogaProperties().keySet()){
	        System.out.println(property+" "+myElementable.getVoogaProperties().get(property).toString());
	    }
		updateProperties();
	}

}
