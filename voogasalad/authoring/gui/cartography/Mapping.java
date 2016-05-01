package authoring.gui.cartography;

/**
 * Mapping class to store information about levels of starting to ending.
 * 
 * @author Harry Guo, Nick Lockett, Arjun Desai, Aditya Srinivasan
 *
 */
public class Mapping {

	/**
	 * private instance variables
	 */
	private String start;
	private String end;

	/**
	 * Instantiates the start and end on input strings.
	 * @param start
	 * @param end
	 */
	public Mapping(String start, String end) {
		this.start = start;
		this.end = end;
	}

	/**
	 * Getter for start string.
	 * @return
	 */
	public String getStart() {
		return this.start;
	}

	/**
	 * Getter for end string.
	 * @return
	 */
	public String getEnd() {
		return this.end;
	}

	/**
	 * to String method for debugging purposes.
	 */
	@Override
	public String toString() {
		return "(" + start + ") to (" + end + ")";
	}

}
