package authoring.interfaces;

/**
 * 
 * This class pertains to any object that contains an ID. Note there is only a getID method, 
 * because upon initialization the ID is set. It cannot be changed. This further encapsulates 
 * the ID from being changed in another class.
 * 
 * @author Nick Lockett, Harry Guo, Arjun Desai, Aditya Srinivasan
 *
 */
public interface IDable {
    
    String getID();
}
