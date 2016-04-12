package authoring.gui;

import java.io.File;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import authoring.interfaces.model.CompleteAuthoringModelable;
import authoring.interfaces.Elementable;
import authoring.properties.PropertiesTabManager;
import authoring.resourceutility.ResourceDecipherer;
import authoring.resourceutility.VoogaFile;
import authoring.resourceutility.VoogaFileFormat;
import authoring.resourceutility.VoogaFileType;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;


public class DesignBoard extends Tab implements Observer{

    private static final String DESIGN_BOARD = "Design Board";
    private static final double HEIGHT = 1000;
    private static final double WIDTH = 1000;

    private ScrollPane container;
    private StackPane contentPane;
    private Node node;

    private CompleteAuthoringModelable elementManager;

    private PropertiesTabManager propertiesTabManager;

    private double y_offset, x_offset;

    public DesignBoard (CompleteAuthoringModelable elem) {
        this.setText(DESIGN_BOARD);
        contentPane = new StackPane();
        contentPane.setMinSize(WIDTH, HEIGHT);
        elementManager = elem;
        elementManager.addObserver(this);
        propertiesTabManager = new PropertiesTabManager();
        initGlobalProperties();
        container = new ScrollPane();
        initializeDragAndDrop();
        container.setContent(contentPane);
        this.setContent(container);
        y_offset = HEIGHT / 2;
        x_offset = WIDTH / 2;
    }

    public PropertiesTabManager getPropertiesTabManager () {
        return propertiesTabManager;
    }

    private void initGlobalProperties () {
        // Elementable elem = elementManager.getGlobalPropertiesManager();
        // propertiesTabManager.getGlobalPropertiesTab().getPropertiesMap(elem);
    }

    // Do something with Elementable.setOnClicked and call this method on self,
    // or put into sprite class?
    
    private void displaySpriteProperties (Elementable elem) {
        propertiesTabManager.getSpritePropertiesTab().getPropertiesMap(elem);
    }

    private void initializeDragAndDrop () {
        contentPane.setOnDragOver(e -> mouseDragOver(e));
        contentPane.setOnDragDropped(e -> mouseDragDropped(e));
        contentPane.setOnDragExited(e -> contentPane.setStyle("-fx-border-color: transparent;"));
    }

    private void mouseDragDropped (final DragEvent event) {
        Dragboard db = event.getDragboard();
        boolean success = false;
        if (db.hasContent(VoogaFileFormat.getInstance())) {
            VoogaFile node = (VoogaFile) db.getContent(VoogaFileFormat.getInstance());
            if (node.getType() != VoogaFileType.FOLDER) {
                if (elementManager.hasElement(node.getPath())) {
                    moveElement(node.getPath(), event);
                }
                else {
                    addElement(node, event);
                }
                success = true;
            }
        }

        event.setDropCompleted(success);
    }

    private void mouseDragOver (final DragEvent event) {
        if (event.getGestureSource() != contentPane &&
            event.getDragboard().hasContent(VoogaFileFormat.getInstance())) {
            VoogaFile content =
                    (VoogaFile) event.getDragboard().getContent(VoogaFileFormat.getInstance());
            String color = "";
            if (content.getPath() != null) {
                color = "#64B5F6";
            }
            else {
                color = "red";
            }
            contentPane.setStyle(String.format("-fx-border-color: %s", color));
            event.acceptTransferModes(TransferMode.ANY);
        }
        event.consume();
    }

    private void addElement (VoogaFile file, DragEvent event) {
        node = null;
        String elementPath = file.getPath();
        if (elementPath != null) {
            if (ResourceDecipherer.isImage(elementPath)) {
                // node = new
                // GameObject(elementManager.getSpriteFactory().createSprite(""));
                node = new ImageView(new Image(new File(elementPath).toURI().toString()));
                node.setId(elementPath);
                node.setTranslateX(event.getX() - x_offset);
                node.setTranslateY(event.getY() - y_offset);
                node.setOnDragDetected(e -> {
                    if (node != null) {
                        Dragboard db = node.startDragAndDrop(TransferMode.MOVE);
                        ClipboardContent cc = new ClipboardContent();
                        cc.put(VoogaFileFormat.getInstance(), file);
                        db.setContent(cc);
                    }
                    e.consume();
                });
            }
            else if (ResourceDecipherer.isAudio(elementPath)) {
                // node = new
                // GameObject(elementManager.getSpriteFactory().createSprite(""));
                node =
                        new MediaView(new MediaPlayer(new Media(Paths.get(elementPath).toUri()
                                .toString())));
            }
            addElement(node, elementPath);
        }

        System.out.println(elementManager.getIds());

    }

    public void addElement (Node node, String id) {
        elementManager.addGameElements(node);
        elementManager.addElementId(id);
        contentPane.getChildren().add(node);
    }

    private void moveElement (String id, DragEvent e) {
        Node element = elementManager.getElement(id);
        System.out.println("" + e.getX() + " " + e.getY());
        element.setTranslateX(e.getX() - x_offset);
        element.setTranslateY(e.getY() - y_offset);

    }
    
    private void displayElements(Collection<Node> nodeList){
        for (Node node : nodeList){
            if (!contentPane.getChildren().contains(node)){
                contentPane.getChildren().add(node);
            }
        }
    }
    @Override
    public void update (Observable o, Object arg) {
        if ((o instanceof CompleteAuthoringModelable) && (arg instanceof List)){
            displayElements(((CompleteAuthoringModelable) o).getElements());
        }
    }

}
