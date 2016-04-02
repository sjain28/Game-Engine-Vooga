package usecases;
import usecases.Cause;
import usecases.Effect;

import java.util.List;
import java.util.Map;

public class Event {

	private Map<List<Cause>,List<Effect>> causeEffect;
	
	public Event(Map<List<Cause>,List<Effect>> causetoEffect){
		causeEffect = causetoEffect;
	}
	
	public void checkConditionsandPerformEffects(){
		for (List<Cause> listofCauses: causeEffect.keySet()){
			if (checkListConditions(listofCauses)){
				performEffects(causeEffect.get(listofCauses));
			}
		}
	}
	
	public boolean checkListConditions(List<Cause> causes){
		for (Cause c:causes){
			if(!c.checkCause()){
				return false;
			}
		}
		return true;
	}
	
	public void performEffects(List<Effect> effect){
		for (Effect e: effect){
			e.performEffect();
		}
	}
	
}
