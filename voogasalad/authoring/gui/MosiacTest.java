package authoring.gui;


import java.net.URL;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Random;

import ai.cogmission.mosaic.ModelLoader;
import ai.cogmission.mosaic.refimpl.javafx.MosaicPane;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
/**
 * 
 * This class was used as an example for the potential Pane implementation of Mosaic, a dynamic resizing environment. Not actually
 * written by Nick, instead copies from the github mosaic page.
 * 
 * @author Nick
 *
 */
public class MosiacTest extends Application {
        /** Mapping of element id's to labels for later reference when serializing */
        private java.util.Map<String, Label> clientMap = new java.util.HashMap<>();
        /** Holds colors to use for component illustration */
        private String[] colors = new String[] { "blue", "red", "green", "yellow", "orange" };
        /** Used to randomize ui element colors */
        private Random random = new Random();

        @Override
        public void start(Stage stage) throws Exception {
                Application.Parameters params = getParameters();
                Map<String, String> map = params.getNamed();
                System.out.println("params = " + params);
                
                ModelLoader loader = new ModelLoader(map.get("file"));
                String[] model = loader.getModel(map.get("surface"));
                
                System.out.println("model.length = " + model.length);
                MosaicPane<Node> mosaicPane = new MosaicPane<Node>();
                
                int i = 0;
                for(String def : model) {
                        String[] args = def.split("[\\s]*\\,[\\s]*");
                        int offset = args.length > 4 ? args.length - 4 : 0;
                        String id = args.length == 4 ? "" + (i++) : args[0];
                        Label l = getLabel(i > 4 ? colors[random.nextInt(5)] : colors[i], id);
                        mosaicPane.add(l, id, 
                                Double.parseDouble(args[offset + 0]), 
                                Double.parseDouble(args[offset + 1]),
                                Double.parseDouble(args[offset + 2]),
                                Double.parseDouble(args[offset + 3]));
                        clientMap.put(id, l);
                }
                
        mosaicPane.getEngine().addSurface(mosaicPane.getSurface());
        
        Scene scene = new Scene(mosaicPane, 1600, 900);
            stage.setTitle("Mosaic Layout Engine Demo (JavaFX)");
                stage.setScene(scene);
                stage.show();
        }
        
        public void addNewWindowTrigger(MosaicPane<Node> mp) {
//              final Surface<Node> surface = mp.getSurface();
//              final MosaicEngine<Node> engine = mp.getEngine();
                
                
        }
        
        public Label getLabel(String color, String id) {
                Label label = new Label();
                label.textProperty().set(id);
                label.textAlignmentProperty().set(TextAlignment.CENTER);
                label.alignmentProperty().set(Pos.CENTER);
                label.setOpacity(1.0);
                label.setTextFill(Color.WHITE);
                label.setFont(Font.font("Arial", FontWeight.BOLD, 16d));
                label.setStyle("-fx-background-color: " + color.toString() + 
                        ";-fx-alignment:center;-fx-text-alignment:center;");
                label.setManaged(false);
                
                return label;
        }
        
        public static void main(String[] args) {
                if(args == null || args.length < 1 || args[0] == null) {
                    URL url = MosiacTest.class.getResource("testModel.txt");
                    String path = Paths.get(url.toExternalForm()).toAbsolutePath().toString();
                    try{
                        path = Paths.get(url.toURI()).toAbsolutePath().toString();
                    }catch(Exception e) { e.printStackTrace(); }
                    
                        args = new String[] { "--file="+path, "--surface=test"};
                }
                System.out.println(System.getProperty("user.dir"));
        launch(args);
    }
}
