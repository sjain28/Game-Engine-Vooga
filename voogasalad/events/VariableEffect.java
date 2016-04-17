package events;

import java.lang.reflect.*;

import player.leveldatamanager.LevelData;
import tools.interfaces.VoogaData;


public class VariableEffect extends Effect {

	private String myMethod;
	private Object myParameter;
	private String myVariable;

	public VariableEffect (String variable, String method, Double parameter, VoogaEvent event) {
		super(event);
		myMethod = method;
		myVariable = variable;
		myParameter = parameter;
	}
	public VariableEffect (String variable, String method, Boolean parameter, VoogaEvent event) {
		super(event);
		myMethod = method;
		myVariable = variable;
		myParameter = parameter;
	}
	public VariableEffect (String variable, String method, VoogaEvent event) {
		super(event);
		myMethod = method;
		myVariable = variable;
	}

	public VariableEffect (String method, Double parameter, VoogaEvent event){
		super(event);
		myMethod = method;
		myParameter = parameter;
	}
	@Override
	public void execute (LevelData data) {
		//Class varClass = getEvent().getManager().getGlobalVar(myVariable).getClass();
		VoogaData variableData = data.getGlobalVar(myVariable);
		callEffectMethod(variableData);
	}
	
	public void init(){
		return;
	}

	protected void callEffectMethod(VoogaData variable){
		Class dataType = variable.getClass();
		try{			
			if (myParameter != null){
				System.out.println("METHOD NAME: "+myMethod);
				Method variableMethod = dataType.getMethod(myMethod, new Class[]{myParameter.getClass()});
				variableMethod.invoke(variable, myParameter);
			}
			else {
				Method variableMethod = dataType.getMethod(myMethod, null);
				variableMethod.invoke(variable);
			}
			
		}catch (Exception e){
			//throw new VoogaException(String.format(format, args));
		}
	}

	public String getVariable(){
		return myVariable;
	}
	public String getMethodString(){
		return myMethod;
	}
	public Object getParameters(){
		return myParameter;
	}
}
