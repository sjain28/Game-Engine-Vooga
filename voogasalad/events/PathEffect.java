package events;

import java.util.ArrayList;
import java.util.List;

import gameengine.Sprite;
import player.leveldatamanager.ILevelData;
import tools.Position;
import tools.Vector;
import tools.Velocity;

public class PathEffect extends SpriteEffect{

	private List<Double> xCoord;
	private List<Double> yCoord;
	private List<Double> xMousePoints;
	private List<Double> yMousePoints;
	private Double myVelocity;
	private Integer myCounter;

	public PathEffect(List<Double> xMousePoints, List<Double> yMousePoints, VoogaEvent event) {
		super(event);
		setNeedsSprites(true);
		this.xMousePoints = xMousePoints;
		this.yMousePoints = yMousePoints;
		myCounter = 0;
	}

	@Override
	public void execute(ILevelData data) {
		setSprites(data);
		myCounter++;
		for (Sprite sprite : getSprites()){
			// TODO: What if other interaction occurs? (for example, collision)
			Vector nextVector = createSpline(myCounter);
			sprite.setVelocity(
					new Velocity(nextVector.getX()/nextVector.getMagnitude() * getMyVelocity(), nextVector.getY()/nextVector.getMagnitude() * getMyVelocity()));
			sprite.getPosition().addVector(createSpline(myCounter));
		}
		clearSprites();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

	protected Double getVelocity(Double duration){
		Double distance = 0.0;
		for (int i = 0; i < xMousePoints.size(); i++){
			distance += getDistance(xCoord.get(i-1), xCoord.get(i), yCoord.get(i-1), yCoord.get(i));
		}

		return distance/duration;
	}

	protected void createAnimationPoints(Double duration){	

		myVelocity = getVelocity(duration);
		
		xCoord.add(xMousePoints.get(0));
		yCoord.add(yMousePoints.get(0));

		Double distance = 0.0;
		for (int i = 1; i < xCoord.size(); i++){
			distance += getDistance(xCoord.get(i-1), xCoord.get(i), yCoord.get(i-1), yCoord.get(i));
			if (distance >= myVelocity){
				xCoord.add(xMousePoints.get(i));
				yCoord.add(yMousePoints.get(i));
				distance = 0.0;
			}
		}
	}
	
	private Double getDistance(Double x1, Double x2, Double y1, Double y2){
		return Math.sqrt(Math.pow((x2-x1), 2) + Math.pow((y2-y1), 2));
	}
	
	protected Double getMyVelocity(){
		return myVelocity;
	}
	protected Vector createSpline(Integer counter){
		return new Position(xCoord.get(counter) - xCoord.get(counter - 1), yCoord.get(counter) - yCoord.get(counter-1));
	}
}
