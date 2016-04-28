package events;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import tools.VoogaException;

public class CauseAndEffectFactory {

    @SuppressWarnings("rawtypes")
/**
 * Takes in a string stating what kind of cause/effect is being made as well as all of its parameters. Also takes in the
 * event to which the cause/effect is being added. Parses the string to get the information for the cause/effect and uses
 * reflection to instantiate the correct constructor, filling it with the needed parameters.
 * @param event
 * @param inputString
 */
    public void create (VoogaEvent event, String inputString) throws VoogaException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException  {
       System.out.println("Input to Factory: " + inputString);
    	String[] parameters = inputString.split(",");
        Class<?> c = null;
        
        try {
            c = Class.forName(parameters[0]); 
        }catch (ClassNotFoundException ee) {
            ee.printStackTrace();
            throw ee;
        }
        
        Class[] paramClasses = new Class[parameters.length];
        Object[] allParams = new Object[parameters.length];
        
        for(int i = 1; i < parameters.length; i++){        	
        	String current = parameters[i];
        	Object parameter = parseString(current);
        	
        	paramClasses[i-1] = parameter.getClass(); //Store all parameter classes
        	allParams[i-1] = parameter;
        }

        paramClasses[paramClasses.length - 1] = VoogaEvent.class;
        allParams[allParams.length - 1] = event; // Assuming that the last param to every Cause is a
                                                 // VoogaEvent

        try {
            Constructor<?> causeConstructor = c.getConstructor(paramClasses);
            causeConstructor.newInstance(allParams);
        }
        catch (Exception e) {
            e.printStackTrace();
            throw e;
        }    
    }
    
/**
 * Parses the input string from the user stating conditions for cause/effect in order to use as separate constructor
 * type/parameters
 * @param input
 * @return double if input is a double
 * 		   boolean if input is a boolean
 * 		   the input string otherwise
 */
    private Object parseString (String input) {
        try{
        	Boolean b = new Boolean(input);
        	return b;
        }
        catch(Exception e){
            try {
            	Double d = Double.parseDouble(input);
                return d;
            }
            catch (Exception ee) {
                return input;
            }
        }

    }
}
