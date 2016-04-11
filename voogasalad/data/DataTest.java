package data;

import java.util.ArrayList;
import java.util.List;

import gameengine.Sprite;
import gameengine.Variable;
import authoring.interfaces.Elementable;
import authoring.model.VoogaButton;
import authoring.model.VoogaText;
import events.VoogaEvent;
import javafx.application.Application;
import javafx.scene.Node;

import javafx.stage.Stage;
import player.gamerunner.GameRunner;
import player.leveldatamanager.LevelDataManager;
import tools.VoogaBoolean;


public class DataTest extends Application {
    public static void main (String[] args) {
        launch(args);
        
    }
    @Override
    public void start (Stage primaryStage) throws Exception {
 
        Elementable[] vts = new Elementable[10];
        Variable[] variables = new Variable[10];
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
           Variable variable = new Variable();
           variables[i] = variable;
        }
        
        for (int i =0; i<10;i++){
            VoogaEvent event = new VoogaEvent();
            events[i] = event;
         }
        
        List<Elementable> nodeList = new ArrayList<Elementable>();
        for (int i =0; i<4;i++){
        	nodeList.add(vts[i]);
        }
        List<Variable> variableArrayList = new ArrayList<Variable>();
        for (int i =0; i<4;i++){
        	variableArrayList.add(variables[i]);
        }
        List<VoogaEvent> eventList = new ArrayList<VoogaEvent>();
        for (int i =0; i<4;i++){
        	eventList.add(events[i]);
        }
        String fileName = "level7";
        String fileName2 = "level7-rewritten";
        DataContainerOfLists data = new DataContainerOfLists(nodeList,variableArrayList, eventList);
        System.out.println("Data type 1 is equal to " + data.getVariableList());
        System.out.println("Data type 2 is equal to " + data.getEventList());
        VoogaBoolean vb = new VoogaBoolean(true);
        FileWriterFromObjects fileWriter = new FileWriterFromObjects();
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