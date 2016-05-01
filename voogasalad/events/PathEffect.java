package events;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gameengine.Sprite;
import physics.StandardPhysics;
import player.leveldatamanager.ILevelData;
import tools.Position;
import tools.Vector;
import tools.Velocity;

public class PathEffect extends SpriteEffect{

	private List<Double> xCoord;
	private List<Double> yCoord;
	private Double[] xPathPoints;
	private Double[] yPathPoints;
	private Double myVelocity;
	private Boolean reverse;
	private Map<Sprite, Velocity> spritePastVelocities;
	private int myCounter;
	private final static int SQUARE = 2;
	
	public PathEffect(Double[] xPathPoints, Double[] yPathPoints, Boolean reverse, AnimationEvent event) {
		super(event);
		setNeedsSprites(true);
		xCoord = new ArrayList<>();
		yCoord = new ArrayList<>();
		this.reverse = reverse;
		if (reverse){
			//reverse xPathPoints/yPathPoints
			//append to itself
		}
		this.xPathPoints = xPathPoints;
		this.yPathPoints = yPathPoints;
		spritePastVelocities = new HashMap<>();
		myCounter = 1;
	}

	@Override
	public void execute(ILevelData data) {
		if (myCounter > 0 && myCounter < xCoord.size()){
			checkPastVelocities();
			setSprites(data);

			for (Sprite sprite : getSprites()){
				Vector nextVector = createSpline(myCounter);
				data.getPhysicsEngine().translateX(sprite, nextVector.getX() / StandardPhysics.REDUCE_FACTOR);
				data.getPhysicsEngine().translateY(sprite, nextVector.getY() / StandardPhysics.REDUCE_FACTOR);
				setPastVelocities(sprite);
			}
		}
		clearSprites();
		myCounter++;
	}

	@Override
	public String toString() {
		return "";
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
		for (int i = 1; i < xPathPoints.length; i++){
			distance += getDistance(xPathPoints[i - 1], xPathPoints[i], yPathPoints[i - 1], yPathPoints[i]);
		}
		return distance/duration;
	}

	protected void createAnimationPoints(Integer duration){	

		myVelocity = getVelocity(duration);

		xCoord.add(xPathPoints[0]);
		yCoord.add(yPathPoints[0]);
		
		Double distance = 0.0;
		for (int i = 1; i < xPathPoints.length; i++){
			distance += getDistance(xPathPoints[i-1], xPathPoints[i], yPathPoints[i-1], yPathPoints[i]);
			if (distance >= myVelocity){
				xCoord.add(xPathPoints[i]);
				yCoord.add(yPathPoints[i]);
				distance = 0.0;
			}
		}
		xCoord.remove(xCoord.size() - 1);
		yCoord.remove(yCoord.size() - 1);
		
		xCoord.add(xCoord.get(xCoord.size()-1));
		yCoord.add(yCoord.get(yCoord.size()-1));
	}

	private Double getDistance(Double x1, Double x2, Double y1, Double y2){
		return Math.sqrt(Math.pow((x2-x1), SQUARE) + Math.pow((y2-y1), SQUARE));
	}

	protected Double getMyVelocity(){
		return myVelocity;
	}
	
	protected Vector createSpline(Integer counter){				
		return new Position(xCoord.get(counter) - xCoord.get(counter - 1), yCoord.get(counter) - yCoord.get(counter-1));
	}
	protected Double[] reverseArray(Double[] array){
		for(int i = 0; i < array.length / 2; i++)
		{
		    double temp = array[i];
		    array[i] = array[array.length - i - 1];
		    array[array.length - i - 1] = temp;
		}
		return array;
	}
	protected PathEffect clone(AnimationEvent event){
		return new PathEffect(xPathPoints, yPathPoints, reverse, event);
	}
	
	protected void setCounter(int newCount){
		myCounter = newCount;
	}
}
