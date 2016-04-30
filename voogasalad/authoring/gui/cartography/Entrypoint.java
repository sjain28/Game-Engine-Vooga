package authoring.gui.cartography;

import javafx.scene.paint.Color;

public class Entrypoint extends Point {
	
	private String entryLevel;
	
	private Entrypoint() {
		super(0, Color.LIGHTGREEN);
	}
	
	private static class SingletonHolder {
		private static final Entrypoint INSTANCE = new Entrypoint();
	}
	
	public static Entrypoint getInstance() {
		return SingletonHolder.INSTANCE;
	}

	@Override
	protected void setPoint(String name) {
		this.entryLevel = name;
	}

	@Override
	protected String getPoint() {
		return this.entryLevel;
	}

	@Override
	protected LevelType getLevelType() {
		return LevelType.ENTRYPOINT;
	}
	
	

}
