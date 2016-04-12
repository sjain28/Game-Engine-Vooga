package data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import authoring.interfaces.Elementable;
import authoring.model.VoogaButton;
import authoring.model.VoogaText;
import events.VoogaEvent;
import javafx.application.Application;

import javafx.stage.Stage;
import tools.VoogaBoolean;
import tools.VoogaNumber;
import tools.interfaces.VoogaData;


public class FormalTest extends Application {
	
    public static void main (String[] args) {
        launch(args);
        
    }
    @Override
    public void start (Stage primaryStage) throws Exception {
 
        DataWritingTest dataTest= new DataWritingTest();
        dataTest.setup();
        DataContainerOfLists manager = dataTest.getData();
        try {
            FileWriterFromGameObjects.saveGameObjects(manager, "TestWriting");
        }
        catch (ParserConfigurationException | TransformerException | IOException | SAXException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
      
    }
    
}