package com.spacegame;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;


/**
 * 
 * @author Group9
 *
 *
 *	Movable actor on stage.
 */
public class MovableEntity extends Actor {
	protected Rectangle bounds = new Rectangle();
	
	public MovableEntity(float width, float height) {
		setWidth(width);
		setHeight(height);
	}
	
	/**
	 * Use this in collision ect
	 * @return The rectangle that contains the position of the player.
	 */
	public Rectangle getBounds() {
		return bounds;
	}
	
	/**
	 * Called when the player has moved
	 */
	protected void updateBounds() {
		bounds.set(getX(), getY(), getWidth(), getHeight());
	}
	
	/**
	 * Called when "act" is called in its stage
	 * Updates its position.
	 */
	@Override
	public void act(float delta){
		super.act(delta);
		updateBounds();
	}
	
	/**
	 * Called when "draw" is called in its stage
	 */
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {

	}
}

