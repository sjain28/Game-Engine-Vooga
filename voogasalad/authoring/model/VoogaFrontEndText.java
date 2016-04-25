package authoring.model;

import java.awt.Font;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;
import authoring.gui.Selector;
import authoring.interfaces.Elementable;
import authoring.interfaces.FrontEndElementable;
import gameengine.BackEndText;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.Glow;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import tools.VoogaException;
import tools.VoogaNumber;
import tools.VoogaString;
import tools.interfaces.VoogaData;


public class VoogaFrontEndText extends TextField implements FrontEndElementable {
    private BackEndText text;
    
    private Map<String,VoogaData> propertiesMap;
    private VoogaData data;
    
    public VoogaFrontEndText (String name) {
        createData();
        initializePropertiesMap();
        
        
        this.setText(name);
        text = new BackEndText(this.getId());
        setName(name);
        
        this.setTranslateX(0);
        this.setTranslateY(0);
        this.setPrefSize(100, 100);
 
    }
    
    public VoogaFrontEndText(String name, VoogaData displayedData){
        this(name);
        data = displayedData;
        text.setDisplayedData(displayedData);
    }
    
    private void initializePropertiesMap(){
        propertiesMap= new TreeMap<String,VoogaData>();
        
        propertiesMap.put("Width", new VoogaNumber());
        propertiesMap.put("Height", new VoogaNumber());
        propertiesMap.put("X", new VoogaNumber());
        propertiesMap.put("Y",new VoogaNumber());
        propertiesMap.put("Opacity", new VoogaNumber());
        propertiesMap.put("Style", new VoogaString());

        Bindings.bindBidirectional(this.prefWidthProperty(), propertiesMap.get("Width").getProperty());
        Bindings.bindBidirectional(this.prefHeightProperty(), propertiesMap.get("Height").getProperty());
        Bindings.bindBidirectional(this.translateXProperty(), propertiesMap.get("X").getProperty());
        Bindings.bindBidirectional(this.translateYProperty(), propertiesMap.get("Y").getProperty());
        Bindings.bindBidirectional(this.opacityProperty(), propertiesMap.get("Opacity").getProperty());
        Bindings.bindBidirectional(this.styleProperty(), propertiesMap.get("Style").getProperty());
        
       
    }
    
    private void createData(){
        setId(UUID.randomUUID().toString());
        setBackground(new Background (new BackgroundFill(Color.BLUE, new CornerRadii(3),new Insets(10,10,10,10))));
        this.setVisible(true);
        //Bindings.bindBidirectional(this.data.getProperty(), text.getDisplayedData().getProperty());
        this.setOnDragDetected( (MouseEvent e) -> onDrag(e));
    }

    @Override
    public Map<String, VoogaData> getVoogaProperties () {
        return propertiesMap;
    }

    @Override
    public Node getNodeObject () {
        return this;
    }

    public String getName () {
        return text.getName();
    }

    public void select (Selector selector) {
        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setBrightness(selector.getLightness());

        this.setEffect(colorAdjust);
        this.setEffect(new Glow(selector.getGlow()));
    }

    @Override
    public void setName (String name) {
        text.setName(name);
    }
    
    private void onDrag (MouseEvent event) {
        ElementSelectionModel.getInstance().setSelected(this);
        Dragboard db = this.startDragAndDrop(TransferMode.ANY);
        ClipboardContent content = new ClipboardContent();
        content.putString(getId());
        db.setContent(content);
        event.consume();
    }
    
    @Override
    public Elementable getElementable () {
        return text;
    }
    
    
    public void setDisplayedData(VoogaData data){
        this.data = data;
        text.setDisplayedData(data);
    }
    
    @Override
    public void addProperty (String name, VoogaData data) {
        
    }

    @Override
    public void removeProperty (String name) {
        
    }
    
    public void setProperties (Map<String, VoogaData> map) {
        
    }

    @Override
    public void setVoogaProperties (Map<String, VoogaData> newVoogaProperties) {
        
    }
    
    @Override
    public void init () throws VoogaException {
        // TODO Auto-generated method stub

    }
    
    @Override
    public void update () {

    }


}
