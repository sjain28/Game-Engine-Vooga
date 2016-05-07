package player.gamedisplay;
import java.util.HashMap;
import java.util.Map;
import authoring.gui.menubar.MenuPanel;
import authoring.gui.menubar.MenuPanelHandlingMirror;
import javafx.scene.Node;
import player.gamerunner.IGameRunner;
import resources.VoogaBundles;
/**
 * Standard Display that creates a display with basic user-interaction controls
 * Uses composition to contain elements of the display
 * 
 * @author Hunter Lee
 */


public class StandardDisplay extends GameDisplay {
	
	private IGameRunner myGameRunner;
	
	public StandardDisplay(IGameRunner gamerunner) {
		super(gamerunner);
		// TODO Auto-generated constructor stub
	}

	
	private Node setRight() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	private Node setTop() {
		// TODO Auto-generated method stub
		return new MenuPanel(myGameRunner, e -> new MenuPanelHandlingMirror(e, myGameRunner), VoogaBundles.playerTesterMenubarProperties);
	}
	
	
	private Node setLeft() {
		// TODO Auto-generated method stub
		return null;
	}
	
	private Node setBottom() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	protected Map<NodeLocation, Node> setPositionalNode() {
		// TODO Auto-generated method stub
		Map<NodeLocation,Node> positionalMap = new HashMap<NodeLocation, Node>();
		positionalMap.put(NodeLocation.TOP, setTop());
		positionalMap.put(NodeLocation.BOTTOM, setBottom());
		return positionalMap;
	}

}