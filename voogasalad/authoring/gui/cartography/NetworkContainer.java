package authoring.gui.cartography;

import java.util.Set;

public class NetworkContainer {
	
	private Set<Mapping> mappings;
	private String entrypoint;
	
	public NetworkContainer(Set<Mapping> mappings, String entrypoint) {
		this.mappings = mappings;
		this.entrypoint = entrypoint;
	}
	
	public Set<Mapping> getMappings() {
		return this.mappings;
	}
	
	public String getEntrypoint() {
		return this.entrypoint;
	}

}
