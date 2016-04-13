package data;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.xml.sax.SAXException;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;


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
            System.out.println("The file name here is " + fileName);
            file.createNewFile();
        }
       Serializer.serialize(lists, fileName);
    }
}
