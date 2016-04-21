package joshmasterpiece;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import authoring.interfaces.Elementable;
import tools.VoogaException;
import tools.interfaces.VoogaData;

/* DeSerializer is a general tool that can be utilized by any class. It is a tool that can be used to generate 
 * a list of objects from an xml file. For now, it has a single method: deserialize.
 */

public final class Deserializer2 {
    private final static String COMMAND_PATH = "data";
    private XStream unSerializer;
    private ObjectInputStream objectInputStream;

    public Deserializer2(String fileName) throws FileNotFoundException, IOException{
    	unSerializer = new XStream(new DomDriver());
    	objectInputStream = unSerializer.createObjectInputStream(new FileInputStream(fileName));
    }
    
    public Object deserialize (int objectNum, String fileName) throws VoogaException {
    	Object object = null;
    	try{
    		 object = (Object) objectInputStream.readObject();
    	}             
        catch (ClassNotFoundException e) {
                    throw new VoogaException("This object type does not exist");
        }
        catch (RuntimeException exception) {
        	throw new VoogaException("Took too long to update");
        }
        catch (IOException exception) {
        	throw new VoogaException("Input file does not exist");
        }
        return object;
    }
    
    public List<Object> deserializeObjectList (String fileName) throws VoogaException {
//    	  ResourceBundle 
    	  List<Object> objectsCreated = new ArrayList<Object>();
//          try {
//        	  
//              for (int i = 0; i < objectNum; i++) {
//                  try {
//                      Object object = null;
//                      object = (Object) objectInputStream.readObject();
//                      objectsCreated.add(object);
//                  }
//                  catch (ClassNotFoundException e) {
//                      throw new VoogaException("This object type does not exist");
//                  }
//              }
//          }
//          catch (RuntimeException exception) {
//          	throw new VoogaException("Took too long to update");
//          }
//          catch (IOException exception) {
//          	throw new VoogaException("Input file does not exist");
//          }
          return objectsCreated;
    	
    }
}
