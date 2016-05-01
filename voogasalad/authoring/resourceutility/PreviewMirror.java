package authoring.resourceutility;

public class PreviewMirror {
	
	/**
	 * Constants
	 */
	private static final String PACKAGE_LOCATION = "authoring.resourceutility.";
	private static final String PREVIEW = "preview";
	private static final String SUFFIX = "Previewer";
	
	/**
	 * The constructor uses reflection in order to call the right subclass to initiate the
	 * previewing. Image files will call on ImagePreviewer and Audio files will call on AudioPreviewer
	 * @param file: the file to be previewed
	 */
	public PreviewMirror(VoogaFile file) {
		Previewer previewer;
		Class<?> clazz;
		try {
			clazz = Class.forName(PACKAGE_LOCATION + toClassCase(file.getType().name()) + SUFFIX);
			previewer = (Previewer) clazz.getConstructor(VoogaFile.class).newInstance(file);
			previewer.getClass().getDeclaredMethod(PREVIEW).invoke(previewer);
		} catch(Exception e) {
		}
	}

	/**
	 * Changes the case from all uppercase to a beginning capital
	 * (AUDIO --> Audio)
	 * @param name: uppercase name to change
	 * @return
	 */
	private String toClassCase(String name) {
		return name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
	}
	
}
