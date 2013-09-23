package com.effects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.spacegame.Assets;
import com.spacegame.MovableEntity;

public class ExplosionEffect extends MovableEntity{

	private float lingerTime;
	private float lastLingerTime = 0;
	
	/**
	 * Constructor
	 * @param x 			x-led spawn
	 * @param y				y-led spawn
	 * @param width			Width of explosion	
	 * @param height		Height of explosion
	 * @param lingerTime	The time the explosion lingers (20 ~ 1 second)
	 */
	public ExplosionEffect(float x, float y, float width, float height, float lingerTime) {
		super( width, height, x, y);
		this.lingerTime = lingerTime;
	}
		
	/**
	 * Explosion should not despawn on collision
	 * @return
	 */
	public boolean despawnesOnCollision(){
		return false;
	}
	
	/**
	 * Draws the explosion
	 */
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		batch.setColor(getColor().r, getColor().g, getColor().b, getColor().a);		
		batch.draw(Assets.explosionSplashMain, getX(), getY(), getWidth()/2, getHeight()/2, getWidth(), getHeight(), 1, 1, getRotation());
	}
	
	/**
	 * Called when "act" is called in its stage
	 * Updates its linger time
	 */
	@Override
	public void act(float delta){
		lastLingerTime++;
		super.act(delta);
	}
	
	/**
	 * Returns true if lingerTime has ended
	 */
	@Override
	public boolean isDespawnReady() {	
		if (lastLingerTime >= lingerTime) return true;
		else return false;
	}
}
