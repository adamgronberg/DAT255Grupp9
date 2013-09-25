package com.effects;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.spacegame.ImageAssets;
import com.spacegame.MovableEntity;

public abstract class AnimatedAreaEffect extends MovableEntity{
	
	protected Animation animation; 
	protected float stateTime;
	protected TextureRegion currentFrame;
	
	/**
	 * Constructor
	 * @param x 			x-led spawn
	 * @param y				y-led spawn
	 * @param width			Width of explosion	
	 * @param height		Height of explosion
	 * @param lingerTime	The time the explosion lingers (20 ~ 1 second)
	 */
	public AnimatedAreaEffect(float x, float y, float width, float height, float timePerFrame, Array<TextureRegion> animationFrames) {
		super( width, height, x, y);
		animation = new Animation(timePerFrame, ImageAssets.explosionAnimation);
		stateTime = 0f;
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
