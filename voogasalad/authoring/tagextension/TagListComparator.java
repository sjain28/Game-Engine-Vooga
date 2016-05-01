package authoring.tagextension;

import java.util.Comparator;
import java.util.List;
import com.clarifai.api.Tag;


/**
 * Compares tags by their percent match to
 * the inputted descriptions
 * 
 * @author Krista
 *
 */
public class TagListComparator implements Comparator<Object> {
	private List<String> myDescripList;
	
	public TagListComparator(List<String> descriptionlist){
		myDescripList = descriptionlist;
	}
	@Override
	public int compare(Object o1, Object o2) {
		GameTagPair pair1 = (GameTagPair) o1;
		GameTagPair pair2 = (GameTagPair) o2;
	   	 
	    double perc1 = generateTotalPercentMatch(pair1.getTagList());
	    double perc2 = generateTotalPercentMatch(pair2.getTagList());

	    if(perc1 < perc2){return 1;}
	    if(perc1 > perc2){return -1;}
	    return 0;
	}
	private double generateTotalPercentMatch(List<Tag> taglist){
		double percentmatch = 0.0;
		for(String descrip : myDescripList){
			for(Tag tag : taglist){
				String tagname = tag.getName();
				if(descrip.equals(tagname)){
					percentmatch += tag.getProbability();
				}
			}
		}
		return percentmatch;
	}
}