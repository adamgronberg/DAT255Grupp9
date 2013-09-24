package com.ships;

import com.spacegame.Assets;

/**
 * 
 * @author Grupp9
 *
 *	The Basic enemy
 *  Only has y led moment more HP
 *
 */
public class HeavyShip extends EnemyShip {
	private final static float SHIPSPEED = 2.0f;
	public final static int HEIGHT=60;
	public final static int WIDTH=40;
	
	private final static int SCOREVALUE=3;
	private final static int HEALTH=2;
	
	/**
	 * Constructor
	 * @param x	x-led Spawn location
	 * @param y y-led Spawn location
	 */
	public HeavyShip(float x, float y) {
		super(x, y, WIDTH, HEIGHT, HEALTH, SCOREVALUE, Assets.enemyBasicShip);
		
	}
	
	/**
	 * Updates its position.
	 */
	@Override
	public void act(float delta){
		setY(getY()-SHIPSPEED);

	}
}
