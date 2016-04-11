package data;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import authoring.interfaces.Elementable;
import tools.interfaces.VoogaData;

public final class UnSerializer {
	private final static String COMMAND_PATH = "data";	
	private UnSerializer(){

	}
	public static List<Object> deserialize(int objectNum,String fileName) {
		XStream unSerializer = new XStream(new DomDriver());
		List<Object> objectsCreated = new ArrayList<Object>();
		try{
			ObjectInputStream objectInputStream = unSerializer.createObjectInputStream(new FileInputStream(fileName));
			for (int i = 0; i < objectNum; i++){ 
					try {
						Object object = null;
						object = (Object)objectInputStream.readObject();
						objectsCreated.add(object);
					} 
					catch (ClassNotFoundException e) {
						e.printStackTrace();
					}
			}
		}
		catch (IOException e){
			e.printStackTrace();
		}
		return objectsCreated;
	}
}
