package data;

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
import auxiliary.VoogaException;
import tools.interfaces.VoogaData;


public final class DeSerializer {
    private final static String COMMAND_PATH = "data";

    public static List<Object> deserialize (int objectNum, String fileName) throws VoogaException {
        System.out.println("What is the fileName" + fileName);
        XStream unSerializer = new XStream(new DomDriver());
        List<Object> objectsCreated = new ArrayList<Object>();
        try {
            ObjectInputStream objectInputStream =
                    unSerializer.createObjectInputStream(new FileInputStream(fileName));
            for (int i = 0; i < objectNum; i++) {
                try {
                    Object object = null;
                    object = (Object) objectInputStream.readObject();
                    objectsCreated.add(object);
                }
                catch (ClassNotFoundException e) {
                    throw new VoogaException("Can't Locate File");
                }
            }
        }
        catch (RuntimeException e) {
            System.out.println("IO exception from deserializer");
            e.printStackTrace();
        }
        catch (FileNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        System.out.println(objectsCreated);
        return objectsCreated;
    }
}
