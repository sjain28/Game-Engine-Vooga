package events;

import java.lang.reflect.Method;

import player.leveldatamanager.ILevelData;
import tools.interfaces.VoogaData;
/**
 * A Cause that returns a value based on the state of some variable in the game
 * @author Saumya Jain
 *
 */
public class VariableCause extends Cause{

	private Object myTarget;
	private String myOperation;
	private VoogaData myVariable;
	private String myVarName;
	private Class<?> variableClass;
	
	/**
	 * 
	 * @param variableName Name of the variable that's being checked
	 * @param predicate A predicate method to apply to the variable
	 * @param targetValue A targetValue as a parameter to the predicate
	 * @param voogaEvent Event that this cause belongs to
	 */
	public VariableCause(String variableName, String predicate, Double targetValue, VoogaEvent voogaEvent) {		
		super(voogaEvent);
		myTarget = targetValue;
		myOperation = predicate;
		myVarName = variableName;
	}
	/**
	 * Separate constructor taking Boolean instead of Double as target. Needed for Reflection.
	 * @param variableName
	 * @param predicate
	 * @param targetValue
	 * @param voogaEvent
	 */
	public VariableCause(String variableName, String predicate, Boolean targetValue, VoogaEvent voogaEvent) {		
		super(voogaEvent);
		myTarget = targetValue;
		myOperation = predicate;
		myVarName = variableName;
	}
	
	/**
	 * Applies predicate to variable and returns result of predicate
	 */
	@Override
	public boolean check(ILevelData data) {
		Boolean value = false;
		if(myVariable == null){
			setVariable(data.getGlobalVar(myVarName));
		}	

		Class<?>[] paramClass = {myTarget.getClass()};
		
		try {
			Method testMethod = variableClass.getMethod(myOperation, paramClass);
			value = (Boolean) testMethod.invoke(myVariable, myTarget);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return value;
	}
	
	protected VariableCause(String predicate, VoogaEvent voogaevent) {
		super(voogaevent);
		myOperation = predicate;
	}
	
	/**
	 * Getters and setters below.
	 */
	
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
	
	protected Object getTarget(){
		return myTarget;
	}
}