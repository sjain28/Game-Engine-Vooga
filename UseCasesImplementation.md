Voogasalad: Use Cases Implementation
===================

**Creating new Game Object**

MenuBar -> File -> New -> Game Object

```class SpriteManager```
```public void add(GameObject o){
	ResourceDragger.addObject() \\ method for making the Design Board editable to click and add objects
}


**Use Case: Editing Sprite Variables/Properties**

Click on the sprite, the properties pane will have the parameters of the sprite. The properties will then be editable through the GUI.

```class GameObject```

private void isEditing = false;

public void edit(){
	isEditing = true;
	PropertiesWindow.editObject(this);
	}