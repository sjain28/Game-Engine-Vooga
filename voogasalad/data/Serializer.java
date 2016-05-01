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

/* Serializer is a general tool that can be utilized by any class. It is a data utility tool that can write any object
 * to a list. That list will be serialized to an XML file. For now, 
 */

public class Serializer {


	public static void serialize(Object object, String fileName)
			throws ParserConfigurationException, TransformerException, IOException, SAXException {
		XStream serializer = new XStream(new DomDriver());
		ObjectOutputStream objectOutputStream = serializer.createObjectOutputStream(new FileOutputStream(fileName));
		objectOutputStream.writeObject(object);
		objectOutputStream.close();
	}

	public static void serializeLevel(Object object, String fileName)
			throws ParserConfigurationException, TransformerException, IOException, SAXException {
		File file = new File(fileName);
		if (!file.exists()) {
			try {
				file.getParentFile().mkdir();
			} catch (Exception e) {
				throw e;
			}
		}
		serialize(object, fileName);
	}
}
