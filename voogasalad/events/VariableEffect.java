package events;

import java.lang.reflect.*;

import player.leveldatamanager.ILevelData;
import tools.VoogaException;
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
	public VariableEffect (String variable, String method, String parameter, VoogaEvent event) {
		super(event);
		myMethod = method;
		myVariable = variable;
		myParameter = parameter;
	}

	public VariableEffect (String method, Double parameter, VoogaEvent event){
		super(event);
		myMethod = method;
		myParameter = parameter;
	}
	
	@Override
	public void execute(ILevelData data) {
		System.out.println("variable effect executing");
		VoogaData variableData = data.getGlobalVar(myVariable);
		callEffectMethod(variableData);
	}

	protected void callEffectMethod(VoogaData variable){
		Class dataType = variable.getClass();
		System.out.println("datatype: "+ dataType);
		try{
			System.out.println("myParameter: "+myParameter);
			if (myParameter != null){
				Method variableMethod = dataType.getMethod(myMethod, new Class[]{myParameter.getClass()});
				System.out.println("variable method "+ variableMethod+" called with "+myParameter);
				variableMethod.invoke(variable, myParameter);
			}
			else {
				Method variableMethod = dataType.getMethod(myMethod, null);
				System.out.println("variable method called with null: "+ variableMethod);
				variableMethod.invoke(variable);
			}
			
		}catch (Exception e){
			System.out.println("THROWING THIS EXCEPTION OVA HERE");
			//throw new VoogaException(String.format(format, args));
		}
	}
	
	@Override
	public String toString() {
		String effectString = myMethod + myVariable;
		if (myParameter != null){
			effectString += "[" + myParameter.toString() + "]";
		}
		return effectString;
	}
	
	protected String getVariable(){
		return myVariable;
	}
	protected String getMethodString(){
		return myMethod;
	}
	protected Object getParameter(){
		return myParameter;
	}
	
	public void setParameter(Object parameter) throws VoogaException{
		if (!parameter.getClass().equals(myParameter.getClass())){
			throw new VoogaException("Please input a " + myParameter.getClass());
		}else{
			myParameter = parameter;
		}
	}	
}
