
package data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import authoring.interfaces.Elementable;
import authoring.model.VoogaButton;
import authoring.model.VoogaText;
import events.VoogaEvent;
import javafx.application.Application;

import javafx.stage.Stage;
import tools.VoogaBoolean;
import tools.VoogaNumber;
import tools.interfaces.VoogaData;


public class DataTest extends Application {
    public static void main (String[] args) {
        launch(args);
        
    }
    @Override
    public void start (Stage primaryStage) throws Exception {
 
        Elementable[] vts = new Elementable[10];
        VoogaData[] variables = new VoogaData[10];
        VoogaEvent[] events = new VoogaEvent[10];
        for (int i =0;i<3;i++){
            VoogaText vt = new VoogaText(""+i);
            vt.setTranslateX(100);
            vt.setTranslateY(100);
            vt.setTranslateZ(0);
            vts[i]=vt;
        }
        for (int i =3; i<5;i++){
            VoogaButton vt = new VoogaButton();
            vt.setTranslateX(100);
            vt.setTranslateY(100);
            vt.setTranslateZ(0);
            vts[i]=vt;
        }
        
        for (int i =0; i<10;i++){
            VoogaEvent event = new VoogaEvent();
            events[i] = event;
         }
        
        List<Elementable> nodeList = new ArrayList<Elementable>();
        for (int i =0; i<4;i++){
        	nodeList.add(vts[i]);
        }
        Map<String,VoogaData> variableArrayList = new HashMap<String,VoogaData>();
        for (int i =0; i<4;i++){
        	variableArrayList.put(""+i,variables[i]);
        }
        List<VoogaEvent> eventList = new ArrayList<VoogaEvent>();
        for (int i =0; i<4;i++){
        	eventList.add(events[i]);
        }
        String fileName = "level7";
        String fileName2 = "level7-rewritten";
        DataContainerOfLists data = new DataContainerOfLists(nodeList,variableArrayList, eventList);
        System.out.println("Data type 1 is equal to " + data.getVariableMap());
        System.out.println("Data type 2 is equal to " + data.getEventList());
        VoogaBoolean vb = new VoogaBoolean(true);
        FileWriterFromGameObjects fileWriter = new FileWriterFromGameObjects();
        fileWriter.saveGameObjects(data, fileName);
        fileWriter.saveGameObjects(data, fileName);
        
        FileReaderToGameObjects filereader = new FileReaderToGameObjects(fileName);
//        GameRunner gameRunner = new GameRunner("level_doc.txt");
        DataContainerOfLists deserializedList = filereader.getDataContainer();
     
        fileWriter.saveGameObjects(deserializedList, fileName2);
        System.out.println("DONE");
        System.out.println(deserializedList);
        System.out.println(deserializedList.getEventList());
        
    }
    
}