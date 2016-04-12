package authoring.resourceutility;

import javafx.scene.input.DataFormat;

public class VoogaFileFormat extends DataFormat {
	
	// Private constructor prevents instantiation from other classes
	  private VoogaFileFormat(String format) {
		  super(format);
	  }
	 
	  /**
	   * SingletonHolder is loaded on the first execution of Singleton.getInstance() 
	   * or the first access to SingletonHolder.INSTANCE, not before.
	   */
	  private static class SingletonHolder { 
	    private static final VoogaFileFormat INSTANCE = new VoogaFileFormat("authoring.resourceutility.VoogaFile");
	  }

	  public static VoogaFileFormat getInstance() {
	    return SingletonHolder.INSTANCE;
	  }

}
