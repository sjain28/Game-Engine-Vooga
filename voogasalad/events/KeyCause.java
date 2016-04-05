package events;

import gameengine.Sprite; 

/*
 * This class extends cause to create the specificity needed to address key causes. This will allow us to deal with
 * potential listeners and other complications that are specific to key strokes.
 */
public class KeyCause implements Cause {

	private Sprite mySprite;
	private char myKey;
	
	public KeyCause(Sprite sprite, char key) {
		mySprite = sprite;
		myKey = key;
		
		mySprite.getImageView().setOnKeyPressed(e -> handleKey(e.getCode()));
	}

	@Override
	public boolean check() {
		// TODO Auto-generated method stub
		
	}
	
	//set sprite to have key as parameter in hashmap
	
	//getSprite.getImageView.setOnKeyPress();

}
