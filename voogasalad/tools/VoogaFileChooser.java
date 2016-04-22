package tools;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class VoogaFileChooser{
    private static final String USER_RESOURCES_PATH = "user_resources/";
    private static final String LOCAL_PATH= "voogasalad_DoovalSalad";
    
    private FileChooser fileChooser;
    
    public VoogaFileChooser(){
        fileChooser = new FileChooser();
    }
    
    public String launch() throws VoogaException{
        File file = fileChooser.showOpenDialog(null);
        if (isLocal(file)){
            String path = file.getPath().split(LOCAL_PATH)[1];
            path=path.substring(1);
            System.out.println("local: "+path);
            return path;
        } else {
            System.out.println("Not local: "+ file.getPath());
            String path= moveToLocalPath(file);
            return path;
        }
    }
    
    public void addFilters(ExtensionFilter... filters){
        fileChooser.getExtensionFilters().addAll(filters); 
    }
    
    private boolean isLocal(File file){
//    	System.out.println(file.getPath());
        return file.getPath().contains(LOCAL_PATH);
    }
    
    private String moveToLocalPath(File file) throws VoogaException{
        String path = USER_RESOURCES_PATH+file.getName();
        File newLocation = new File(path);
        
        if (newLocation.exists()){
            throw new VoogaException("This filename already exits");
        }
        
        try {
            Files.copy(file.toPath(), newLocation.toPath(), StandardCopyOption.COPY_ATTRIBUTES);
        }
        catch (IOException e) {
            throw new VoogaException("Could not import file");
        }

        return path;
    }
    
    

}
