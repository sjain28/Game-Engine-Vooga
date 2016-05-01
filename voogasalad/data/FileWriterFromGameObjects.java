package data;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

/* This class is a custom tool that utilizes the Deserializer to allow a user to write game-specific objects 
 * an XML file. Because the game objects are currently serialized in a DataContainer, the saveGameObjects method 
 * requires that the user put the desired objects into a DataContainerOfLists.
 */

public class FileWriterFromGameObjects {

    public static void saveGameObjects (DataContainerOfLists lists,
                                        String fileName) throws ParserConfigurationException,
                                                         TransformerException, IOException,
                                                         SAXException {
        File file = new File(fileName);
        if (!file.exists()) {
            /*
             * createNewFile() method is used to creates a new, empty file
             * mentioned by given abstract pathname if and only if a file with
             * this name does not exist in given abstract pathname.
             */
            file.getParentFile().mkdir();
        }
       Serializer.serializeLevel(lists, fileName);
    }
}
