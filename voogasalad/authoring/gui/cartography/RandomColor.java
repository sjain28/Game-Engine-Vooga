package authoring.gui.cartography;

import java.util.Random;
import java.util.ResourceBundle;

import resources.VoogaBundles;

/**
 * Random Color Generator.
 * 
 * @author Harry Guo, Nick Lockett, Arjun Desai, Aditya Srinivasan
 *
 */
public class RandomColor {

	private String[] myColors;

	
	/**
	 * Instantiates the random color array.
	 */
	public RandomColor() {
	
		myColors = new String[] {"#EF5350",
								 "#EC407A",
								 "#7E57C2",
								 "#5C6BC0",
								 "#2196F3",
								 "#039BE5",
								 "#0097A7",
								 "#009688",
								 "#8BC34A",
								 "#FBC02D",
								 "#EF6C00"};
	}
	
	/**
	 * Gets a random color.
	 * @return
	 */
	public String getRandomColor() {
		Random random = new Random();
		return myColors[random.nextInt(myColors.length)];
	}

}
