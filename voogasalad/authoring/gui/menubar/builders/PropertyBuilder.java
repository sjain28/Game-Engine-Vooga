package authoring.gui.menubar.builders;

import authoring.interfaces.model.EditElementable;
import javafx.scene.Node;
import javafx.scene.control.TextField;
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

    public PropertyBuilder () {
        this(null);
    }

    public PropertyBuilder (EditElementable editor) {
        super(editor);
        this.variableName = new TextField();
        this.variableValue = new TextField();
        populate();
        load(this.container);
    }

    private void populate () {
        this.container = new VBox();
        container.getChildren().addAll(makeInfo("Property Name:", "Enter a name...", variableName),
                                       makeInfo("Value:", "Enter a value...", variableValue),
                                       makeButtons());
    }

    public String getName () {
        return this.variableName.getText();
    }

    public String getValue () {
        return this.variableValue.getText();
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
