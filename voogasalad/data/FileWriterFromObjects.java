package data;

import java.io.FileOutputStream;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class FileWriterFromObjects {

	
	 /*public void serialize(DataContainerOfLists lists, String fileName) throws ParserConfigurationException, TransformerException, IOException, SAXException{
		 XStream mySerializer = new XStream(new DomDriver());
	       // String sprites = mySerializer.toXML(nodeList);
	       // System.out.println(sprites);
	        //mySerializer.createObjectOutputStream(out)(nodes,new FileOutputStream("HIHI.txt"))
	        
	        FileOutputStream fos = null;
	        try{            
	            String xml = mySerializer.toXML(lists);
	            fos = new FileOutputStream(fileName + ".xml");
	            fos.write("<?xml version=\"1.0\"?>".getBytes("UTF-8"));
	            byte[] bytes = xml.getBytes("UTF-8");
	            fos.write(bytes);

	        }catch (Exception e){
	            System.err.println("Error in XML Write: " + e.getMessage());
	        }
	        finally{
	            if(fos != null){
	                try{
	                    fos.close();
	                }catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }
	        }
	 }*/
	 public void serialize(Object obj, String fileName) throws ParserConfigurationException, TransformerException, IOException, SAXException{
		 XStream mySerializer = new XStream(new DomDriver());
	       // String sprites = mySerializer.toXML(nodeList);
	       // System.out.println(sprites);
	        //mySerializer.createObjectOutputStream(out)(nodes,new FileOutputStream("HIHI.txt"))
	        
	        FileOutputStream fos = null;
	        try{            
	            String xml = mySerializer.toXML(obj);
	            fos = new FileOutputStream(fileName + ".xml");
	            fos.write("<?xml version=\"1.0\"?>".getBytes("UTF-8"));
	            byte[] bytes = xml.getBytes("UTF-8");
	            fos.write(bytes);

	        }catch (Exception e){
	            System.err.println("Error in XML Write: " + e.getMessage());
	        }
	        finally{
	            if(fos != null){
	                try{
	                    fos.close();
	                }catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }
	        }
	 }
	
}
