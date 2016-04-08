package events;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class CauseFactory {
    String causeName;
    public void createCause(String name){
        Class<?> c=null;
        try {
            c = Class.forName(name);
        }
        catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        List<Field> fields = new ArrayList<>();
        Field[] allFields = c.getDeclaredFields();
        System.out.println(allFields);
    }
    
    public static void main(String args[]){
        CauseFactory cf = new CauseFactory();
        cf.createCause("events.KeyCause");
    }
}
