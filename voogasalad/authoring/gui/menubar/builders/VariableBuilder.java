package authoring.gui.menubar.builders;

import authoring.interfaces.model.EditElementable;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import tools.VoogaNumber;
import tools.interfaces.VoogaData;

public class VariableBuilder extends Builder {
   
	private VBox container;
	private TextField variableName;
	private TextField variableValue;
	
    public VariableBuilder (EditElementable editor) {
        super(editor);
        this.variableName = new TextField();
        this.variableValue = new TextField();
        populate();
        
        load(this.container);
    }
    
    private void populate() {
    	this.container = new VBox();
    	container.getChildren().addAll(makeInfo("Variable Name:", "Enter a name...", variableName),
    								   makeInfo("Value:", "Enter a value...", variableValue),
    								   makeButtons());
    }

	@Override
    public void compile () {
        VoogaData value = new VoogaNumber();
        try{
            value.setValue(Double.parseDouble(variableValue.getText()));
            myManager.addGlobalVariable(variableName.getText(), value);
        }
       catch(Exception e) {
           numberError("Please input a valid number");
       }
       quit();
    }

}
