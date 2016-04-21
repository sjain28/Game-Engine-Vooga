package authoring.gui;

import java.util.ArrayList;
import java.util.List;

import authoring.CustomText;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.Tab;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class DesignBoardPreferences extends Tab {
	
	private static final double SPACING = 10;
	private static final double WIDTH = 300;
	
	private VBox container;
	
	private RadioButton realistic;
	private RadioButton cartoon;
	private RadioButton continuous;
	private RadioButton tracking;
	
	private HBox slider;
	
	private Slider scrollSpeed;

	public DesignBoardPreferences() {
		container = new VBox();
		container.setSpacing(SPACING);
		container.setAlignment(Pos.CENTER);
		this.setContent(container);
		container.getChildren().addAll(choosePhysicsModule(),
									   chooseTrackingMode());
		scrollSpeed = new Slider();
		scrollSpeed.setMaxWidth(WIDTH);
		chooseSpecificTrackingMode();
	}
	
	private HBox choosePhysicsModule() {
		realistic = new RadioButton("Realistic");
		cartoon = new RadioButton("Cartoon");
		return createToggleGroup("Physics Module:", realistic, cartoon);
	}
	
	private HBox chooseTrackingMode() {
		continuous = new RadioButton("Continuous");
		tracking = new RadioButton("Tracking");
		return createToggleGroup("Scrolling Mode:", continuous, tracking);
	}
	
	private void chooseSpecificTrackingMode() {
//		continuous.selectedProperty().addListener((obs, old, n) -> {
//			if(n){
//				container.getChildren().add(cont);
//				container.getChildren().remove(trak);
//			}
//			else {
//				container.getChildren().add(trak);
//				container.getChildren().remove(cont);
//			}
//		});
	}
	
//	private HBox makeSlider() {
//		
//	}
	
	private HBox createToggleGroup(String label, RadioButton...toggles) {
		ToggleGroup group = new ToggleGroup();
		HBox row = makeRow(new CustomText(label));
		for(RadioButton toggle : toggles) {
			toggle.setToggleGroup(group);
			row.getChildren().add(toggle);
		}
		return row;
	}
	
	private HBox makeRow(Node... nodes) {
		HBox row = new HBox();
		row.setSpacing(SPACING);
		row.setMaxWidth(WIDTH);
		row.getChildren().addAll(nodes);
		return row;
	}

}