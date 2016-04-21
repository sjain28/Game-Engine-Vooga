package tools.bindings;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.scene.Node;
import tools.VoogaException;


public abstract class NodeProperties {
    private ResourceBundle bundle;
    
    public NodeProperties (ResourceBundle bundle) {
        this.bundle = bundle;
        
    }

    public Map<String,Object> storeData (Node node) throws VoogaException {
        System.out.println("Storing Data");
        System.out.println(this.getClass());
        System.out.println(this.getClass().getDeclaredFields().length);
        
        Map<String,Object> propertiesMap = new HashMap<String,Object>();
        
        for (Field field : this.getClass().getDeclaredFields()) {
            Method method =
                    getMethodName(node, getResourceBundle().getString(field.getName()), "get");
            System.out.println("Method: " + method.toString());

            if (method.getParameters().length == 0) {
                try {
                    field.set(this, method.invoke(node, null));
                    propertiesMap.put(field.getName(), field.get(this));
                }
                catch (IllegalArgumentException | IllegalAccessException
                        | InvocationTargetException e) {
                    throw new VoogaException("Failed to store the data");
                }
            }
        }
        
        return propertiesMap;
    }

    public void loadData (Node node, Map<String,Object> nodeProperties) throws VoogaException {
        System.out.println("Loading Data");
        for (String field : nodeProperties.keySet()) {
            System.out.println("field name: " + field);
            Method method =
                    getMethodName(node, getResourceBundle().getString(field), "set");
            try {
                method.invoke(node, nodeProperties.get(field));
            }
            catch (IllegalAccessException | IllegalArgumentException
                    | InvocationTargetException e) {
                throw new VoogaException("Failed to load the data");
            }
        }

    }

    private Method getMethodName (Object o, String name, String operation) throws VoogaException {
        Method[] methods = o.getClass().getMethods();

        for (Method method : methods) {
            if (method.getName().equalsIgnoreCase(operation + name) ||
                method.getName().equalsIgnoreCase(name)) {
                return method;
            }
        }

        throw new VoogaException("Could not store data");
    }

    private ResourceBundle getResourceBundle () {
        return bundle;
    }



}
