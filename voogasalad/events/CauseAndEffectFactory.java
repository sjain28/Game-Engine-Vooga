package events;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import tools.VoogaException;
/**
 * Receives String input from GUI and instantiates cause or effect objects with it
 * @author Saumya Jain
 *
 */
public class CauseAndEffectFactory {

    @SuppressWarnings("rawtypes")
/**
 * Takes in a string stating what kind of cause/effect is being made as well as all of its parameters. Also takes in the
 * event to which the cause/effect is being added. Parses the string to get the information for the cause/effect and uses
 * reflection to instantiate the correct constructor, filling it with the needed parameters.
 * @param event the Event that is receiving the Cause or Effect
 * @param inputString A comma-separated String representing all user input to instantiate a Cause or Effect
 */
    public void create (VoogaEvent event, String inputString) throws VoogaException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException  {
    	String[] parameters = inputString.split(",");
        Class<?> c;
        
        c = Class.forName(parameters[0]); 
        
        Class[] paramClasses = new Class[parameters.length];
        Object[] allParams = new Object[parameters.length];
        initConstructorParams(parameters, paramClasses, allParams, event);

        Constructor<?> causeConstructor = c.getConstructor(paramClasses);
        causeConstructor.newInstance(allParams);
    }
    
	private void initConstructorParams(String[] parameters, Class<?>[] paramClasses, Object[] allParams, VoogaEvent event) {
		for(int i = 1; i < parameters.length; i++){        	
        	Object parameter = parseString(parameters[i]);
        	paramClasses[i-1] = parameter.getClass(); 
        	allParams[i-1] = parameter;
        }
        paramClasses[paramClasses.length - 1] = VoogaEvent.class;
        allParams[allParams.length - 1] = event; 
	}
	
	/**
	 * Parses the input string from the user stating conditions for cause/effect in order to use as separate constructor
	 * type/parameters
	 * @param input a String to be parsed
	 * @return parsed form of String
	 */
    private Object parseString (String input) {
        if (input.equalsIgnoreCase("true")) {
            return new Boolean(true);
        }
        
        else if (input.equalsIgnoreCase("false")) {
            return new Boolean(false);
        }
        
        try {
        	return Double.parseDouble(input);
        }
        catch (Exception e) {
            return input;
        }
    }
}    