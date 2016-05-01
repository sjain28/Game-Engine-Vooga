package authoring.model;

import java.util.Observable;

import authoring.gui.Selector;
import authoring.interfaces.AuthoringElementable;


public class ElementSelectionModel extends Observable {

	private AuthoringElementable selected;

	/**
	 * Private constructor prevents instantiation from other classes
	 */
	private ElementSelectionModel () {

	}

	/**
	 * SingletonHolder is loaded on the first execution of
	 * Singleton.getInstance() or the first access to SingletonHolder.INSTANCE,
	 * not before.
	 */
	private static class SingletonHolder {
		private static final ElementSelectionModel INSTANCE = new ElementSelectionModel();
	}
	
	/**
	 * 
	 * @return instance of element selection model that is instantiated with singletone method
	 */
	public static ElementSelectionModel getInstance () {
		return SingletonHolder.INSTANCE;
	}
	
	/**
	 * Set selected authoring elementable
	 * @param elementable to select
	 */
	public void setSelected (AuthoringElementable e) {

		if (this.selected!=null){
			this.selected.select(Selector.UNHIGHLIGHTED);
		}

		this.selected = e;
		this.selected.select(Selector.HIGHLIGHTED);

		setChanged();
		notifyObservers(this.selected);
	}

}
