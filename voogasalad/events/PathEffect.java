package events;

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
	private Integer myCounter;
	private Boolean reverse;
	private Map<Sprite, Velocity> spritePastVelocities;

	public PathEffect(Double[] xMousePoints, Double[] yMousePoints, Boolean reverse, AnimationEvent event) {
		super(event);
		setNeedsSprites(true);
		this.xMousePoints = xMousePoints;
		this.yMousePoints = yMousePoints;
		this.reverse = reverse;
		myCounter = 0;
	}

	@Override
	public void execute(ILevelData data) {
		checkPastVelocities();
		setSprites(data);
		for (Sprite sprite : getSprites()){
			Vector nextVector = createSpline(((AnimationEvent) getEvent()).getCounter());
			sprite.setVelocity(
					new Velocity(nextVector.getX()/nextVector.getMagnitude() * getMyVelocity(), nextVector.getY()/nextVector.getMagnitude() * getMyVelocity()));
			sprite.getPosition().addVector(createSpline(myCounter));
		}
		setPastVelocities();
		clearSprites();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}
	private void setPastVelocities(){
		for (Sprite sprite : getSprites()){
			spritePastVelocities.put(sprite, sprite.getVelocity());
		}
	}
	private void checkPastVelocities(){
		for (Sprite sprite : getSprites()){
			if (!sprite.getVelocity().equals(spritePastVelocities.get(sprite))){
				((AnimationEvent) getEvent()).removeSprite(sprite);
			}
		}
	}
	protected Double getVelocity(Double duration){
		Double distance = 0.0;
		for (int i = 1; i < xMousePoints.length; i++){
			distance += getDistance(xMousePoints[i - 1], xMousePoints[i], yMousePoints[i - 1], yMousePoints[i]);
		}

		return distance/duration;
	}

	protected void createAnimationPoints(Double duration){	

		myVelocity = getVelocity(duration);

		xCoord.add(xMousePoints[0]);
		yCoord.add(yMousePoints[0]);

		Double distance = 0.0;
		for (int i = 1; i < xCoord.size(); i++){
			distance += getDistance(xCoord.get(i-1), xCoord.get(i), yCoord.get(i-1), yCoord.get(i));
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
		return new Position(xCoord.get(counter) - xCoord.get(counter - 1), yCoord.get(counter) - yCoord.get(counter-1));
	}
	protected PathEffect clone(AnimationEvent event){
		return new PathEffect(xMousePoints, yMousePoints, reverse, event);
	}
}
