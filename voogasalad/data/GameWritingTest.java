package data;

import static org.junit.Assert.*;

import org.junit.Test;

public class GameWritingTest {

	@Test
	public void test() {
		DataContainerOfLists data = new DataContainerOfLists();
		Sprite s= new Sprite();
		FileWriterFromGameObjects filewriter = new FileWriterFromGameObjects();
		filewriter.saveGameObjects(,"testing.xml");
		FileReaderToGameObjects filereader = new FileReaderToGameObjects("testing.xml");
	}

}
