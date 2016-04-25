package authoring.tagextension;

import authoring.CustomText;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class TagLabel extends HBox {
	
	private static final double SPACING = 7;
	private static final double INSETS = 5;
	private static final double RADIUS = 10;
	
	private String tag;
	
	public TagLabel(String tag) {
		this.tag = tag;
		this.getChildren().addAll(new CustomText(tag), new CustomText("x"));
		this.setPadding(new Insets(INSETS));
		this.setSpacing(SPACING);
		this.setBackground(new Background(new BackgroundFill(Color.CORNFLOWERBLUE, new CornerRadii(RADIUS), Insets.EMPTY)));
	}
	
	public String getTag() {
		return tag;
	}
	
	@Override
	public String toString() {
		return tag;
	}

}
