// This entire file is part of my masterpiece.
// Joshua Xu

/* I created this class in order to create an interface between KeyHandler and the Standard Display. Doing so in this manner
 * allows the functionality of handling key presses to be separate from the functionality of displaying all the nodes on
 * the screen. In a way, this could be viewed as the Model-View-Controller design pattern, in which back end components (the model)
 * is kept separate from the front end components (the nodes being displayed). 
 * 
 * The purpose of this interface is to not give the Game Display complete access to the key handler. In this manner,
 * the Game Display can only interact with the key handler through limited means, ala the methods outlined in this interface.
 * 
 * This use of interfaces was discussed in class as SOLID design.
 * 
 */


package player.gamedisplay;

import java.util.List;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public interface IKeyHandler{
	EventHandler<KeyEvent> createKeyListener() ;
	List<KeyEvent> getKeyEvents();
	List<KeyEvent> getMyKeyPresses();
	List<KeyEvent> getMyKeyReleases();
	void clearKeyEvents();
}
