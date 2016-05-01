package tools.bindings;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.scene.Node;
import tools.VoogaException;

/**
 * Storing and loading of data in serialization
 */
public abstract class NodeProperties {
	private ResourceBundle bundle;

	public NodeProperties(ResourceBundle bundle) {
		this.bundle = bundle;

	}

	public Map<String, Object> storeData(Node node) throws VoogaException {
		Map<String, Object> propertiesMap = new HashMap<>();

		for (Field field : this.getClass().getDeclaredFields()) {
			Method method = getMethodName(node,
					getResourceBundle().getString(field.getName()), "get", 0);

			if (method.getParameters().length == 0) {
				try {
					field.set(this, method.invoke(node));
					propertiesMap.put(field.getName(), field.get(this));
				} catch (IllegalArgumentException | IllegalAccessException
						| InvocationTargetException e) {
					throw new VoogaException("Failed to store the data");
				}
			}
		}

		return propertiesMap;
	}

	public void loadData(Node node, Map<String, Object> nodeProperties)
			throws VoogaException {
		for (String field : nodeProperties.keySet()) {
			Method method = getMethodName(node,
					getResourceBundle().getString(field), "set", 1);

			try {
				method.invoke(node, nodeProperties.get(field));
			} catch (IllegalAccessException | IllegalArgumentException
					| InvocationTargetException e) {
				throw new VoogaException("Failed to load the data");
			}
		}

	}

	private Method getMethodName(Object o, String name, String operation,
			int numParameters) throws VoogaException {
		Method[] methods = o.getClass().getMethods();

		for (Method method : methods) {
			if ((method.getName().equalsIgnoreCase(operation + name) || method
					.getName().equalsIgnoreCase(name))
					&& (method.getParameterCount() == numParameters)) {
				return method;
			}
		}

		throw new VoogaException("Could not store data");
	}

	public ResourceBundle getResourceBundle() {
		return bundle;
	}



}
