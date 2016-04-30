package events;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import gameengine.Sprite;
import player.leveldatamanager.ILevelData;
import tools.Position;
import tools.Vector;
import tools.Velocity;

public class PathEffect extends SpriteEffect{

	private List<Double> xCoord;
	private List<Double> yCoord;
	private Double[] xMousePoints;
	private Double[] yMousePoints;
	private Double myVelocity;
	private Boolean reverse;
	private Map<Sprite, Velocity> spritePastVelocities;
	private int myCounter;

	public PathEffect(Double[] xMousePoints, Double[] yMousePoints, Boolean reverse, AnimationEvent event) {
		super(event);
		setNeedsSprites(true);
		xCoord = new ArrayList<>();
		yCoord = new ArrayList<>();
		this.xMousePoints = xMousePoints;
		this.yMousePoints = yMousePoints;
		this.reverse = reverse;
		spritePastVelocities = new HashMap<>();
		myCounter = 1;
	}

	@Override
	public void execute(ILevelData data) {
		if (myCounter > 0 && myCounter < ((AnimationEvent)getEvent()).getDuration() - 1){
			//checkPastVelocities();
			setSprites(data);

			for (Sprite sprite : getSprites()){
				System.out.println("Got me in the loop. Bought it dinner first too.");
				Vector nextVector = createSpline(myCounter);
				data.getPhysicsEngine().translateX(sprite, nextVector.getX());
				data.getPhysicsEngine().translateY(sprite, nextVector.getY());
//				Velocity nextVelocity = new Velocity(nextVector.getX()/nextVector.getMagnitude() * getMyVelocity(), 
//									nextVector.getY()/nextVector.getMagnitude() * getMyVelocity());
//				
//				System.out.println("NEXT VELOCITY: " + nextVelocity.getX() + ", " + nextVelocity.getY());
//				sprite.setVelocity(nextVelocity);
				setPastVelocities(sprite);
			}
		}
		clearSprites();
		myCounter++;
	}

	@Override
	public String toString() {
		return null;
	}
	private void setPastVelocities(Sprite sprite){
			spritePastVelocities.put(sprite, sprite.getVelocity());
	}
	private void checkPastVelocities(){
		for (int i = 0; i < getSprites().size(); i++){
			Sprite sprite = getSprites().get(i);
			if (!sprite.getVelocity().equals(spritePastVelocities.get(sprite))){
				((AnimationEvent) getEvent()).removeSprite(sprite);
				i--;
			}
		}
	}
	protected Double getVelocity(Integer duration){
		Double distance = 0.0;
		for (int i = 1; i < xMousePoints.length; i++){
			distance += getDistance(xMousePoints[i - 1], xMousePoints[i], yMousePoints[i - 1], yMousePoints[i]);
		}

		return distance/duration;
	}

	protected void createAnimationPoints(Integer duration){	

		myVelocity = getVelocity(duration);

		xCoord.add(xMousePoints[0]);
		yCoord.add(yMousePoints[0]);
		
		Double distance = 0.0;
		for (int i = 1; i < xMousePoints.length; i++){
			distance += getDistance(xMousePoints[i-1], xMousePoints[i], yMousePoints[i-1], yMousePoints[i]);
			if (distance >= myVelocity){
				xCoord.add(xMousePoints[i]);
				yCoord.add(yMousePoints[i]);
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
		System.out.println("total spline number " + xCoord.size());
		System.out.println("total duration " + ((AnimationEvent) getEvent()).getDuration());
		
		if(counter >= xCoord.size()){
			counter = xCoord.size()-1;
		}
		return new Position(xCoord.get(counter) - xCoord.get(counter - 1), yCoord.get(counter) - yCoord.get(counter-1));
	}
	
	protected PathEffect clone(AnimationEvent event){
		return new PathEffect(xMousePoints, yMousePoints, reverse, event);
	}
	
	protected void setCounter(int newCount){
		myCounter = newCount;
	}
}
