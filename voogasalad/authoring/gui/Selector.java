package authoring.gui;

/**
 * When items are selected in the GUI, selector to help highlight/un-highlight.
 * 
 * @author Arjun Desai, Aditya Srinivasan, Harry Guo, Nick Lockett
 *
 */

public enum Selector {
	HIGHLIGHTED(0.4, 4.0),
	UNHIGHLIGHTED(0, 0);

	private double lightness;
	private double glow;

	/**
	 * Affects the image selected.
	 * @param lightness: how light image appears
	 * @param glow: how much image gloves
	 */
	private Selector(double lightness, double glow) {
		this.lightness = lightness;
		this.glow = glow;
	}

	/**
	 * @return the index of how light the object selected is
	 */
	public double getLightness() {
		return this.lightness;
	}

	/**
	 * @return the index of how glowing an object is
	 */
	public double getGlow() {
		return this.glow;
	}

}
