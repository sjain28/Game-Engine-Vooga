package authoring.model;

import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.SimpleIntegerProperty;
import player.gamedisplay.Menuable;

public class ElementTabManager {
	
	private List<ElementManager> myManagers;
	private SimpleIntegerProperty currentManagerIndex;
	
	private ElementManager myCurrentManager;
	
	public ElementTabManager() {
		this.myManagers = new ArrayList<ElementManager>();
		this.currentManagerIndex = new SimpleIntegerProperty(-1);
		this.currentManagerIndex.addListener((obs, old, n) -> {
			myCurrentManager = myManagers.get((int) n);
		});
	}
	
	public void addManager(ElementManager manager) {
		this.myManagers.add(manager);
		this.currentManagerIndex.set(this.currentManagerIndex.get() + 1);
	}
	
	public ElementManager getCurrentManager() {
		return myCurrentManager;
	}
	
	public SimpleIntegerProperty getCurrentManagerIndexProperty() {
		return this.currentManagerIndex;
	}

    public List<ElementManager> getAllManagers () {
        return myManagers;
    }

}
