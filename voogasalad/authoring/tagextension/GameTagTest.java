package authoring.tagextension;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class GameTagTest {

	@Test
	public void testGameTagLibrary() {
		GameTagLibrary taglibrary = new GameTagLibrary();
		List<String> listoftags = new ArrayList<String>();
		listoftags.add("hi");
		listoftags.add("cartoon");
		listoftags.add("character");
		List<String> listofgamenames = taglibrary.getListOfGameNamesByTag(listoftags);
		//System.out.println(listofgamenames);
	}

}
