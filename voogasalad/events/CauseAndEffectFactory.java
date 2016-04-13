package events;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ResourceBundle;
import auxiliary.VoogaException;

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
	public void create (VoogaEvent event, String inputString) {

		String[] parameters = inputString.split("\\s+");
		Class<?> c = null;

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

		paramClasses[paramClasses.length - 1] = VoogaEvent.class;
		allParams[allParams.length - 1] = event; // Assuming that the last param to every Cause is a
		// VoogaEvent

		try {
			Constructor<?> causeConstructor = c.getConstructor(paramClasses);
			Object result = causeConstructor.newInstance(allParams);
			addToEvent(event, result);

		}
		catch (NoSuchMethodException e) {
			//throw new VoogaException();
			e.printStackTrace();
		}
		catch (InstantiationException e) {
			//throw new VoogaException();
			e.printStackTrace();
		}
		catch (IllegalAccessException e) {
			//throw new VoogaException();
			e.printStackTrace();
		}
		catch (IllegalArgumentException e) {
			//throw new VoogaException();
			e.printStackTrace();
		}
		catch (InvocationTargetException e) {
			//throw new VoogaException();
			e.printStackTrace();
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
			return Double.parseDouble(input);
		}
		catch (Exception e) {
			return input;
		}

	}
	/**
	 * Adds finalized cause/effect to its event class
	 * @param event
	 * @param added
	 */
	private void addToEvent (VoogaEvent event, Object added) {
		try {
			if (added instanceof Cause) {
				event.addCause((Cause) added);
			}
			else {
				event.addEffect((Effect) added);
			}
		}
		catch (Exception e) {
			System.out.println("Incorrect object!");
		}
	}
	/**
	 * Main method was used for testing purposes
	 * @param args
	 */
	public static void main (String args[]) {
		CauseAndEffectFactory cf = new CauseAndEffectFactory();
		VoogaEvent e = new VoogaEvent();
		Object[] obj = {"Hello"};

		//cf.create("events.KeyCause", e, obj);
		// System.out.println(e.getCauses().size());

		// Object[] params = {"one", "two"};
		// cf.create("events.VariableEffect", e, params);
		//
		// Object[] spriteParams = {"test", "tester"};
		// cf.create("events.SpriteEffect", e, spriteParams);

		// public VariableCause(String variableName, Double targetValue, String predicate,
		// VoogaEvent voogaEvent) {

		//    	public VariableCause(String variableName, Double targetValue, String predicate, VoogaEvent voogaEvent) {

		String cause = "events.SpriteVariableCause ID Health 25.0 greaterThan";
		cf.create(e, cause);

		String variableCauseParams = "events.VariableCause Score 500 greaterThan";
		cf.create(e, variableCauseParams);

		System.out.println(e.getCauses().size());

	}
}
