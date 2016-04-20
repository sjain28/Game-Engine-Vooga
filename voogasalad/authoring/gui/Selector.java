package authoring.gui;

public enum Selector {
	HIGHLIGHTED(0.4, 4.0),
	UNHIGHLIGHTED(0, 0);
	
	private double lightness;
	private double glow;
	
	private Selector(double lightness, double glow) {
		this.lightness = lightness;
		this.glow = glow;
	}
	
	public double getLightness() {
		return this.lightness;
	}

	public double getGlow() {
		return this.glow;
	}
	
}
