package events;

public abstract class Effect {
	
	private VoogaEvent myEvent;	

	public abstract void execute();
	
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
	 
	 public abstract void init();

}
