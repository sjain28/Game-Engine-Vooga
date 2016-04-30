package tests;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import data.DataContainerOfLists;
import data.FileWriterFromGameObjects;
import javafx.application.Application;
import javafx.stage.Stage;


public class FormalTest extends Application {
	
	 public static final String testString = "levels/WORKPLS.xml";
    public static void main (String[] args) {
        launch(args);
        
    }
    @Override
    public void start (Stage primaryStage) throws Exception {
 
        DataWritingTest dataTest= new DataWritingTest();
        dataTest.setup();
        DataContainerOfLists manager = dataTest.getData();
        try {
            FileWriterFromGameObjects.saveGameObjects(manager, testString);
        }
     
        catch (ParserConfigurationException | TransformerException | IOException | SAXException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
      
    }
    
}