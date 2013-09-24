package com.effects;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.spacegame.Assets;
import com.spacegame.MovableEntity;

public class AreaEffect extends MovableEntity{
	
	private Animation animation = new Animation(0.02f, Assets.explosionAnimation);
	private float stateTime = 0f;
	private TextureRegion currentFrame;
	
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
		;
	}
	
	/**
	 * Draws the explosion
	 */
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		currentFrame = animation.getKeyFrame(stateTime, false);
        batch.draw(currentFrame, getX(), getY(), getWidth()/2, getHeight()/2, getWidth(), getHeight(), 1, 1, getRotation());
	}
	
	/**
	 * Updates its linger time
	 */
	@Override
	public void act(float delta){
		stateTime += delta;
		if(animation.isAnimationFinished(stateTime)) remove();
	}	
}
