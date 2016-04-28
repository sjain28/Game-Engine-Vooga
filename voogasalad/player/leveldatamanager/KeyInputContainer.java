/**
 * 
 */
package player.leveldatamanager;

import java.util.List;
import java.util.Map;

import events.KeyCause;

/**
 * A container class that stores KeyPressedCombos
 * 								 KeyReleasedCombos
 * 								 KeyPressCauses
 * 								 KeyReleaseCauses
 * Also provides relevant methods to support managing them
 * 
 * Composition element of LevelData interface
 * 
 * @author Hunter Lee
 *
 */
public class KeyInputContainer {

	private List<List<String>> myKeyPressedCombos;
	private List<List<String>> myKeyReleasedCombos;
	private Map<List<String>, KeyCause> myKeyPressCauses;
	private Map<List<String>, KeyCause> myKeyReleaseCauses;

}

