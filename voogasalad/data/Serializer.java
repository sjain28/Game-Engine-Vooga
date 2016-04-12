package data;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.xml.sax.SAXException;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;


public class Serializer {

    private Serializer () {

    }

    public static void serialize (Object object, String fileName) throws ParserConfigurationException,
                                                           TransformerException, IOException,
                                                           SAXException {
        XStream serializer = new XStream(new DomDriver());
        ObjectOutputStream objectOutputStream =
                serializer.createObjectOutputStream(new FileOutputStream(fileName + ".xml"));
        objectOutputStream.writeObject(object);
        objectOutputStream.close();
    }
}
