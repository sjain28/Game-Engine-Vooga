// This entire file is part of my masterpiece.
// Arjun Desai
 
package tools.bindings;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.scene.Node;
import tools.VoogaException;


/**
 * This class is used to create maps to store Javafx object data, particularly object traits, and to
 * load it back into the object upon initialization.
 * 
 * This class is used by the backend objects to store intial data primarily for the backend objects,
 * and the map generated is maintained
 * in the initialization map.
 * 
 * Dependencies:
 * 1. This class is dependent on the properties file specified for the Javafx object to have the right variable name 
 * (though not necessarily correct casing)
 * 
 * 2. If the nodes property names do not match with the keys in the properties file or if the keys in the map to load
 * data do not match the properties file keys, then the program will throw an error.
 * 
 * Limitations:
 * 
 * 1. Only data that is defined by Java can be serialized and written as the data must still be serialized. However for
 * Javafx objects, the data permitted by serialization is allowed (aka. Java Objects only) 
 * 
 * {@code: gameengine/BackEndText.java init()
 *     @Override
    public void init () throws VoogaException {
        text = new Text();
        TextProperties tp = new TextProperties();
        tp.loadData(text, initializationProperties);
    }
 * }
 * 
 * Extend: 
 * 
 * This class can be extended to accomodate different JavaFx Objects. To do so, the following must be done:
 * 
 * 1. Create a properties file for the data of the object you wish to store (similar to imageProperties in the resource
 * package)
 * 
 * 2. Extend this class and in the constructor of the new class, make a call to this class using "super()" and specify
 * the resource bundle with the path of the properties file that you want to use for the specific object (see ImageProperties
 * class in tools/bindings)
 * 
 * @author Arjun Desai
 *
 *
 * Prior to refactoring, this class had extra strings that were not stored in resource files. I refactored
 * the strings and added the values to the resource file and mapped them to keys, which are associated with the
 * String keys in this class. Therefore, different classes can also use the values in the Resource File if needed,
 * allowing the keys to always be constant and having a central place where the values can change. This improves
 * the extendibility and readability of the code as it is easier for the developer to go in and make changes.
 * 
 * In addition, because there are only two methods for storing and loading data (get and set), these strings are
 * kept as constants as they should not be changed. They are also kept private because they should not be visible to
 * other classes. While usually all strings should be moved to properties files, because these two strings are very
 * unlikely to change throughout the course of Java and object oriented programming, where "get" and "set" are leading
 * modifiers for methods, and are therefore kept as static and private strings. By keeping the strings private, they are
 * not visible nor pertinant to other classes, maintain encapsulation of even the constant strings, illustrating solid
 * encapsulation of the class.
 * 
 * There are only two entry methods, either for storing or for loading data. By having only two entry methods and limiting
 * the parameters needed for each, this class functions as a black box, hiding the method of actually generating the map
 * and actually populating the object with the respective data. This format encapsulates the function of the class, allowing
 * the class to function primarily independently of all other classes, relying only on the properties file loaded in and 
 * the node object that the user wishes to load the data into
 * 
 * This class also uses reflection to get the method names corresponding to the keys in the properties file
 * and invoke the methods from the node object. Reflection allows for cleaner code as the user does not need to 
 * necessarily implement the different names of each and instances of each Slogo command in an if tree. 
 * In addition, it is easy to locate the bugs and errors that are found at compile time, making it simple to find 
 * and fix different issues in the code. Reflection also makes hte code more extendible as multiple if statements 
 * do not have to be created. By using the fully_qualified names of the classes, reflection can invoke methods 
 * of the class without having to increatse the clutter in the code. In addition, different functions and commands 
 * can also readily be added as long as their names match with the names of the classes. In general, reflection
 * increases the readability and the execution power of the code, though it does violate encapsulation of data.
 * 
 * This class exemplifies the Single Responsibility Principle as detailed in the SOLID code
 * acronym. This class has a single responsibility, and is made into a class as it is used in multiple places
 * throughout the program, primarily in initializing data for javafx objects in the engine elementables. While the
 * role of the class does appear specific, the frequency at which it occurs warrants its use as a class. In
 * addition, note that this class does uphold this principle by focusing on solely one task: allowing the user to load
 * and store data for a specific javafx object upon initialization.
 * 
 * One of the key features of this class is its use of streams rather than Collections to gather its
 * data. The use of streams is indicative of functional programming, where lambda expressions are used to
 * represent what is being done rather than how its being done in Java. In addition, filters can be added, as in the 
 * getFilteredList method to check for only keys that the property map can handle. This ensures that the map keys,
 * even if tampered, cannot be translated back into the node object, reducing the chance for error. This flow
 * promotes the encapsulation of the data and preserves its purity by ensuring that changes to the keyset will not
 * be manifested unless present in the properties map used to load the data.
 * 
 * The collections framework relies on the concept of external iteration, where an Iterable
 * is implemented to enumerate its elements. However, the for-each loop to gather data is inherently
 * sequential and must process the elements in order that is specified by the Collection. This limitation deprives the
 * library method of the opportunity to manage the control flow, which is useful in managing the parallelism of
 * the program.
 * 
 * By using streams, the control of the program has been handed off from the client code to the library code,
 * allowing the libraries to abstract over common control flow operations. This design helps in clearer code on
 * the client side, as it only focuses on solving the problem, not how to solve it, making the code generally
 * readable. In addition, the primary check on the key set comes from the properties map, which determines what data
 * can and should be loaded back into the object. This allows for a single entry point in saving and loading data, 
 * making the purpose of the class more sound and reducing the chance for errors.
 * 
 * Streams do not provide a means to directly access or manipulate their elements, and are instead concerned with
 * declaratively describing the computational operations which will be performed in aggregate on that source. This
 * makes the data drawn using the streams immutable. It is being directly added to the items list, maintaining the
 * encapsulation of the data drawn by the stream.
 * 
 * In addition, this is an abstract class. Although there are no abstract methods, this class cannot be instantiated.
 * The constructor does need a properties file to be instantiated. As this is the primary dependency, by making this 
 * class abstract, the subclasses of this class have the power to define a specific properties file to load, making
 * it more apparent to the user what the dependency is. This allows the user to know what he/she must do in order to
 * ensure that this class functions appropriately. This class, as abstract, is closed to modification, but open to
 * extension, allowing for new details to follow, but centering the functionality on loading and storing data, which
 * is the primarily role of this class and its subclasses
 * 
 * In addition the package friendly instance variables in the subclasses that this class reflected on were removed.
 * Overall, all fields in a class should be private to maintain the encapsulation of the class data. Rather than being
 * able to access the fields directly, as reflection can let you do, by putting the field names that will be stored in
 * the Map. This increases the encapsulation of the program. 
 * 
 * This class is also properly documented now, allowing other developers to also be able to understand, implement,
 * and extend this class as necessary. The names are representative of the functionality of each
 * field and method and each method and field is explained to illustrate their purpose. Blank spaces
 * allows the code to be more readable and to structure fields that share similar properties or
 * code that together executes a function.
 * 
 * All fields are also private and are not accessible to other classes. The only data that other
 * classes can get from this class is data generated by executing a method. This practice of encapsulating
 * the fields allows the code to be more robust to changes in other classes, as it does not
 * rely on any other class to get data. While the parameters should be data from other classes in
 * the program when they call this class, the methods will also execute with simple integer fields.
 * 
 * This class "keeps it dry" by not repeating code, as instances of different classes cannot be instantiated
 * in the same procedure (i.e. they type cannot be passed to a method and the method will
 * produce a different instance). Though this can be done by a switch case statement, my separating
 * the methods the role of each method in producing the obstacles is clearer. This class is also "shy"
 * hiding all of its fields.
 * 
 * These are all principle and effective design practices to generating clean, debuggable, testable
 * and easy to fix code.
 * 
 * This class also thorws exceptions rather than printing stack traces
 *
 */
