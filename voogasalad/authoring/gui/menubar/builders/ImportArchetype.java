package authoring.gui.menubar.builders;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import authoring.CustomText;
import authoring.VoogaScene;
import authoring.interfaces.model.CompleteAuthoringModelable;
import authoring.interfaces.model.EditSpritable;
import authoring.resourceutility.ButtonMaker;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import resources.VoogaBundles;
import tools.VoogaAlert;
import tools.VoogaException;


public class ImportArchetype extends Stage {
    private EditSpritable manager;
    private Scene scene;
    private BorderPane root;
    private List<String> selectedArchetypes;
    private VBox vbox;
    private HBox hbox;
    
    public ImportArchetype(){
    	ButtonMaker maker = new ButtonMaker();
        root = new BorderPane();
        scene = new VoogaScene(root);
        setSelectedArchetypes(new ArrayList<String>());
        vbox = new VBox();
        hbox = new HBox();
        
        Button apply = maker.makeButton("Apply",e->apply());
        Button cancel = maker.makeButton("Cancel",e->this.close());
        hbox.getChildren().addAll(apply,cancel);
        
        root.setBottom(hbox);
        root.setCenter(vbox);
        this.setScene(scene);
    }
    
    public ImportArchetype (EditSpritable manager) {
        this();
        
        this.manager = manager;
        addArchetypeNames(getArchetypeNames());
    }
    
    private List<String> getArchetypeNames(){
        File folder = new File("resources/saved_archetypes/");
        File[] listOfFiles = folder.listFiles();
        List<String> archetypeNames = new ArrayList<String>();
        
        for (File file : listOfFiles){
            String name = file.getName();
            int index = name.indexOf('.');
            name = name.substring(0, index);
            archetypeNames.add(name);
        }
        return archetypeNames;
    }
    
    protected void addArchetypeNames (Collection<String> archetypeNames) {
    	if(archetypeNames.size() == 0) {
    		vbox.getChildren().add(new CustomText("No archetypes available."));
    	}
        for (String name : archetypeNames) {
            addArchetypeName(name);
        }
    }

    protected void addArchetypeName (String archetypeName) {
        CheckBox checkbox = new CheckBox(archetypeName);
        checkbox.setOnAction(e -> manageSelectedArchetypes(checkbox));
        vbox.getChildren().add(checkbox);
    }

    protected void manageSelectedArchetypes (CheckBox checkbox) {
        if (checkbox.isSelected()) {
            getSelectedArchetypes().add(checkbox.getText());
        }
        else {
            if (getSelectedArchetypes().contains(checkbox.getText())) {
                getSelectedArchetypes().remove(checkbox.getText());
            }
        }
    }
    
    protected void apply(){
        try {
            for (String name : getSelectedArchetypes()){
                manager.getSpriteFactory().importArchetype(name);
            }
            this.close();
        }
        catch (VoogaException e) {
            new VoogaAlert(e.getMessage());
        }
    }
    
    protected void setManager(EditSpritable manager){
        this.manager=manager;
    }
    
    protected EditSpritable getManager(){
        return manager;
    }

    protected List<String> getSelectedArchetypes () {
        return selectedArchetypes;
    }

    protected void setSelectedArchetypes (List<String> selectedArchetypes) {
        this.selectedArchetypes = selectedArchetypes;
    }

}
