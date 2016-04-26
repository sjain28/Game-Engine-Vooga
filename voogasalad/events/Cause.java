package events;

import player.leveldatamanager.ILevelData;

public abstract class Cause {
	
	 private VoogaEvent myEvent;	
	 public abstract boolean check(ILevelData data);
	 
	 public Cause(VoogaEvent voogaEvent){
		 setMyEvent(voogaEvent);
		 getMyEvent().addCause(this);
	 }
	 
	 public VoogaEvent getEvent(){
		 return getMyEvent();
	 }
	 
	 protected void setEvent(VoogaEvent event){
		 setMyEvent(event);
	 }

	public VoogaEvent getMyEvent() {
		return myEvent;
	}

	public void setMyEvent(VoogaEvent myEvent) {
		this.myEvent = myEvent;
	}
}