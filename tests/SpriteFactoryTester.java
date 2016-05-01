package tests;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import gameengine.Sprite;
import gameengine.SpriteFactory;
import tools.VoogaNumber;
import tools.interfaces.VoogaData;

public class SpriteFactoryTester {
	//Spite Manager Testing
	private SpriteFactory myFactory;
	//check if Archetypes are being added upon setArchetype
	@Test
	public void addArchetypeToListListIncrements() throws Exception {
		myFactory = new SpriteFactory();
		
		//create sprite to add as default for archetype
		Map<String, VoogaData> newProperties = new HashMap<String, VoogaData>();
		newProperties.put("health points",null);
		Sprite sprite = new Sprite("/bricks.jpg","Pirate",newProperties, new VoogaNumber(50.0));
		sprite.setProperties(newProperties);
		
		//add archetype to list
		myFactory.addArchetype("Pirate", sprite);
		assertEquals(1,myFactory.getAllArchetypeNames().size());
	}
	
}
