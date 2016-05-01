package data;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import resources.VoogaBundles;
import tools.VoogaException;

/* Deserializer is a general tool that can be utilized by any class. It is a tool that can be used to generate 
 * a list of objects from an xml file. For now, it has a single method: deserialize.
 */

public final class Deserializer {

    public static List<Object> deserialize (int objectNum, String fileName) throws VoogaException {
        XStream unSerializer = new XStream(new DomDriver());
        List<Object> objectsCreated = new ArrayList<>();
        try {
            ObjectInputStream objectInputStream =
                    unSerializer.createObjectInputStream(new FileInputStream(fileName));
            for (int i = 0; i < objectNum; i++) {
                try {
                    Object object;
                    object = objectInputStream.readObject();
                    objectsCreated.add(object);
                }
                catch (ClassNotFoundException e) {
                    throw new VoogaException(VoogaBundles.exceptionProperties.getString("ClassNotFound"));
                }
            }
        }
        catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
        catch (IOException e1) {
            e1.printStackTrace();
        }
        return objectsCreated;
    }
}
