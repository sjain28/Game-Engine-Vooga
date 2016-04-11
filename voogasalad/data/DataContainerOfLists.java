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
		System.out.println(this.eventList);
		this.elementableList = ElementableList;
		this.variableList = variableList;
	}
	
	public List<Elementable> getElementableList(){
		
		return this.elementableList;
	}
	public List<VoogaEvent> getEventList(){
		return this.eventList;
	}
	public List<Variable> getVariableList(){
		return this.variableList;
	}
	
	public String toString(){
		return "Data Container + 35";
	}
	
}
