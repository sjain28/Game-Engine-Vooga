package authoring.model;

import java.util.Observable;

import authoring.interfaces.Elementable;

public class ElementSelectionModel extends Observable {

	private Elementable selected;
	
	// Private constructor prevents instantiation from other classes
	private ElementSelectionModel() {
		
	}

	/**
	 * SingletonHolder is loaded on the first execution of
	 * Singleton.getInstance() or the first access to SingletonHolder.INSTANCE,
	 * not before.
	 */
	private static class SingletonHolder {
		private static final ElementSelectionModel INSTANCE = new ElementSelectionModel();
	}

	public static ElementSelectionModel getInstance() {
		return SingletonHolder.INSTANCE;
	}
	
	public void setSelected(Elementable e) {
		this.selected = e;
		setChanged();
		notifyObservers(this.selected);
	}

}
