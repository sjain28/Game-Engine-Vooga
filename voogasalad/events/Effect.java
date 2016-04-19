package events;

import player.leveldatamanager.ILevelData;

public abstract class Effect {
	
	private VoogaEvent myEvent;	

	public abstract void execute(ILevelData data);
	
     public Effect(VoogaEvent voogaEvent){
    	 myEvent = voogaEvent;
    	 myEvent.addEffect(this);
     }
     
	 public void setEvent(VoogaEvent e){
		 myEvent = e;
	 }
	 
	 public VoogaEvent getEvent(){
		 return myEvent;
	 }

}
