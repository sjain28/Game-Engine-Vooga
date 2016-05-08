// This entire file is part of my masterpiece.
// Joshua Xu

/* This class used to be a gigantic boilerplate for all the other classes. Now, all this class contains are the characteristics
 * specific to the display screen. These methods include the setBorders() class, which calls on the private methods
 * of its superclass, Game Display. In addition, the user has a chance to change the type of control he wants in this class.
 * If  wanted to substitute a different type of Control (which is responsible for stopping, starting, and speeding up the game,
 * he could easily do so by creating a different class called Custom Control.
 *  
 * Notice that in the SetBorders class, not all the borders of the borderpane must be set. Rather, only the borders that 
 * the programmer specifies in the subclass will be set. 
 */

package player.gamedisplay;
import authoring.gui.menubar.MenuPanel;
import authoring.gui.menubar.MenuPanelHandlingMirror;
import javafx.scene.Node;
import player.gamerunner.IGameRunner;
import resources.VoogaBundles;
/**
 * Standard Display that creates a display with basic user-interaction controls
 * Uses composition to contain elements of the display
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
	
	private Node setTop() {
		return new MenuPanel(myGameRunner, e -> new MenuPanelHandlingMirror(e, myGameRunner), VoogaBundles.playerTesterMenubarProperties);
	}
	
	private Node setBottom() {
		return myControl.createControl();
	}

	public IControl getControl() {
		return myControl;
	}

	@Override
	protected void setBorders() {
		super.getBorderPane().setBottom(setBottom());
		super.getBorderPane().setTop(setTop());
	}
}