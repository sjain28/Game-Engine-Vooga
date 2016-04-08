package data;

import java.util.List;

import authoring.interfaces.Elementable;
import events.VoogaEvent;
import gameengine.Variable;

public class DataContainerOfLists {

	private List<VoogaEvent> eventList;
	private List<Elementable> elementableList;
	private List<Variable> variableList;
	
	public DataContainerOfLists(){
	}
	
	public DataContainerOfLists(List<Elementable> ElementableList, List<Variable> variableList, List<VoogaEvent> eventList){
		this.eventList = eventList;
		this.elementableList = ElementableList;
		this.variableList = variableList;
	}
	
	public List<Elementable> getElementableList(){
		return elementableList;
	}
	public List<VoogaEvent> getEventList(){
		return eventList;
	}
	public List<Variable> getVariableList(){
		return variableList;
	}
	
}
