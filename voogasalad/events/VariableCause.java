package events;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import player.leveldatamanager.LevelData;
import tools.interfaces.VoogaData;

public abstract class VariableCause extends Cause{

	private Object myTarget;
	private String myOperation;
	private VoogaData myVariable;
	//private String myVarName;
	public VariableCause(VoogaEvent voogaEvent){
		super(voogaEvent);
	}
	/*public VariableCause(String predicate, Double targetValue, VoogaEvent voogaEvent) {		
		super(voogaEvent);
		myTarget = targetValue;
		myOperation = predicate;
		//myVarName = variableName;
	}
	
	public VariableCause(String predicate, VoogaEvent voogaEvent){
		super(voogaEvent);
		myOperation = predicate;
	}*/
	
	@SuppressWarnings("rawtypes")
	@Override
	public boolean check(LevelData data) {
		//added in this first line to replace need for init 
		//because for many of the effects and causes it doesn't seem necessary
		//myVariable = data.getGlobalVar(myVarName);
		Class<?> variableClass = myVariable.getClass();
		Class[] paramClass = {myTarget.getClass()};
		
		try {
			Method testMethod = variableClass.getMethod(myOperation, paramClass);
			Boolean value = (Boolean) testMethod.invoke(myVariable, myTarget);
			return value;
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	//TODO: WHERE IS THIS USED?
	protected void setVariable(VoogaData data){
		myVariable = data;
	}
	
	protected void setTarget(Object target){
		myTarget = target;
	}
	
	protected void setPredicate(String pred){
		myOperation = pred;
	}

	@Override
	public void init() {
		//myVariable = super.getEvent().getManager().getGlobalVar(myVarName);
		
	}
}
