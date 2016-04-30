package events;

import player.leveldatamanager.ILevelData;
/**
 * Abstraction for an Effect, which modifies the state of the game when its execute() method is called
 * Effects are executing when triggered by Causes in VoogaEvents
 * @author Saumya
 *
 */
public abstract class Effect {
	
	private VoogaEvent myEvent;	

	public abstract void execute(ILevelData data);
	
	/**
	 * Creates an Effect and adds it to an event
	 * @param voogaEvent
	 */
     public Effect(VoogaEvent voogaEvent){
    	 myEvent = voogaEvent;
    	 myEvent.addEffect(this);
     }
     /**
      * Getter for the effect's event
      * @return myEvent
      */
	 protected VoogaEvent getEvent(){
		 return myEvent;
	 }

}