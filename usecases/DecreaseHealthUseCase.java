package usecases;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tools.VoogaNumber;

public class DecreaseHealthUseCase {

    public static void main (String[] args){
        SpriteUseCase a = new SpriteUseCase("bricks.jpg","GoodGuy");
        a.addProperty("Health", new VoogaNumber(5.0));
        SpriteUseCase b = new SpriteUseCase("bricks.jpg","BadGuy");
        a.addProperty("HealthTakeAway", new VoogaNumber(3.0));

        //System Fails to detect collision
        
        Map<List<CauseUseCase>,List<EffectUseCase>> causetoEffect = new HashMap<List<CauseUseCase>,List<EffectUseCase>>();
        List<CauseUseCase> causeList = new ArrayList<CauseUseCase>();
        List<EffectUseCase> effectList = new ArrayList<EffectUseCase>();
        causeList.add(new CollisionCauseUseCase(a,b));
        EffectUseCase decreaseHealthEffect = new VariableEffectUseCase(a.getDProperty("Health"),b.getDProperty("HealthTakeAway"));
        effectList.add(decreaseHealthEffect);
        causetoEffect.put(causeList, effectList);
        
        EventUseCase decreasinghealth = new EventUseCase(causetoEffect);
        decreasinghealth.checkConditionsandPerformEffects();
        System.out.println(a.getProperty("Health"));
        
    }

}
