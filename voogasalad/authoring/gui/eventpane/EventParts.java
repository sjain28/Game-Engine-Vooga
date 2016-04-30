package authoring.gui.eventpane;

public enum EventParts {
    CAUSE("Cause"),EFFECT("Effect");
    
    private String name;
    private EventParts(String name){
        this.name=name;
    }
    
    public String toString(){
        return name;
    }
}
