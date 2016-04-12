package authoring.Properties;

import authoring.interfaces.Elementable;
import tools.interfaces.VoogaData;

public class SpritePropertiesTab extends AbstractPropertiesTab {
	
	public final static String SPRITE_PROPERTIES = "Sprite Properties";
	private Elementable myElementable;

	public SpritePropertiesTab() {
		super();
		this.setText(SPRITE_PROPERTIES);
	}

	@Override
	public void getPropertiesMap(Object o) {
		myElementable = (Elementable) o;
		propertiesMap = myElementable.getVoogaProperties();
		originalPropertiesMap = myElementable.getVoogaProperties();
		displayProperties();
	}

	@Override
	public void addNewProperty(String s, VoogaData vgData) {
		myElementable.addProperty(s, vgData);
		displayProperties();
	}

	@Override
	public void removeProperty(String s) {
		myElementable.removeProperty(s);
		displayProperties();
	}

}
