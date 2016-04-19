package events;

import player.leveldatamanager.ILevelData;

public abstract class Cause {
	
	 private VoogaEvent myEvent;	
	 public abstract boolean check(ILevelData data);
	 
	 public Cause(VoogaEvent voogaEvent){
		 myEvent = voogaEvent;
		 myEvent.addCause(this);
	 }
	 
	 public VoogaEvent getEvent(){
		 return myEvent;
	 }
	 
	 protected void setEvent(VoogaEvent event){
		 myEvent = event;
	 }
}

//Every cause and effect needs to look at both Sprite Maps from SpriteManager
//Some effects will need to look at the SpriteFactory

//Solution: Give all causes and effects a SpriteManager
// EventManager has a SpriteManager
//EventManager sets the SpriteManager of every Event that gets added
//2 Options: Allow causes/effects to know their Event, or give them a SpriteManager
