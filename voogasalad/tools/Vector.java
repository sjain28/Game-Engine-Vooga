package tools;

/**
 * 
 * @author Aditya Srinivasan, Arjun Desai, Nick Lockett, Harry Guo
 *
 */
public class Vector {
    private double x;
    private double y;
    
    public double getX(){
        return x;
    }
    
    public double getY(){
        return y;
    }
    
    public void setX(double x){
       this.x=x;
    }
    
    public void setY(double y){
        this.y=y;
    }
    
    public void addX(double dx){
        x+=dx;
    }
    
    public void addY(double dy){
        y+=dy;
    }
    
    public void addVector(Vector v){
        x+=v.getX();
        y+=v.getY();
    }
    
}
