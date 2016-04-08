package events;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import groovyjarjarantlr.debug.Event;

public class CauseAndEffectFactory {
    String causeName;
    
    public void create(String name, VoogaEvent event, Object[] parameters){
        
    	Class<?> c=null;
        try {
            c = Class.forName(name); //Find Cause class using reflection
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        
        Class[] paramClasses = new Class[parameters.length+1];
        Object[] allParams = new Object[parameters.length+1];
        
        for(int i = 0; i < parameters.length; i++){
        	paramClasses[i] = parameters[i].getClass(); //Store all parameter classes
        	allParams[i] = parameters[i];
        }
        
        paramClasses[paramClasses.length-1] = VoogaEvent.class;
        allParams[allParams.length-1] = event; //Assuming that the last param to every Cause is a VoogaEvent

        try {
			Constructor<?> causeConstructor = c.getConstructor(paramClasses);
			Object result = causeConstructor.newInstance(allParams);
			addToEvent(event, result);
			
        }catch(NoSuchMethodException e){
        	e.printStackTrace();
        }catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}	
    }
    
    public void addToEvent(VoogaEvent event, Object added){
	    try{	
    		if(added instanceof Cause){
	    		event.addCause((Cause) added);
	    	}else{
	    		event.addEffect((Effect) added);
	    	}
	    }catch(Exception e){
	    	System.out.println("Incorrect object!");
	    }
    } 
    
    
    public static void main(String args[]){
        CauseAndEffectFactory cf = new CauseAndEffectFactory();
        VoogaEvent e = new VoogaEvent();
        Object[] obj = {"Hello"};

        cf.create("events.KeyCause", e, obj);
        System.out.println(e.getCauses().size());
        
        
    }
}
