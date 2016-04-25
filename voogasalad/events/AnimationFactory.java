package events;

import java.util.ArrayList;
import java.util.List;

public class AnimationFactory {
	
	private Double myVelocity;
	private List<List<Double>> myCoordinates;

	public AnimationFactory() {
		// TODO Auto-generated constructor stub
	}
	
	/*public AnimationEffect createEffect(Double velocity, List<List<Double>> mousePoints, VoogaEvent event){
		List<List<Double>> animationPoints = createAnimationPoints(velocity, mousePoints);
		List<Double> xCoord = animationPoints.get(0);
		List<Double> yCoord = animationPoints.get(1);
	}*/
	private List<List<Double>> createAnimationPoints(Double velocity, List<List<Double>> mousePoints){
		List<Double> animationX = new ArrayList<>();
		List<Double> animationY = new ArrayList<>();
		
		List<Double> xCoord = mousePoints.get(0);
		List<Double> yCoord = mousePoints.get(1);
		
		animationX.add(xCoord.get(0));
		animationY.add(yCoord.get(0));
		
		Double distance = 0.0;
		for (int i = 1; i < xCoord.size(); i++){
			distance += Math.sqrt(Math.pow(xCoord.get(i)-xCoord.get(i-1), 2) + yCoord.get(i)*yCoord.get(i));
			if (distance >= velocity){
				animationX.add(xCoord.get(i));
				animationY.add(yCoord.get(i));
				distance = 0.0;
			}
		}
		
		List<List<Double>> animationCoord = new ArrayList<>();
		animationCoord.add(animationX);
		animationCoord.add(animationY);
		
		return animationCoord;
	}
	// Take in mouse listener coordinates
	// Take in user defined velocity
	// Sample coordinates according to velocity
		// Use tan(x, y) to calculate distance of mouse listener, keep adding until equal to velocity
		// Add x/y to animation coordinates
		// Reset distance

}
