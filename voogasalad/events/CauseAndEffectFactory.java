package events;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ResourceBundle;

public class CauseAndEffectFactory {
	
//	private ResourceBundle errorMessages = ResourceBundle.getBundle("./resources/ErrorMessages.properties");
	
    @SuppressWarnings("rawtypes")
	public void create(VoogaEvent event, String inputString){
        String[] parameters = inputString.split("\\s+");
    	Class<?> c=null;
        try {
            c = Class.forName(parameters[0]); //Find Cause class using reflection
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        
        Class[] paramClasses = new Class[parameters.length];
        Object[] allParams = new Object[parameters.length];
        
        for(int i = 1; i < parameters.length; i++){        	
        	String current = parameters[i];
        	Object parameter = parseString(current);
        	
        	paramClasses[i-1] = parameter.getClass(); //Store all parameter classes
        	allParams[i-1] = parameter;
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
    
    private Object parseString(String input){
    	if(input.equalsIgnoreCase("true"))
    		return new Boolean(true);
    	
    	if(input.equalsIgnoreCase("false"))
    		return new Boolean("false");
    	try{
    		return Double.parseDouble(input);
    	}catch(Exception e){
    		return input;
    	}
    	
    }
    
    private void addToEvent(VoogaEvent event, Object added){
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
//        Object[] obj = {"Hello"};

//        cf.create("events.KeyCause", e, obj);
//        System.out.println(e.getCauses().size());
        
//        Object[] params = {"one", "two"};
//        cf.create("events.VariableEffect", e, params);
//        
//        Object[] spriteParams = {"test", "tester"};
//        cf.create("events.SpriteEffect", e, spriteParams);
        
//    	public VariableCause(String variableName, Double targetValue, String predicate, VoogaEvent voogaEvent) {		
        
        String variableCauseParams = "events.VariableCause Score 500 greaterThan";
        cf.create(e, variableCauseParams);
        System.out.println(e.getCauses().size());
      
    }
}
