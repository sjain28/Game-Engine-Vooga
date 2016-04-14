package events;

import java.lang.reflect.*;
import tools.interfaces.VoogaData;


public class VariableEffect extends Effect {

	private String myMethod;
	private Object myParameter;
	private String myVariable;

	public VariableEffect (String method, String variable, Double parameter, VoogaEvent event) {
		super(event);
		myMethod = method;
		myVariable = variable;
		myParameter = parameter;
	}
	public VariableEffect (String method, String variable, Boolean parameter, VoogaEvent event) {
		super(event);
		myMethod = method;
		myVariable = variable;
		myParameter = parameter;
	}
	public VariableEffect (String method, String variable, VoogaEvent event) {
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
	public void execute () {
		VoogaData variableData = getEvent().getManager().getGlobalVar(myVariable);
		callEffectMethod(variableData);
	}

	protected void callEffectMethod(VoogaData variable){
		Class dataType = variable.getClass();
		Method[] methods = dataType.getMethods();
		try{
			Method variableMethod = getMethodfromString(methods, myMethod);
			Class[] parameterTypes = variableMethod.getParameterTypes();
			
			if (myParameter != null){
				variableMethod.invoke(variable, parameterTypes[0].cast(myParameter));
			}
			else {
				variableMethod.invoke(variable);
			}
			
		}catch (Exception e){
			//throw new VoogaException(String.format(format, args));
		}
	}
	protected Method getMethodfromString(Method[] methods, String name){
		for (int i = 0; i < methods.length; i++){
			if(name.equals(methods[i].getName()))
				return methods[i];
		}
		return null;	
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