public abstract class NodeProperties {
    private static final String GET = "get";
    private static final String SET = "set";
    private static final String FAILED = "Failed to load the data";
    
    private ResourceBundle bundle;
    
    /**
     * Constructor for Node Properties
     * @param bundle: resource bundle used to load the keys that should be added to hte map and loaded from the map
     */
    public NodeProperties (ResourceBundle bundle) {
        this.bundle = bundle;
    }
    
    /**
     * Stores the Data from a node into a map and returns the map to the object
     * Make sure that the node object matches the data you wish to store or no data will be stored
     * @param node: node to draw data from and store in the properties map
     * @return map of keys defined in properties file to values in the node
     * @throws VoogaException:  throws exceptions when data cannot be stored
     */
    public Map<String, Object> storeData (Node node) throws VoogaException {
        Map<String, Object> propertiesMap = new HashMap<>();

        for (String field : getFilteredList(getResourceBundle().keySet(), node)) {
            Method method = getMethodName(node,
                                          field, GET, 0);

            try {
                propertiesMap.put(field, method.invoke(node));
            }
            catch (IllegalArgumentException | IllegalAccessException
                    | InvocationTargetException e) {
                throw new VoogaException();
            }
        }

        return propertiesMap;
    }
    
    /**
     * Loads the properties from a property map into the node object
     * @param node: node to initialize with respective properties
     * @param nodeProperties: map of properties to load
     * @throws VoogaException: exception thrown when load fails
     */
    public void loadData (Node node, Map<String, Object> nodeProperties)
                                                                         throws VoogaException {

        for (String field : getFilteredList(nodeProperties.keySet(), node)) {
            Method method = getMethodName(node,
                                          getResourceBundle().getString(field), SET, 1);

            try {
                method.invoke(node, nodeProperties.get(field));
            }

            catch (IllegalAccessException | IllegalArgumentException
                    | InvocationTargetException e) {
                throw new VoogaException(FAILED);
            }
        }

    }
    
    /**
     * Gets method name 
     * @param o: object to get method from
     * @param name: name of method
     * @param operation: operation (either get(store) or set (load)
     * @param numParameters: number of parameters to add
     * @return
     * @throws VoogaException: exception to throw when name not found
     */
    private Method getMethodName (Object o,
                                  String name,
                                  String operation,
                                  int numParameters) throws VoogaException {
        Method[] methods = o.getClass().getMethods();

        for (Method method : methods) {
            if (checkMethod(method, name, operation, numParameters)) {
                return method;
            }
        }

        throw new VoogaException(FAILED);
    }
    
    /**
     * Checks if method is appropriate through filtering
     * @param method: method to check
     * @param name: name of method
     * @param operation: operation - get or set
     * @param numParameters: number of parameters method takes
     * @return boolean if method is valid
     */
    private boolean checkMethod (Method method, String name, String operation, int numParameters) {
        return (method.getName().equalsIgnoreCase(operation + name) || method
                .getName().equalsIgnoreCase(name)) &&
               (method.getParameterCount() == numParameters);
    }
    
    /**
     * 
     * @return loaded resrouce bundle
     */
    private ResourceBundle getResourceBundle () {
        return bundle;
    }
    
    /**
     * 
     * @param keySet: set to filter
     * @param node : node data to load
     * @return collection of strings that can be used for set
     */
    private Collection<String> getFilteredList (Collection<String> keySet, Node node) {
        Collection<String> parameters = keySet.stream()
                .filter(m -> getResourceBundle().keySet().contains(m))
                .collect(Collectors.toList());

        return parameters;
    }

}
