package data;

import java.util.List;

import events.VoogaEvent;
import gameengine.Variable;
import javafx.scene.Node;

public class DataContainerOfLists {

	private List<VoogaEvent> eventList;
	private List<Node> nodeList;
	private List<Variable> variableList;
	
	public DataContainerOfLists(){
	}
	
	public DataContainerOfLists(List<Node> nodeList, List<Variable> variableList, List<VoogaEvent> eventList){
		this.eventList = eventList;
		this.nodeList = nodeList;
		this.variableList = variableList;
	}
	
	public List<Node> getNodeList(){
		return nodeList;
	}
	public List<VoogaEvent> getEventList(){
		return eventList;
	}
	public List<Variable> getVariableList(){
		return variableList;
	}
	
}
