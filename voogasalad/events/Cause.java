package events;

public abstract class Cause {
	
	 private VoogaEvent myEvent;	
	 public abstract boolean check();
	 
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
	 
	 public abstract void init();
}

//Every cause and effect needs to look at both Sprite Maps from SpriteManager
//Some effects will need to look at the SpriteFactory

//Solution: Give all causes and effects a SpriteManager
// EventManager has a SpriteManager
//EventManager sets the SpriteManager of every Event that gets added
//2 Options: Allow causes/effects to know their Event, or give them a SpriteManager
