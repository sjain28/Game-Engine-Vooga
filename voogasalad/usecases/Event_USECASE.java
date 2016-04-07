package usecases;
import usecases.Cause_USECASE;
import usecases.Effect_USECASE;

import java.util.List;
import java.util.Map;

public class Event_USECASE {

	private Map<List<Cause_USECASE>,List<Effect_USECASE>> causeEffect;
	
	public Event_USECASE(Map<List<Cause_USECASE>,List<Effect_USECASE>> causetoEffect){
		causeEffect = causetoEffect;
	}
	
	public void checkConditionsandPerformEffects(){
		for (List<Cause_USECASE> listofCauses: causeEffect.keySet()){
			if (checkListConditions(listofCauses)){
				performEffects(causeEffect.get(listofCauses));
			}
		}
	}
	
	public boolean checkListConditions(List<Cause_USECASE> causes){
		for (Cause_USECASE c:causes){
			if(!c.checkCause()){
				return false;
			}
		}
		return true;
	}
	
	public void performEffects(List<Effect_USECASE> effect){
		for (Effect_USECASE e: effect){
			e.performEffect();
		}
	}
	
}
