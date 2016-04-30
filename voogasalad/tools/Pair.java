package tools;

/**
 * A simple example used in many tutorials.
 * 
 * Replicates the functionality of Map.Entry, but is perhaps simpler to use.
 */
public class Pair<A, B> {
    private final A first;
    private final B last;

    /**
     * Pair a map key to a value
     * 
     * @param key
     * @param value
     */
    public Pair (A key, B value) {  
		this.first = key;
		this.last = value;
	}

	/**
	 * Return the key
	 * 
	 * @return
	 */
	public A getFirst() {
		return first;
	}

	/**
	 * Return the mapped value
	 * 
	 * @return
	 */
	public B getLast() {
		return last;
	}

	/**
	 * Return the mapping as a string
	 */
	public String toString() {
		return "(" + first + ", " + last + ")";
	}
}