package authoring.gui;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import authoring.CustomText;
import authoring.gui.menubar.builders.GameObjectBuilder;
import authoring.interfaces.AuthoringElementable;
import authoring.interfaces.model.CompleteAuthoringModelable;
import authoring.model.ElementSelectionModel;
import authoring.resourceutility.ResourceDecipherer;
import authoring.resourceutility.VoogaFile;
import authoring.resourceutility.VoogaFileFormat;
import authoring.resourceutility.VoogaFileType;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.control.Tab;
import javafx.scene.control.ToolBar;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import resources.VoogaBundles;
import tools.NodeZAxisComparator;
import tools.VoogaAlert;
import tools.VoogaException;


/**
 * This class handles the display of all objects on the Authoring Environment
 * GUI. This is the board on which the author can build the game.
 * 
 * @author Aditya Srinivasan, Arjun Desai
 *
 */
public class DesignBoard extends Tab implements Observer {

    private VBox container;
    private ToolBar zoomBar;
    private ScrollPane scroller;
    private StackPane contentPane;
    private double width;
    private double height;

    private CompleteAuthoringModelable elementManager;
    private ElementSelectionModel selectionModel;

    private ResourceBundle designboardProperties;

    private double y_offset, x_offset;

    private double RESIZE_FACTOR;
    private int INT_RESIZE_FACTOR;
    private double SLIDER_MIN;
    private double SLIDER_MAX;
    private double SLIDER_INCREMENT;

    /**
     * Constructs DesignBoard with object that has the functionality described
     * by CompleteAuthoringModelable interface
     * 
     * @param elem:
     *        functionality described by CompleteAuthoringModelable
     *        interface
     */
    public DesignBoard (CompleteAuthoringModelable elem) {
        this.elementManager = elem;
        this.designboardProperties = VoogaBundles.designboardProperties;

        this.width = Double.parseDouble(designboardProperties.getString("Width"));
        this.height = Double.parseDouble(designboardProperties.getString("Height"));

        RESIZE_FACTOR = Double.parseDouble(designboardProperties.getString("ResizeFactor"));
        INT_RESIZE_FACTOR = Integer.parseInt(designboardProperties.getString("intResizeFactor"));
        SLIDER_MIN = Double.parseDouble(designboardProperties.getString("SliderMin"));
        SLIDER_MAX = Double.parseDouble(designboardProperties.getString("SliderMax"));
        SLIDER_INCREMENT = Double.parseDouble(designboardProperties.getString("SliderIncrement"));

        initializeContainers();
        initializeZoom();
        initializeObservables();

        initializeDragAndDrop();

        addGuides();
        displayElements(elem.getElements());
    }

    /**
     * Initializes the container which contains all the contents of the design board.
     */
    private void initializeContainers () {
        this.setText(designboardProperties.getString("DesignBoardName"));
        this.setClosable(false);

        contentPane = new StackPane();
        contentPane.setMinSize(width, height);
        scroller = new ScrollPane();
        scroller.setContent(contentPane);

        zoomBar = new ToolBar();

        container = new VBox(zoomBar, scroller);
        this.setContent(container);

        y_offset = width * RESIZE_FACTOR;
        x_offset = height * RESIZE_FACTOR;
    }

    /**
     * Initializes the zoom slider which affects the magnification of the authoring environment.
     */
    private void initializeZoom () {
        Slider zoomControl = new Slider(SLIDER_MIN, SLIDER_MAX, SLIDER_INCREMENT);
        Text coordinateDisplay = new CustomText("");
        contentPane.setOnMouseMoved(e -> {
            String xCoordinate =
                    new BigDecimal(e.getX() - width * RESIZE_FACTOR)
                            .setScale(INT_RESIZE_FACTOR, RoundingMode.HALF_UP)
                            .toString();
            String yCoordinate =
                    new BigDecimal(e.getY() - height * RESIZE_FACTOR)
                            .setScale(INT_RESIZE_FACTOR, RoundingMode.HALF_UP)
                            .toString();
            coordinateDisplay.setText(String.format("X: %s   Y: %s", xCoordinate, yCoordinate));
        });
        zoomBar.getItems().addAll(zoomControl, coordinateDisplay);
        zoomControl.valueProperty().addListener( (obs, old, n) -> {
            contentPane.setScaleX((double) n);
            contentPane.setScaleY((double) n);
        });
    }

    /**
     * Initializes the observables connected to this observer class.
     */
    private void initializeObservables () {
        selectionModel = ElementSelectionModel.getInstance();
        selectionModel.addObserver(this);
        elementManager.addObserver(this);
    }

    /**
     * Adds Guide rectangle on the design board so the user can see the dimensions of the game
     * window as the user authors in the environment.
     */
    private void addGuides () {
        double width = Double.parseDouble(VoogaBundles.preferences.getProperty("GameWidth"));
        double height = Double.parseDouble(VoogaBundles.preferences.getProperty("GameHeight"));
        Rectangle guide = new Rectangle(width, height);
        guide.setStroke(Paint.valueOf(designboardProperties.getString("RecStrokeColor")));
        guide.setStrokeWidth(Integer.parseInt(designboardProperties.getString("StrokeWidth")));
        guide.setFill(Paint.valueOf(designboardProperties.getString("RecFill")));
        this.contentPane.getChildren().add(guide);
        guide.setTranslateX(width * RESIZE_FACTOR);
        guide.setTranslateY(height * RESIZE_FACTOR);
        this.scroller
                .setVvalue(Double.parseDouble(designboardProperties.getString("ScrollerVValue")));
        this.scroller
                .setHvalue(Double.parseDouble(designboardProperties.getString("ScrollerHValue")));
    }

