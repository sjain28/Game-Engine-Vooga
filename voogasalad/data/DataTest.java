package data;

import java.util.ArrayList;
import java.util.List;

import gameengine.Sprite;
import gameengine.Variable;

import authoring.model.VoogaButton;
import authoring.model.VoogaText;
import events.VoogaEvent;
import javafx.application.Application;
import javafx.scene.Node;

import javafx.stage.Stage;
import player.leveldatamanager.LevelDataManager;
import tools.VoogaBoolean;


public class DataTest extends Application {

	
	
    public static void main (String[] args) {
        launch(args);
        
    }
    
    
    @Override
    public void start (Stage primaryStage) throws Exception {
        Sprite sprite = new Sprite("/bricks.jpg","6");
        Node[] vts = new Node[10];
        Variable[] variables = new Variable[10];
        VoogaEvent[] events = new VoogaEvent[10];
        for (int i =0;i<3;i++){
            VoogaText vt = new VoogaText(""+i);
            vt.setTranslateX(100);
            vt.setTranslateY(100);
            vt.setTranslateZ(1000);
            vts[i]=vt;
        }
        for (int i =3; i<5;i++){
            VoogaButton vt = new VoogaButton();
            vt.setTranslateX(100);
            vt.setTranslateY(100);
            vt.setTranslateZ(1000);
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
        
        List<Node> nodeList = new ArrayList<Node>();
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
        String fileName = "test_4-9";
        DataContainerOfLists data = new DataContainerOfLists(nodeList,variableArrayList, eventList);
        System.out.println(data.getVariableList());
        System.out.println(data.getEventList());
        System.out.println(data.getEventList());
        VoogaBoolean vb = new VoogaBoolean(true);
        FileWriterFromObjects fileWriter = new FileWriterFromObjects();
        fileWriter.serialize(data, fileName);
        LevelDataManager manager = new LevelDataManager(fileName);
     //   DataContainerOfLists deserializedList = fileReader.getDataContainer();
//        System.out.println("DONE");
//        System.out.println(deserializedList);
//        System.out.println(deserializedList.getEventList());
        
    }
    
}