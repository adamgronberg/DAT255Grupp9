package com.playerweapons;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.spacegame.Assets;
import com.spacegame.GameLogic;

/**
 * 
 * @author Grupp9
 *
 * Player missile. Have a small blast radius on impact
 *
 */
public class PlayerMissile extends Projectile {
	
	public static final float RATEOFFIRE = 800000000f; //In nanoseconds
	private static final float SPEED = 5f;
	public static final float HEIGHT = 30;
	public static final float WIDTH = 20;
	
	/**
	 * Constructor
	 * @param x x-led Spawn
	 * @param y y-led Spawn
	 */
	public PlayerMissile(float x, float y){
		super(x, y, WIDTH, HEIGHT);
	}
	
	/**
	 * Draws the missile
	 */
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		batch.setColor(getColor().r, getColor().g, getColor().b, getColor().a);
		batch.draw(Assets.playerMissile, getX(), getY(), getWidth()/2, getHeight()/2, getWidth(), getHeight(), 1, 1, getRotation());
	}
	
	/**
	 * Called when "act" is called in its stage
	 * Updates its position.
	 */
	@Override
	public void act(float delta){
		setY(getY()+SPEED);
		super.act(delta);
	}
	
	/**
	 * Returns a collision dummy for the explosion that lingers
	 */
	public ExplosionDummy explode(GameLogic stage) {
		ExplosionDummy explosion = new ExplosionDummy(getX()-WIDTH/4f, 
										getY()-HEIGHT/4f, 2*WIDTH, 2*HEIGHT, 20);
		stage.addActor(explosion);
		return explosion;
	}
}