package events;

import java.lang.reflect.Method;

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
	public VariableEffect(VoogaEvent event){
		super(event);
	}
	
	@Override
	public void execute(ILevelData data) {
		VoogaData variableData = data.getGlobalVar(myVariable);
		callEffectMethod(variableData);
	}

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
	
	public void setParameter(Object parameter) throws VoogaException{
		if (!parameter.getClass().equals(myParameter.getClass())){
			throw new VoogaException("Please input a " + myParameter.getClass());
		}else{
			myParameter = parameter;
		}
	}	
}
