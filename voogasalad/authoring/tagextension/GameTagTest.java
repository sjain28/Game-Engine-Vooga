package authoring.tagextension;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * Game Tag Test class to push
 * @author Arjun Desai
 *
 */
public class GameTagTest {

	@Test
	public void testGameTagLibrary() {
		GameTagLibrary taglibrary = new GameTagLibrary();
		List<String> listoftags = new ArrayList<String>();
		listoftags.add("hi");
		listoftags.add("cartoon");
		listoftags.add("vector");
		listoftags.add("character");
		List<String> listofgamenames = taglibrary.getListOfGameNamesByTag(listoftags);
	}

}
