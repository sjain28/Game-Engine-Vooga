package authoring.gui.cartography;

import javafx.scene.paint.Color;

public class Endpoint extends Point {
	
	private String endLevel;
	
	public Endpoint() {
		super(0, Color.ORANGERED);
	}

	@Override
	protected void setPoint(String name) {
		this.endLevel = name;
	}

	@Override
	protected String getPoint() {
		return this.endLevel;
	}

	@Override
	protected LevelType getLevelType() {
		return LevelType.ENDPOINT;
	}

}
