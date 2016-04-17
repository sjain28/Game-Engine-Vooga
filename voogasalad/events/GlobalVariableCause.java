package events;

import player.leveldatamanager.LevelData;

public class GlobalVariableCause extends VariableCause {
	private String myVarName;

	public GlobalVariableCause(String variableName, String predicate, Double targetValue, VoogaEvent voogaEvent) {		
		super(voogaEvent);
		super.setTarget(targetValue);
		super.setPredicate(predicate);
		myVarName = variableName;
	}
	public GlobalVariableCause(String predicate, VoogaEvent voogaEvent){
		super(voogaEvent);
		super.setPredicate(predicate);
	}
	
	public boolean check(LevelData data){
		//set the variable
		super.setVariable(data.getGlobalVar(myVarName));
		//have the variable cause abstract class do the work of checking
		return super.check(data);
		
	}
}
