package com.ships;

import com.spacegame.Assets;

/**
 * 
 * @author Grupp9
 *
 *	The Basic enemy
 *  Only has y led moment
 *
 */
public class BasicShip extends EnemyShip {
	private final static float SHIPSPEED=3.0f;
	private final static int HEALTH=1;
	
	public final static int HEIGHT=40;
	public final static int WIDTH=40;

	
	
	/**
	 * Constructor
	 * @param x	x-led Spawn location
	 * @param y y-led Spawn location
	 */
	public BasicShip(float x, float y) {
		super(x, y, WIDTH, HEIGHT, HEALTH, Assets.enemyBasicShip);
	}

	
	/**
	 * Called when "act" is called in its stage
	 * Updates its position.
	 */
	@Override
	public void act(float delta){
		setY(getY()-SHIPSPEED);
		super.act(delta);
	}
}
