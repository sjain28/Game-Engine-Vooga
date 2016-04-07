package events;

public abstract class Effect {
	
	private Event myEvent;	

	public abstract void execute();
 
	 public void setEvent(Event e){
		 myEvent = e;
	 }
	 
	 public Event getEvent(){
		 return myEvent;
	 }

}
