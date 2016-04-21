package tools.bindings;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ResourceBundle;

import javafx.scene.Node;
import tools.VoogaException;


public abstract class NodeProperties {
    private ResourceBundle bundle;

    public NodeProperties (ResourceBundle bundle) {
        this.bundle = bundle;
        
    }

    public void storeData (Node node) throws VoogaException, IllegalArgumentException,
                                      IllegalAccessException, InvocationTargetException {
        System.out.println("Storing Data");
        System.out.println(this.getClass());
        System.out.println(this.getClass().getDeclaredFields().length);

        for (Field field : this.getClass().getDeclaredFields()) {
            Method method =
                    getMethodName(node, getResourceBundle().getString(field.getName()), "get");
            System.out.println("Method: " + method.toString());

            if (method.getParameters().length == 0) {
                field.set(this, method.invoke(node, null));
            }
        }

        for (Field field : this.getClass().getDeclaredFields()) {
            System.out.println(field.getName() + " " + field.get(this));
        }
    }

    public void loadData (Node node) throws VoogaException, IllegalAccessException,
                                     IllegalArgumentException, InvocationTargetException {
        System.out.println("Loading Data");
        for (Field field : this.getClass().getDeclaredFields()) {
            System.out.println("field name: " + field.getName());
            Method method =
                    getMethodName(node, getResourceBundle().getString(field.getName()), "set");
            method.invoke(node, field.get(this));

            Method method2 =
                    getMethodName(node, getResourceBundle().getString(field.getName()), "get");
            System.out.println(method2.getName() + " " + method2.invoke(node, null));
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
