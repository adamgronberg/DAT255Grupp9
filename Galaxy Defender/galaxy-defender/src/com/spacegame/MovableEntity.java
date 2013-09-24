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
	
	protected boolean despawnReady = false;
	
	/**
	 * Constructor
	 * @param width
	 * @param height
	 * @param x
	 * @param y
	 */
	public MovableEntity(float width, float height, float x, float y) {
		setBounds(x, y, width, height);
	}
	
	/**
	 * @return The rectangle that contains the position of the player.
	 */
	public Rectangle getBounds() {
		return new Rectangle(getX(), getY(), getWidth(),getHeight());
	}
}

