package authoring.interfaces;

/**
 * An interface implemented by all game elements that defines how
 * sizing is specified.
 * 
 * @author Aditya Srinivasan, Arjun Desai, Nick Lockett, Harry Guo
 *
 */
public interface Sizeable {
    /*
     * Gets and Sets the size of a given object by height and width
     */
    
    public void setSize(double height, double width);
    
    public void setHeight(double height);
    
    public void setWidth(double width);
    
    public double[] getSize();
    
    public double getHeight();
    
    public double getWidth();
}
