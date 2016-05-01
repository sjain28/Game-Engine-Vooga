package authoring.interfaces.gui;

import tools.VoogaException;

/**
 * This interface applies to anything that can be saveable or that does save, 
 * which is effectively any data that will be written into the XML
 * 
 * 
 * @author Aditya Srinivasan, Arjun Desai, Nick Lockett, Harry Guo
 *
 */
public interface Saveable {
    
    /**
     * Execute onSave
     * @throws VoogaException 
     */
    void onSave() throws VoogaException;
}
