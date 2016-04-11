package gameengine.test;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import gameengine.Sprite;
import gameengine.SpriteFactory;
import tools.interfaces.VoogaData;

public class SpriteFactoryTester {
	//Spite Manager Testing
	
	//check if Archetypes are being added upon setArchetype
	@Test
	public void test() {
		SpriteFactory s = new SpriteFactory();
		Map<String, VoogaData> newProperties = new HashMap<String, VoogaData>();
		newProperties.put("health points",null);
//		Sprite sprite = new Sprite("","Pirate");
//		sprite.setProperties(newProperties);
//		s.setArchetype("Pirate", sprite);
//		s.createSprite("Pirate");
		assertEquals(1,s.getAllArchetypeNames().size());
	}
}
