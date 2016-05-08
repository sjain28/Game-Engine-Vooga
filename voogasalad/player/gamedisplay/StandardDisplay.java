// This entire file is part of my masterpiece.
// Joshua Xu

/* This class used to be a gigantic boilerplate for all the other classes. Now, all this class contains are the characteristics
 * specific to the display screen. These methods include the setBorders() class, which calls on the private methods
 * of its superclass, Game Display. In addition, the user has a chance to change the type of control he wants in this class.
 * If  wanted to substitute a different type of Control (which is responsible for stopping, starting, and speeding up the game,
 * he could easily do so by creating a different class called Custom Control.
 *  
 * Notice that in the SetBorders class, not all the borders of the BorderPane must be set. Rather, only the borders that 
 * the programmer specifies in the subclass will be set. This gives the user the flexibility to customize the borders of the display
 * however he wants. I determined this to be good design. Although there is the drawback that the display could override the center of the screen,
 * I left that to the discretion of the new game display. If such a GameDisplay specifies that it will contain something unrelated,
 * such as a map of England instead of the game objects, that is within the right of the Display to show that. I preferred to give flexibility
 * in order to reduce duplicated "setBottom", "setRight", "setTop" methods, by simply combining them into the setBorders() method.
 */

package player.gamedisplay;
import authoring.gui.menubar.MenuPanel;
import authoring.gui.menubar.MenuPanelHandlingMirror;
import javafx.scene.Node;
import player.gamerunner.IGameRunner;
import resources.VoogaBundles;
/**
 * Standard Display that creates a display with basic user-interaction controls
 * A subclass of GameDisplay.
 * 
 * @author Hunter Lee, Joshua Xu
 */


public class StandardDisplay extends GameDisplay {
	
	private IGameRunner myGameRunner;
	private IControl myControl;
	
	public StandardDisplay(IGameRunner gamerunner, double width, double height) {
		super(gamerunner, width, height);
		myGameRunner = super.getGameRunner();
		myControl = new StandardControl(myGameRunner);
	}
	
	/**
	 *  In the Standard Display, the top part of the BorderPane contains the menu.
	 */
	private Node setTop() {
		return new MenuPanel(myGameRunner, e -> new MenuPanelHandlingMirror(e, myGameRunner), VoogaBundles.playerTesterMenubarProperties);
	}
	
	/**
	 *  In the Standard Display, the bottom part of the BorderPane contains the control buttons (such as pause, start, and fast forward). Not necessarily
	 *  used in every Display type, but the Standard Display includes the control.
	 */
	private Node setBottom() {
		return myControl.createControl();
	}
	
	public IControl getControl() {
		return myControl;
	}
	
	/**
	 *  Subclass abstract method used to set only the borders of the BorderPane (including the top, left, right, and bottom). 
	 */
	
	@Override
	protected void setBorders() {
		super.getBorderPane().setBottom(setBottom());
		super.getBorderPane().setTop(setTop());
	}
}