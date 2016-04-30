package authoring.gui.items;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import authoring.interfaces.model.EditEventable;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class ArchetypeSpriteCombo extends Observable {
    private SpriteComboBox spriteComboBox;
    private ComboBox needsSprites;
    private HBox hbox;
    private VBox overallRegion;
    private boolean effect;
    
    public ArchetypeSpriteCombo (EditEventable manager, VBox overallRegion, EventHandler e, boolean effect) {
        this.overallRegion=overallRegion;
        this.effect=effect;
        needsSprites = new ComboBox();
        needsSprites.setPromptText("Get sprites from cause");
        needsSprites.getItems().addAll("true","false");
        
        hbox = new HBox(15);
        ToggleGroup tg = createToggleGroup(hbox,"Sprite","Archetype");
        
        
        tg.selectedToggleProperty().addListener((obs,old,n)->{
            clearAfterIndex(overallRegion);
            overallRegion.getChildren().addAll(hbox);
            if (((RadioButton) n).getText().equals("Sprite")){
                spriteComboBox = new SpriteComboBox(manager);
            }
            if (((RadioButton) n).getText().equals("Archetype")){
                if (effect){
                    overallRegion.getChildren().add(needsSprites);
                }
                spriteComboBox = new ArchetypeComboBox(manager);
            }
            spriteComboBox.setOnAction(e);
            
            overallRegion.getChildren().addAll(spriteComboBox);
        });

    }
    
    private void clearAfterIndex(VBox region){
        int index =region.getChildren().indexOf(hbox);
        List<Node> nodes = new ArrayList<Node>();
        
        for (int i = index;i<region.getChildren().size();i++){
            nodes.add(region.getChildren().get(i));
        }
        
        region.getChildren().removeAll(nodes);
    }
    
    private ToggleGroup createToggleGroup (HBox hbox, String... names) {
        ToggleGroup tg = new ToggleGroup();
        for (String name : names){
            RadioButton rb = new RadioButton(name);
            rb.setToggleGroup(tg);
            hbox.getChildren().add(rb);
        }
        return tg;
    }
    
    public SpriteComboBox getSpriteComboBox(){
        return spriteComboBox;
    }
    
    public void display(){
        overallRegion.getChildren().add(hbox);
    }
    
    public String getDetails(){
        String result ="";
        if (spriteComboBox instanceof ArchetypeComboBox && effect){
            result=needsSprites.getValue()+",";
        }
        result+=spriteComboBox.getSpriteId();
        return result;
    }
    
}
