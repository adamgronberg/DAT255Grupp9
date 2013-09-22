package com.ships;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
	
	private final static int HEALTH=2;
	
	/**
	 * Constructor
	 * @param x	x-led Spawn location
	 * @param y y-led Spawn location
	 */
	public HeavyShip(float x, float y) {
		super(x, y, WIDTH, HEIGHT, HEALTH);
	}
	
	/**
	 * Called when draw is called on stage
	 */
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		batch.setColor(getColor().r, getColor().g, getColor().b, getColor().a);		
		batch.draw(Assets.enemyBasicShip, getX(), getY(), getWidth()/2, getHeight()/2, getWidth(), getHeight(), 1, 1, getRotation());
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
