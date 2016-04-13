package usecases;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import usecases.Sprite_USECASE;
import tools.VoogaNumber;

public class DecreaseHealthExample_USECASE {

    public static void main (String[] args){
        Sprite_USECASE a = new Sprite_USECASE("bricks.jpg","GoodGuy");
        a.addProperty("Health", new VoogaNumber(5.0));
        Sprite_USECASE b = new Sprite_USECASE("bricks.jpg","BadGuy");
        a.addProperty("HealthTakeAway", new VoogaNumber(3.0));

        //System Fails to detect collision
        
        Map<List<Cause_USECASE>,List<Effect_USECASE>> causetoEffect = new HashMap<List<Cause_USECASE>,List<Effect_USECASE>>();
        List<Cause_USECASE> causeList = new ArrayList<Cause_USECASE>();
        List<Effect_USECASE> effectList = new ArrayList<Effect_USECASE>();
        causeList.add(new CollisionCause_USECASE(a,b));
        Effect_USECASE decreaseHealthEffect = new VariableEffect_USECASE(a.getDProperty("Health"),b.getDProperty("HealthTakeAway"));
        effectList.add(decreaseHealthEffect);
        causetoEffect.put(causeList, effectList);
        
        Event_USECASE decreasinghealth = new Event_USECASE(causetoEffect);
        decreasinghealth.checkConditionsandPerformEffects();
        System.out.println(a.getProperty("Health"));
        
    }

}
