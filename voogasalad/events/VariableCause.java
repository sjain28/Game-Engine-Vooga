package events;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import player.leveldatamanager.ILevelData;
import tools.interfaces.VoogaData;

public class VariableCause extends Cause{

	private Object myTarget;
	private String myOperation;
	private VoogaData myVariable;
	private String myVarName;
	private Class<?> variableClass;
	
	public VariableCause(String variable, String predicate, Double target, VoogaEvent voogaEvent) {		
		super(voogaEvent);
		myTarget = target;
		myOperation = predicate;
		myVarName = variable;
	}
	
	public VariableCause(String variable, String predicate, Boolean target, VoogaEvent voogaEvent) {		
		super(voogaEvent);
		myTarget = target;
		myOperation = predicate;
		myVarName = variable;
	}
	
	public VariableCause(String predicate, VoogaEvent event){
		super(event);
		myOperation = predicate;
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public boolean check(ILevelData data) {
		
		if(myVariable == null){
			setVariable(data.getGlobalVar(myVarName));
		}	

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
	
	protected void setVariable(VoogaData data){
		myVariable = data;
		variableClass = myVariable.getClass();
	}
	
	protected void setTarget(Object target){
		myTarget = target;
	}
	
	protected void setPredicate(String pred){
		myOperation = pred;
	}
	
	@Override
	public String toString(){
		return "Checking if the variable " + myVarName + " is equal to " + myTarget;
	}
}