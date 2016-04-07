package gameengine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import authoring.model.VoogaText;
import javafx.scene.text.Text;

public class TextManager {
	private Map<String, VoogaText> myText;
	
	/**
	 * takes in list of text and puts them in map
	 * 
	 * @param textList
	 */
	public TextManager(List<VoogaText> textList) {
		myText = new HashMap<String, VoogaText>();
		for(VoogaText t : textList){
			myText.put(t.getId(),t);
		}
	}
	
	/**
	 * Returns a voogatext by id
	 * @param id
	 * @return
	 */
	public VoogaText getText(Object id){
		return myText.get(id);
	}

}
