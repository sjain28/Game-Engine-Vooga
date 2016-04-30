package usecases;
import java.util.List;
import java.util.Map;

public class EventUseCase {

	private Map<List<CauseUseCase>,List<EffectUseCase>> causeEffect;
	
	public EventUseCase(Map<List<CauseUseCase>,List<EffectUseCase>> causetoEffect){
		causeEffect = causetoEffect;
	}
	
	public void checkConditionsandPerformEffects(){
		for (List<CauseUseCase> listofCauses: causeEffect.keySet()){
			if (checkListConditions(listofCauses)){
				performEffects(causeEffect.get(listofCauses));
			}
		}
	}
	
	public boolean checkListConditions(List<CauseUseCase> causes){
		for (CauseUseCase c:causes){
			if(!c.checkCause()){
				return false;
			}
		}
		return true;
	}
	
	public void performEffects(List<EffectUseCase> effect){
		for (EffectUseCase e: effect){
			e.performEffect();
		}
	}
	
}
