package events;

import java.lang.reflect.*;
import tools.interfaces.VoogaData;


public class VariableEffect extends Effect {

	private String myMethod;
	private String myVariable;

	public VariableEffect (String method, String variable, VoogaEvent event) {
		super(event);
		myMethod = method;
		myVariable = variable;
	}

	@Override
	public void execute () {
		VoogaData variableData = getEvent().getManager().getGlobalVar(myVariable);
		callEffectMethod(variableData);
	}

	private void callEffectMethod(VoogaData variable){
		Class dataType = variable.getClass();
		Method[] methods = dataType.getMethods();
		String[] methodParameters = myMethod.split(" ");
		try{
			Method variableMethod = getMethodfromString(methods, methodParameters[0]);
			Class[] parameterTypes = variableMethod.getParameterTypes();
			
			if (methodParameters.length > 1){
				variableMethod.invoke(variable, parameterTypes[0].cast(methodParameters[1]));
			}
			else {
				variableMethod.invoke(variable);
			}
			
		}catch (Exception e){

		}
	}
	private Method getMethodfromString(Method[] methods, String name){
		for (int i = 0; i < methods.length; i++){
			if(name.equals(methods[i].getName()))
				return methods[i];
		}
		return null;	
	}

	public String getVariable(){
		return myVariable;
	}
}
