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

import usecases.Sprite;
import tools.VoogaNumber;

public class DecreaseHealthExample {

    public static void main (String[] args){
        Sprite a = new Sprite("bricks.jpg","GoodGuy");
        a.addProperty("Health", new VoogaNumber(5));
        Sprite b = new Sprite("bricks.jpg","BadGuy");
        a.addProperty("HealthTakeAway", new VoogaNumber(3));

        //System Fails to detect collision
        
        Map<List<Cause>,List<Effect>> causetoEffect = new HashMap<List<Cause>,List<Effect>>();
        List<Cause> causeList = new ArrayList<Cause>();
        List<Effect> effectList = new ArrayList<Effect>();
        causeList.add(new CollisionCause(a,b));
        Effect decreaseHealthEffect = new VariableEffect(a.getDProperty("Health"),b.getDProperty("HealthTakeAway"));
        effectList.add(decreaseHealthEffect);
        causetoEffect.put(causeList, effectList);
        
        Event decreasinghealth = new Event(causetoEffect);
        decreasinghealth.checkConditionsandPerformEffects();
        System.out.println(a.getProperty("Health"));
        
    }

}
