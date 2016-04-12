package data;

import static org.junit.Assert.*;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import authoring.interfaces.Elementable;
import authoring.model.ElementManager;

public class DataWritingTest {
    private FileWriterFromGameObjects manager;
    
    @Before
    public void setup(){
        manager = new FileWriterFromGameObjects();
        
    }
    
    private List<Elementable> generateSprites(){
        return null;
    }
    
    @Test
    public void test () {
        fail("Not yet implemented");
    }

}
