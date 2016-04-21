package joshmasterpiece;

import java.io.FileNotFoundException;
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

public class Serializer2 {

	private XStream serializer;
	private ObjectOutputStream objectOutputStream;
	private boolean writeMultipleObjects;
	
	public Serializer2(Object o, String fileName) throws FileNotFoundException, IOException{
		serializer = new XStream(new DomDriver());
        objectOutputStream =
                serializer.createObjectOutputStream(new FileOutputStream(fileName));
        writeMultipleObjects = false;
	}
	
	public Serializer2(String fileName) throws FileNotFoundException, IOException{
		serializer = new XStream(new DomDriver());
        objectOutputStream =
                serializer.createObjectOutputStream(new FileOutputStream(fileName));
        writeMultipleObjects = true;
	}
	
    public void serialize (Object object) throws ParserConfigurationException,
                                                           TransformerException, IOException,                    
                                                           SAXException {
        objectOutputStream.writeObject(object);
    	if (!writeMultipleObjects){
    		endSerializer();
    	}
    }
    
    public void endSerializer() throws IOException{
    	objectOutputStream.close();
    }
}