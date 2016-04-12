package gameengine.test;

import static org.junit.Assert.*;

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
	public void addArchetypeToListListIncrements() {
		myFactory = new SpriteFactory();
		
		//create sprite to add as default for archetype
		Map<String, VoogaData> newProperties = new HashMap<String, VoogaData>();
		newProperties.put("health points",null);
		Sprite sprite = new Sprite("/bricks.jpg","Pirate",newProperties, new VoogaNumber(50));
		sprite.setProperties(newProperties);
		
		//add archetype to list
		myFactory.setArchetype("Pirate", sprite);
		assertEquals(1,myFactory.getAllArchetypeNames().size());
	}
	
	@Test
	public void serializeAndDeserializeAListOfSprites(){
		addArchetypeToListListIncrements();
		myFactory.serializeArchetypes("archetypes_saved_here");
		
		SpriteFactory myTesterFactory = new SpriteFactory();
		assertEquals(0,myTesterFactory.getAllArchetypeNames().size());
		myTesterFactory.deSerializeArchetype("archetypes_saved_here_Pirate");
		assertEquals(1,myFactory.getAllArchetypeNames().size());
//		Sprite sprite = new Sprite("","Pirate");
//		sprite.setProperties(newProperties);
//		s.setArchetype("Pirate", sprite);
//		s.createSprite("Pirate");
		assertEquals(1,myFactory.getAllArchetypeNames().size());
	}
}
