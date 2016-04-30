package authoring.model;

import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

import authoring.gui.Selector;
import authoring.interfaces.AuthoringElementable;
import authoring.interfaces.Elementable;
import gameengine.BackEndText;
import javafx.beans.binding.Bindings;
import javafx.scene.Node;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.Glow;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.text.Text;
import tools.VoogaException;
import tools.VoogaNumber;
import tools.VoogaString;
import tools.bindings.TextProperties;
import tools.interfaces.VoogaData;


public class VoogaFrontEndText extends Text implements AuthoringElementable {

    private Map<String, VoogaData> propertiesMap;
    private BackEndText backEndText;

    public VoogaFrontEndText () {
        setId(UUID.randomUUID().toString());
        backEndText = new BackEndText(getId());
        
        create();
        initializeMap();
        this.setOpacity(1);

    }

    public VoogaFrontEndText (BackEndText backtext) throws VoogaException {
        this.backEndText = backtext;
        this.setId(backtext.getId());
        create();
        initializeMap();
        this.setOpacity(1);

        TextProperties tp = new TextProperties();
        Map<String, Object> properties = tp.storeData((Text) backtext.getNodeObject());
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

        this.textProperty().addListener( (o, oldVal, newVal) -> {
            System.out.println(newVal);
            backEndText.setName(newVal);
        });

        this.setOnDragDetected((MouseEvent e) -> onDrag(e));
        this.setOnMouseClicked(e -> ElementSelectionModel.getInstance().setSelected(this));
    }

    private void initializeMap () {
        propertiesMap = new TreeMap<String, VoogaData>();

        propertiesMap.put("Name", new VoogaString());
        propertiesMap.put("X", new VoogaNumber());
        propertiesMap.put("Y", new VoogaNumber());
        propertiesMap.put("Z", new VoogaNumber());
        propertiesMap.put("Opacity", new VoogaNumber());
        propertiesMap.put("Style", new VoogaString());
        propertiesMap.putAll(backEndText.getVoogaProperties());
        
        Bindings.bindBidirectional(this.textProperty(), propertiesMap.get("Name").getProperty());
        Bindings.bindBidirectional(this.translateXProperty(), propertiesMap.get("X").getProperty());
        Bindings.bindBidirectional(this.translateYProperty(), propertiesMap.get("Y").getProperty());
        Bindings.bindBidirectional(this.translateZProperty(), propertiesMap.get("Z").getProperty());
        Bindings.bindBidirectional(this.opacityProperty(),
                                   propertiesMap.get("Opacity").getProperty());
        Bindings.bindBidirectional(this.styleProperty(), propertiesMap.get("Style").getProperty());
        
        this.setStyle("-fx-fill: red;");
    }

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

    public void setVoogaData (VoogaData data) {
        backEndText.setDisplayedData(data);
    }

    @Override
    public void setVoogaProperties (Map<String, VoogaData> newVoogaProperties) {
        backEndText.setVoogaProperties(newVoogaProperties);
    }

    @Override
    public void addProperty (String name, VoogaData data) {
        backEndText.addProperty(name, data);
    }

    @Override
    public void removeProperty (String name) {
        backEndText.removeProperty(name);
    }

    @Override
    public Node getNodeObject () {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void init () throws VoogaException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void update () {
        // TODO Auto-generated method stub
        
    }
    
}
