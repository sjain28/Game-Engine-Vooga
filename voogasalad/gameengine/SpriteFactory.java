package gameengine;

import java.util.Map;

public class SpriteFactory {

	private Map<String, Sprite> archetypeBank;
	
	public SpriteFactory() {
		// TODO Auto-generated constructor stub
	}
	public void addArchetype(Sprite sprite){
		archetypeBank.add(sprite.getArchetype(), sprite);
	}
public Sprite cloneSprite(String archetype){
	Sprite original = archetypeBank.get(archetype);
	Sprite clone = new Sprite(original.getImagePath(), original.getParameterMap());
	return clone;
}
}
