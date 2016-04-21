package events;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import tools.VoogaException;

public class CauseAndEffectFactory {

//	private ResourceBundle errorMessages = ResourceBundle.getBundle("./resources/ErrorMessages.properties");

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
    	String[] parameters = inputString.split("\\s+");
        Class<?> c = null;
        
        try {
            c = Class.forName(parameters[0]); //Find Cause class using reflection
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
            Object result = causeConstructor.newInstance(allParams);
        }
        catch (NoSuchMethodException ee) {
            ee.printStackTrace();
            throw ee;
        }
        catch (InstantiationException e) {
            e.printStackTrace();
            throw e;
        }
        catch (IllegalAccessException e) {
            e.printStackTrace();
            throw e;
        }
        catch (IllegalArgumentException e) {
            e.printStackTrace();
            throw e;
        }
        catch (InvocationTargetException e) {
            e.printStackTrace();
            throw e;
        }
    }
/**
 * Parses the input string from the user stating conditions for cause/effect in order to use as separate constructor
 * type/parameters
 * @param input
 * @return
 */
    private Object parseString (String input) {
        if (input.equalsIgnoreCase("true"))
            return new Boolean(true);

        if (input.equalsIgnoreCase("false"))
            return new Boolean("false");
        try {
        	Double d = Double.parseDouble(input);
            return d;
        }
        catch (Exception e) {
            return input;
        }
    }
    /**
	 * Main method was used for testing purposes
	 * @param args
	 */
    public static void main (String args[]) {
        CauseAndEffectFactory cf = new CauseAndEffectFactory();
        VoogaEvent e = new VoogaEvent();

//    	public VariableCause(String variableName, Double targetValue, String predicate, VoogaEvent voogaEvent) {		
//        String variableCauseParams = "events.VariableCause Score 500 greaterThan";
//        cf.create(e, variableCauseParams);
       
//        String cause = "events.SpriteVariableCause ID Health 25.0 greaterThan";
//        Map<String, VoogaData> params = new HashMap<>();
//        params.put("Health", new VoogaNumber(100.0));
//        params.put("Score", new VoogaNumber(500.0));
//        
//        Sprite tester = new Sprite("dummypath", "Character", params, new VoogaNumber(50.0));
//        String ID = tester.getID();
//        VoogaEvent event = new VoogaEvent();
//        
//        SpriteVariableCause cause = new SpriteVariableCause(tester, "Health", "greaterThan", 50.0, event);
//        SpriteEffect effect = new SpriteEffect("increaseValue", "Score", new Double(2), event);
//        
//        event.update();
//        //	public SpriteVariableCause(Sprite sprite, String varName, Double targetValue, String predicate, VoogaEvent event){
//        
//        cf.create(e, cause);
//        
//        String effect = "events.SpriteEffect ID increaseValue Health 50.0";
//        cf.create(e, effect);
        //	public SpriteVariableCause(String spriteID, String varName, Double targetValue, String predicate, VoogaEvent voogaEvent) {
//    	public SpriteEffect(String spriteID, String method, String variable, Double parameter, VoogaEvent event) {

        
//		  TESTING CAUSES AND EFFECTS, NO FACTORY STUFF HERE        
//        Map<String, VoogaData> params = new HashMap<>();
//        params.put("Health", new VoogaNumber(100.0));
//        params.put("Score", new VoogaNumber(500.0));
//        
//        Sprite tester = new Sprite("dummypath", "Character", params, new VoogaNumber(50.0));
//        String ID = tester.getID();
//        VoogaEvent event = new VoogaEvent();
//        
//        SpriteVariableCause cause = new SpriteVariableCause(tester, "Health", 50.0, "greaterThan", event);
//        SpriteEffect effect = new SpriteEffect("increaseValue", "Score", new Double(2), event);
//        
//        event.update();

    }
}
