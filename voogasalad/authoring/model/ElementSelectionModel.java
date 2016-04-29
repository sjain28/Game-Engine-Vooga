package authoring.model;

import java.util.Observable;
import authoring.gui.Selector;
import authoring.interfaces.FrontEndElementable;


public class ElementSelectionModel extends Observable {

    private FrontEndElementable selected;

    // Private constructor prevents instantiation from other classes
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

    public static ElementSelectionModel getInstance () {
        return SingletonHolder.INSTANCE;
    }

    public void setSelected (FrontEndElementable e) {
        
        if (this.selected!=null){
            this.selected.select(Selector.UNHIGHLIGHTED);
        }
        
        this.selected = e;
        this.selected.select(Selector.HIGHLIGHTED);

        System.out.println("");
        setChanged();
        notifyObservers(this.selected);
    }

}
