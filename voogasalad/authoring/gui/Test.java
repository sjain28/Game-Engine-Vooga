package authoring.gui;

/**
 * Test class
 */

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.NumberBinding;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Test extends Application {

	  private Rectangle lastOne;

	  private static final int TEST_SCENE_SIZE = 500;
	  private static final int TEST_GRID_LENGTH = 7;
	  
	  public void start(Stage stage) throws Exception {
	    Pane root = new Pane();

	    int grid_x = TEST_GRID_LENGTH; //number of rows
	    int grid_y = TEST_GRID_LENGTH; //number of columns

	    // this binding will find out which parameter is smaller: height or width
	    NumberBinding rectsAreaSize = Bindings.min(root.heightProperty(), root.widthProperty());

	    for (int x = 0; x < grid_x; x++) {
	        for (int y = 0; y < grid_y; y++) {
	            Rectangle rectangle = new Rectangle();
	            rectangle.setStroke(Color.WHITE);

	            rectangle.setOnMouseClicked(new EventHandler<MouseEvent>() {
	                @Override
	                public void handle(MouseEvent t) {
	                    if (lastOne != null) {
	                        lastOne.setFill(Color.BLACK);
	                    }
	                    // remembering clicks
	                    lastOne = (Rectangle) t.getSource();
	                    // updating fill
	                    lastOne.setFill(Color.RED);
	                }
	            });

	            // here we position rects (this depends on pane size as well)
	            rectangle.xProperty().bind(rectsAreaSize.multiply(x).divide(grid_x));
	            rectangle.yProperty().bind(rectsAreaSize.multiply(y).divide(grid_y));

	            // here we bind rectangle size to pane size 
	            rectangle.heightProperty().bind(rectsAreaSize.divide(grid_x));
	            rectangle.widthProperty().bind(rectangle.heightProperty());

	            root.getChildren().add(rectangle);
	        }
	    }
	    Scene scene = new Scene(root, TEST_SCENE_SIZE, TEST_SCENE_SIZE);
	    stage.setScene(scene);
	    stage.show();
	  }

	  public static void main(String[] args) { launch(); }
	}
