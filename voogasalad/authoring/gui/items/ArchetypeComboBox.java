package authoring.gui.items;

import authoring.interfaces.model.EditEventable;


public class ArchetypeComboBox extends SpriteComboBox {

    public ArchetypeComboBox (EditEventable manager) {
        super(manager);
    }

    protected void loadData () {
        getItems().addAll(getManager().getSpriteFactory().getAllArchetypeNames());
    }

    public String getSpriteId () {
        return this.getValue();
    }

}
