package com.spacegame;


import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;


/**
 * 
 * @author Group9
 *
 *
 *	Movable actor on stage.
 */
public abstract class MovableEntity extends Actor {
	
	protected Rectangle bounds = new Rectangle();
	protected boolean despawnReady = false;
	
	/**
	 * Constructor
	 * @param width
	 * @param height
	 * @param x
	 * @param y
	 */
	public MovableEntity(float width, float height, float x, float y) {
		setPosition(x, y);
		setWidth(width);
		setHeight(height);
	}
	
	/**
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
	 * Override this method to make something linger (look at ExplosionDummy for example)
	 * @return 
	 */
	public boolean isDespawnReady() {	
		return despawnReady;
	}
	
	/**
	 * Sets the object to despawn
	 */
	public void setDespawnReady(){
		despawnReady = true;
	}
	
	
}

