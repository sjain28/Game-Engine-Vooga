package events;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import tools.interfaces.VoogaData;

public class VariableCause extends Cause{

	private Object myTarget;
	private String myOperation;
	private VoogaData myVariable;
	private String myVarName;
	
	public VariableCause(String variableName, Double targetValue, String predicate, VoogaEvent voogaEvent) {		
		super(voogaEvent);
		myTarget = targetValue;
		myOperation = predicate;
		myVarName = variableName;
	}
	
	public VariableCause(String variableName, Boolean targetValue, String predicate, VoogaEvent voogaEvent) {		
		super(voogaEvent);
		myTarget = targetValue;
		myOperation = predicate;
		myVarName = variableName;
	}
	
	public VariableCause(String predicate, VoogaEvent event){
		super(event);
		myOperation = predicate;
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public boolean check() {
		
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
		myVariable = super.getEvent().getManager().getGlobalVar(myVarName);
		
	}

}
