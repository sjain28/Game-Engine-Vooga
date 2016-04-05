package events;

import java.util.List;

public class Event {

	private List<Cause> myCauses;
	private List<Effect> myEffects;
	
	public Event(List<Cause> cause, List<Effect> effect) {
		myCauses = cause;
		myEffects = effect;
	}
	
	public void update(){
		for(Cause c: myCauses){
			if(!c.check()){
				return;
			}
		}
		
		for(Effect e: myEffects){
			e.execute();
		}
	}
	
	public List<Cause> getCauses(){
		return myCauses;
	}

}
