package tools;

public class VoogaException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public VoogaException() {
	}
	
	public VoogaException(String message) {
		super(message);
	}
	
	public VoogaException(Throwable cause) {
		super(cause);
	}
	
	public VoogaException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public VoogaException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
	
}
