/**
 * 
 */
package Player.gamedisplay;

import java.util.ArrayList;
import java.util.List;

import data.FileReaderToGameObjects;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * GameDisplayTester method that tests and demonstrates
 * how to use classes in gamedisplay package
 * 
 * @author Heeb
 *
 */
public class GameDisplayTester extends Application {	

	static List<Node> nodes = new ArrayList<>();

	private static void populate() {

		Node r = new Rectangle(30, 30);
		r.setLayoutX(10);
		r.setLayoutY(10);

		Node r2 = new Rectangle(30, 30);
		r2.setLayoutX(100);
		r2.setLayoutY(100);

		Node r3 = new Circle(50);
		r3.setLayoutX(200);
		r3.setLayoutY(200);

		nodes.add(r);
		nodes.add(r2);
		nodes.add(r3);

	}
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {

		
		IPromptFactory pf = new PromptFactory();
		IGameDisplay gd = new StandardDisplay();
//		IControl c = new StandardControl();

//		FileReaderToGameObjects fileReader = new FileReaderToGameObjects("level2");
		populate();
		
//		DisplayScroller scroller = new DisplayScroller(50,50);
//		fileReader.createNodeList().toString();
//		scroller.centerScroll(fileReader.createNodeList(), 50);
		gd.read(nodes);
		gd.display();
		pf.prompt("Hi! Prompt worked!");

	}

	public static void main(String[] args) {
		launch(args);
	}
}
