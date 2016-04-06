package events;

public interface Cause {
	
	 boolean check();
	 
	 //Causes Need: 
	 //Access to Map of IDs to Sprites, and Map of Archetypes to IDs
	 
	 //Effects need: 
	 //Both of the above, and also the SpriteFactory incase the Effect has to create new Sprites
	 
	 //Options for transferring them: 
	 //Getters in Cause and Effect superclasses
	 
}
