package authoring.gui.eventpane;

import authoring.gui.items.SpriteComboBox;
import authoring.interfaces.model.EditEventable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import resources.VoogaBundles;
import tools.VoogaException;

public class CollisionCauseGUI implements EventGUI{
    private SpriteComboBox group1;
    private SpriteComboBox group2;
    private ComboBox<String> collisionType;
    
    public CollisionCauseGUI(EditEventable manager){
        group1=new SpriteComboBox(manager);
        group2= new SpriteComboBox(manager);
        collisionType = new ComboBox<>();
        collisionType.getItems().addAll("Horizontal","Above", "Below");
    }

    @Override
    public Node display () {
        VBox vbox = new VBox();
        vbox.getChildren().add(collisionType);
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(50,50,50,50));
        hbox.setAlignment(Pos.CENTER);
        hbox.getChildren().addAll(group1,group2);
        vbox.getChildren().add(hbox);
        return vbox;
    }

    @Override
    public String getDetails () throws VoogaException {
        
        return "events.CollisionCause,"+group1.getSpriteId()+","+group2.getSpriteId()+","+VoogaBundles.EventMethods.getString(collisionType.getValue());
    }
}
//
//
//package authoring.gui.eventpane;
//
//import authoring.gui.items.ArchetypeSpriteCombo;
//import authoring.gui.items.SpriteComboBox;
//import authoring.interfaces.model.EditEventable;
//import javafx.geometry.Insets;
//import javafx.geometry.Pos;
//import javafx.scene.Node;
//import javafx.scene.control.ComboBox;
//import javafx.scene.layout.HBox;
//import javafx.scene.layout.VBox;
//import resources.VoogaBundles;
//import tools.GUIUtils;
//import tools.VoogaException;
//
//public class CollisionCauseGUI implements EventGUI{
//    private ArchetypeSpriteCombo group1;
//    private ArchetypeSpriteCombo group2;
//    private ComboBox<String> collisionType;
//    private VBox parent;
//    private VBox vbox1;
//    private VBox vbox2;
//    private HBox hbox;
//    
//    public CollisionCauseGUI(EditEventable manager){
//        parent = new VBox();
//        
//        vbox1 = new VBox();
//        vbox2 = new VBox();
//        
//        hbox = GUIUtils.makeRow(vbox1,vbox2);
//        
//        
//        group1=new ArchetypeSpriteCombo(manager,vbox1,e->{},false);
//        group2= new ArchetypeSpriteCombo(manager,vbox2,e->{},false);
//        
//        collisionType = new ComboBox<String>();
//        collisionType.getItems().addAll("Horizontal","Above", "Below");
//        parent.getChildren().addAll(collisionType,hbox);
//    }
//
//    @Override
//    public Node display () {
//        return parent;
//    }
//
//    @Override
//    public String getDetails () throws VoogaException {
//        
//        return "events.CollisionCause,"+group1.getDetails()+","+group2.getDetails()+","+VoogaBundles.EventMethods.getString(collisionType.getValue());
//    }
//}

