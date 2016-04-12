package data;

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
        XStream serializer = new XStream(new DomDriver());
//        if (!file.exists()) {
//        	 
//        	/*
//        	* createNewFile() method is used to creates a new, empty file
//        	* mentioned by given abstract pathname if and only if a file with
//        	* this name does not exist in given abstract pathname.
//        	*/
//        	b = file.createNewFile();
//        }
        ObjectOutputStream objectOutputStream =
                serializer.createObjectOutputStream(new FileOutputStream(fileName));

        objectOutputStream.writeObject(lists);

        objectOutputStream.writeObject("Hello World");
        objectOutputStream.close();
    }

}