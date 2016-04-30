package events;

import player.leveldatamanager.ILevelData;
/**
 * The abstraction for a Cause, which has a check() method that returns true or false based on internal logic.
 * Causes are used to trigger Events based on user-specified conditions.
 * @author Saumya Jain
 *
 */
public abstract class Cause {
	
	 private VoogaEvent myEvent;	
	 public abstract boolean check(ILevelData data);
	 
	 /**
	  * Creates a Cause and places inside a VoogaEvent which is triggered by that Cause
	  * @param voogaEvent The Event which is triggered by this Cause.
	  */
	 public Cause(VoogaEvent voogaEvent){
		 myEvent = voogaEvent;
		 myEvent.addCause(this);
	 }
	 /**
	  * 
	  * @return this Cause's event
	  */
	 protected VoogaEvent getEvent(){
		 return myEvent;
	 }
}