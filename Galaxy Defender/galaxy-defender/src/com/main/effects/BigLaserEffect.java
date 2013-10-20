package effects;

import assets.ImageAssets;
import ships.BigLaserShip;

/**
 * Effect for BigLaserShip
 * @author Grupp9
 */
public class BigLaserEffect extends AnimatedAreaEffect{
	
	private static final float TIMEPERFRAME = 0.015f;
	private BigLaserShip ship;

	/**
	 * Constructor
	 * @param x x-led spawn location
	 * @param y y-led spawn location
	 * @param width x-led size of effect
	 * @param height y-led size of effect
	 * @param loop repeats effect indefinitly if true
	 * @param ship reference to creating ship
	 */
	public BigLaserEffect(float x, float y, float width, float height, boolean loop, BigLaserShip ship) {
		super(x, y, width, height, TIMEPERFRAME, ImageAssets.enemyLaserAnimation); //TODO: change animation image
		loopFrames = loop;
		this.ship = ship;
	}
	
	/**
	 * Updates effect actions and checks if ship is still alive and active
	 */
	@Override
	public void act(float delta){
		stateTime += delta;
		if(animation.isAnimationFinished(stateTime)&& !loopFrames) remove();
		if(ship.getHealth() <1 || ship.disabled()) remove();
	}

}
