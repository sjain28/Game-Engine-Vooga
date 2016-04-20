package authoring.model;

import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.SimpleIntegerProperty;
import player.gamedisplay.Menuable;

public class ElementTabManager implements Menuable {
	
	private List<ElementManager> myManagers;
	private SimpleIntegerProperty currentManagerIndex;
	
	public ElementTabManager() {
		this.myManagers = new ArrayList<ElementManager>();
		this.currentManagerIndex = new SimpleIntegerProperty(-1);
		this.currentManagerIndex.addListener((obs, old, n) -> {
			System.out.println("NEW TAB WITH INDEX " + n);
		});
	}
	
	public void addManager(ElementManager manager) {
		this.myManagers.add(manager);
		this.currentManagerIndex.set(this.currentManagerIndex.get() + 1);
	}
	
	public ElementManager getCurrentManager() {
		return myManagers.get(currentManagerIndex.get());
	}
	
	public SimpleIntegerProperty getCurrentManagerIndexProperty() {
		return this.currentManagerIndex;
	}

}
