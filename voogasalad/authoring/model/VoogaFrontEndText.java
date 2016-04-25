package authoring.model;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;
import authoring.gui.Selector;
import authoring.interfaces.Elementable;
import authoring.interfaces.FrontEndElementable;
import gameengine.BackEndText;
import javafx.beans.binding.Bindings;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.Glow;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Background;
import tools.VoogaException;
import tools.VoogaNumber;
import tools.VoogaString;
import tools.bindings.TextProperties;
import tools.interfaces.VoogaData;


public class VoogaFrontEndText extends TextField implements FrontEndElementable {

    private Map<String, VoogaData> propertiesMap = new HashMap<String, VoogaData>();
    private BackEndText backEndText;

    public VoogaFrontEndText () {
        create();
        
        initializeMap();
        this.setOpacity(1);

    }
    
    public VoogaFrontEndText(BackEndText backtext) throws VoogaException{
        this();
        TextProperties tp = new TextProperties();
        Map<String, Object> properties = tp.storeData(backtext.getNodeObject());
        tp.loadData(this, properties);
    }
    
    // stroke, color, font, text, size, name/group, position (x,y,z),
    public VoogaFrontEndText (double x, double y, String text) {
        this();
        this.setTranslateX(x);
        this.setTranslateY(y);
        this.setText(text);
    }

    public VoogaFrontEndText (String text) {
        this(0, 0, text);
    }

    private void create () {
        setId(UUID.randomUUID().toString());
        setBackground(Background.EMPTY);
        this.setMaxSize(100, 100);
        
        backEndText = new BackEndText(getId());
        
        this.textProperty().addListener((o,oldVal,newVal)->{
            backEndText.setName(newVal);
        });
        
        this.setOnDragDetected( (MouseEvent e) -> onDrag(e));
        this.setOnMouseClicked(e -> ElementSelectionModel.getInstance().setSelected(this));

    }

    public void init () throws VoogaException {

    }

    private void initializeMap () {
        propertiesMap = new TreeMap<String, VoogaData>();

        propertiesMap.put("Width", new VoogaNumber());
        propertiesMap.put("Height", new VoogaNumber());
        propertiesMap.put("X", new VoogaNumber());
        propertiesMap.put("Y", new VoogaNumber());
        propertiesMap.put("Opacity", new VoogaNumber());
        propertiesMap.put("Style", new VoogaString());

        Bindings.bindBidirectional(this.maxWidthProperty(),
                                   propertiesMap.get("Width").getProperty());
        Bindings.bindBidirectional(this.maxHeightProperty(),
                                   propertiesMap.get("Height").getProperty());
        Bindings.bindBidirectional(this.translateXProperty(), propertiesMap.get("X").getProperty());
        Bindings.bindBidirectional(this.translateYProperty(), propertiesMap.get("Y").getProperty());
        Bindings.bindBidirectional(this.opacityProperty(),
                                   propertiesMap.get("Opacity").getProperty());
        Bindings.bindBidirectional(this.styleProperty(), propertiesMap.get("Style").getProperty());
    }
    // TODO:
    // This method is repeated in all Elements, we should use some form of inheritance
    // hierarchy to determine this

    void onDrag (MouseEvent event) {
        Dragboard db = this.startDragAndDrop(TransferMode.MOVE);
        ClipboardContent content = new ClipboardContent();
        System.out.println("Picked up");
        content.putString(getId());
        db.setContent(content);
        event.consume();
    }

    @Override
    public Map<String, VoogaData> getVoogaProperties () {
        return propertiesMap;
    }

    @Override
    public Node getNodeObject () {
        // TODO Auto-generated method stub
        return this;
    }

    @Override
    public String getName () {
        return backEndText.getName();
    }

    @Override
    public Elementable getElementable () {
        return backEndText;
    }

    @Override
    public void select (Selector selector) {
        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setBrightness(selector.getLightness());

        this.setEffect(colorAdjust);
        this.setEffect(new Glow(selector.getGlow()));

    }
    
    @Override
    public void setName (String name) {
        this.backEndText.setName(name);
    }
    
    public void setBackEndText(BackEndText text){
        this.backEndText=text;
    }
    
    @Override
    public void update () {

    }
    

    @Override
    public void setVoogaProperties (Map<String, VoogaData> newVoogaProperties) {
        // TODO Auto-generated method stub

    }
    
    @Override
    public void addProperty (String name, VoogaData data) {

    }

    @Override
    public void removeProperty (String name) {
        // TODO Auto-generated method stub
    }

}
