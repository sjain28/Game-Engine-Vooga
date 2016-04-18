package authoring.gui.menubar.builders;

import authoring.CustomText;
import authoring.gui.items.SwitchButton;
import authoring.interfaces.model.EditElementable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import tools.VoogaNumber;
import tools.interfaces.VoogaData;

public class PropertyBuilder extends Builder {
   
	private VBox container;
	private PropertiesTable pTable;
	private TextField variableName;
	private TextField variableValue;
	private RadioButton number;
	private HBox field;
	
	public PropertyBuilder() {
		this(null);
	}
	
    public PropertyBuilder(EditElementable editor) {
        super(editor);
        this.variableName = new TextField();
        this.variableValue = new TextField();
        populate();
        load(this.container);
    }
    
    private void populate() {
    	this.container = new VBox();
    	this.container.setSpacing(SPACING);
    	this.container.setPadding(new Insets(SPACING));
    	container.getChildren().addAll(makeDataSelector(),
    								   makeInfo("Property Name:", "Enter a name...", variableName),
    								   makeInfo("Value:", "Enter a value...", variableValue),
    								   makeButtons());
    }
    
    private HBox makeDataSelector() {
    	ToggleGroup group = new ToggleGroup();
    	RadioButton number = new RadioButton("Number");
        number.setToggleGroup(group);
        number.setSelected(true);
        RadioButton bool = new RadioButton("Boolean");
        bool.setToggleGroup(group);
        return makeRow(number, bool);
    }
    
    public String getName() {
    	return this.variableName.getText();
    }
    
    public String getValue() {
    	return this.variableValue.getText();
    }

	@Override
    public void compile () {
        try{
        	
        }
       catch(Exception e) {
           numberError("Please input a valid number");
       }
       quit();
    }

}
