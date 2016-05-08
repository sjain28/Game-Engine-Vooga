package authoring.gui.eventpane;

public enum EventParts {
    CAUSE("Cause",false),EFFECT("Effect",true);
    
    private String name;
    private boolean isEffect;
    
    private EventParts(String name,boolean isEffect){
        this.name=name;
        this.isEffect=isEffect;
    }
    
    public String toString(){
        return name;
    }
    
    public boolean isEffect(){
        return isEffect;
    }
}
