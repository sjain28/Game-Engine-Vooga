package authoring.resourceutility;

import java.io.File;
import java.util.List;
import authoring.tagextension.TagMaker;
import tools.VoogaException;
import tools.VoogaFileChooser;


/**
 * This class prompts the user to choose a file to import. The accepted
 * extensions can be found in the properties file "extensions.properties"
 * 
 * @author DoovalSalad
 */

public class FileImporter {

    private ResourceTreeView rtv;
    private TagMaker tagmaker;
    private File fileToImport;

    /**
     * The constructor that initializes the file chooser and calls to add to UI
     * upon choosing a file.
     * 
     * @param ui
     * @throws VoogaException
     */
    public FileImporter (ResourceTreeView rtv) throws VoogaException {
    	System.out.println("here");
    	tagmaker = new TagMaker();
        this.rtv = rtv;
        
        VoogaFileChooser chooser = new VoogaFileChooser();
        String path = chooser.launch();
        System.out.println("path"+path);
        System.out.println("about to use tag maker");
        tagmaker.addTagsFromImage(path);
        System.out.println("importing file: "+path);
        fileToImport = new File(path);
        provideToUI();
    }

    /**
     * Adds the file to import to the resource explorer, given it is not already
     * there. Note that this implementation allows multiple copies of the same
     * file to exist given that they are named differently.
     */
    private void provideToUI () throws VoogaException {
    	System.out.println("here!!!!");
        List<String> importedItems = rtv.getFileNamesOfType(VoogaFileType.AUDIO);
        importedItems.addAll(rtv.getFileNamesOfType(VoogaFileType.IMAGE));
        if (fileToImport != null) {
            String fileName = ResourceDecipherer.getExtension(fileToImport.toString(), '\\');
            if (!importedItems.contains(fileName)) {
                VoogaFileType type = ResourceDecipherer.decipherName(fileToImport.toString());
                VoogaFile file = new VoogaFile(type, fileName);
                file.setPath(fileToImport.getAbsolutePath());
                rtv.addItem(file);
            }
        }
    }
}