    /**
     * Initializes the drag and drop feature for the design board.
     */
    private void initializeDragAndDrop () {
        contentPane.setOnDragOver(e -> mouseDragOver(e));
        contentPane.setOnDragDropped(e -> mouseDragDropped(e));
        contentPane.setOnDragExited(e -> contentPane
                .setStyle(designboardProperties.getString("ContentPaneStyle")));
    }

    private void mouseDragDropped (final DragEvent event) {
        Dragboard db = event.getDragboard();
        boolean success = false;
        if (db.hasContent(VoogaFileFormat.getInstance())) {
            VoogaFile node = (VoogaFile) db.getContent(VoogaFileFormat.getInstance());
            if (node.getType() != VoogaFileType.FOLDER) {
                if (node.getType() != VoogaFileType.ARCHETYPE &&
                    node.getType() != VoogaFileType.GAME_OBJECT) {
                    if (elementManager.hasElement(node.getPath())) {
                        moveElement(node.getPath(), event);
                    }
                    else {
                        addElement(node, event, "");
                    }
                }
                else {
                    if (node.getType() == VoogaFileType.ARCHETYPE) {
                        addElement(node, event, node.toString());
                    }
                }
            }
            success = true;
        }

        if (db.hasString()) {
            Node object = (Node) elementManager.getElement(db.getString());
            object.setTranslateX(event.getX() - x_offset);
            object.setTranslateY(event.getY() - y_offset);
        }

        event.setDropCompleted(success);
    }

    /**
     * Mouse dragover event.
     * 
     * @param event
     */
    private void mouseDragOver (final DragEvent event) {
        if (event.getGestureSource() != contentPane &&
            (event.getDragboard().hasContent(VoogaFileFormat.getInstance()))) {
            VoogaFile content =
                    (VoogaFile) event.getDragboard().getContent(VoogaFileFormat.getInstance());
            String color = "";
            if (content.getType() != VoogaFileType.FOLDER) {
                color = designboardProperties.getString("NonVoogaFileColor");
            }
            else {
                color = designboardProperties.getString("VoogaFileColor");
            }
            contentPane.setStyle(String
                    .format(designboardProperties.getString("ContentPaneStringStyle"), color));
            event.acceptTransferModes(TransferMode.ANY);
        }
        else if (event.getDragboard().hasString()) {
            event.acceptTransferModes(TransferMode.ANY);
        }

        event.consume();
    }

    /**
     * Method to add new element to the design board
     * 
     * @param file: file to add
     * @param event: drag and drop feature
     * @param archetype: archetype (if element has one)
     */
    private void addElement (VoogaFile file, DragEvent event, String archetype) {
        String elementPath = file.getPath();
        if (elementPath != null) {
            try {
                if (ResourceDecipherer.isImage(elementPath)) {

                    GameObjectBuilder builder = new GameObjectBuilder(elementManager);
                    if (!archetype.isEmpty()) {
                        builder.setArchetype(archetype);
                    }
                    else {
                        builder.setDraggedImage(file.getPath());
                    }
                    builder.showAndWait();

                }
            }
            catch (VoogaException e) {
                VoogaAlert alert = new VoogaAlert(e.getMessage());
                alert.showAndWait();
            }
            elementManager.addElementId(elementPath);
        }
    }

    /**
     * Method to move the element around on the design board.
     * 
     * @param id: sprite ID
     * @param e: dragevent
     */
    private void moveElement (String id, DragEvent e) {
        Node element = elementManager.getElement(id);
        element.setTranslateX(e.getX() - x_offset);
        element.setTranslateY(e.getY() - y_offset);
    }

    /**
     * Displays the collection of the elements on the content pane
     * 
     * @param nodeList: list of nodes
     */
    private void displayElements (Collection<Node> nodeList) {
        contentPane.getChildren().clear();
        addGuides();
        contentPane.getChildren().addAll(nodeList);
    }

    /**
     * Updates changes to the class based on the observation from the Model,
     * Specifically the ElementManager
     */
    @Override
    public void update (Observable o, Object arg) {
        if ((o instanceof CompleteAuthoringModelable) && (arg instanceof List)) {
            displayElements(((CompleteAuthoringModelable) o).getElements());
        }

        if ((o instanceof ElementSelectionModel) && (arg instanceof AuthoringElementable)) {
            for (Node e : contentPane.getChildren()) {
                System.out.println(e);
            }
            List<Node> newChildren = new ArrayList<>(contentPane.getChildren());
            newChildren.sort(new NodeZAxisComparator());
            contentPane.getChildren().clear();
            contentPane.getChildren().addAll(newChildren);
        }
    }

}
