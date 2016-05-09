package events;

import java.lang.reflect.Method;

import player.leveldatamanager.ILevelData;
import tools.VoogaException;
import tools.interfaces.VoogaData;

/**
 * Effect for the events API that allows user to change global variables.
 * @author Anita
 *
 */

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
	public VariableEffect(VoogaEvent event){
		super(event);
	}
	
	@Override
	public void execute(ILevelData data) {
		VoogaData variableData = data.getGlobalVar(myVariable);
		callEffectMethod(variableData);
	}

	/**
	 * Uses reflection to call the corresponding method to the user's indicated method upon the user indicated
	 * variable using the user indicated parameter as the method argument
	 * @param variable
	 */
	protected void callEffectMethod(VoogaData variable){
		Class dataType = variable.getClass();
		try{
			if (myParameter != null){
				Method variableMethod = dataType.getMethod(myMethod, new Class[]{myParameter.getClass()});
				variableMethod.invoke(variable, myParameter);
			}
			else {
				Method variableMethod = dataType.getMethod(myMethod, null);
				variableMethod.invoke(variable);
			}
			
		}catch (Exception e){
			e.printStackTrace();
			//throw new VoogaException(String.format(format, args));
		}
	}
	
	@Override
	public String toString() {
		String effectString = myMethod + " " + myVariable;
		if (myParameter != null){
			effectString += " [" + myParameter.toString() + "]";
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
}
