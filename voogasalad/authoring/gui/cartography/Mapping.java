package authoring.gui.cartography;

public class Mapping {

	private String start;
	private String end;
	
	public Mapping(String start, String end) {
		this.start = start;
		this.end = end;
	}
	
	public String getStart() {
		return this.start;
	}
	
	public String getEnd() {
		return this.end;
	}
	
	@Override
	public String toString() {
		return "(" + start + ") to (" + end + ")";
	}
	
}
