package com.effects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.spacegame.MovableEntity;

public class AreaEffect extends MovableEntity{

	private float lingerTime;
	private float lastLingerTime = 0;
	private TextureRegion texture;
	
	/**
	 * Constructor
	 * @param x 			x-led spawn
	 * @param y				y-led spawn
	 * @param width			Width of explosion	
	 * @param height		Height of explosion
	 * @param lingerTime	The time the explosion lingers (20 ~ 1 second)
	 */
	public AreaEffect(float x, float y, float width, float height, float lingerTime, TextureRegion texture) {
		super( width, height, x, y);
		this.lingerTime = lingerTime;
		this.texture = texture;
	}
		
	/**
	 * Draws the explosion
	 */
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		batch.setColor(getColor().r, getColor().g, getColor().b, getColor().a);		
		batch.draw(texture, getX(), getY(), getWidth()/2, getHeight()/2, getWidth(), getHeight(), 1, 1, getRotation());
	}
	
	/**
	 * Called when "act" is called in its stage
	 * Updates its linger time
	 */
	@Override
	public void act(float delta){
		if (lastLingerTime >= lingerTime) despawnReady = true;
		lastLingerTime++;
		super.act(delta);
	}	
}
