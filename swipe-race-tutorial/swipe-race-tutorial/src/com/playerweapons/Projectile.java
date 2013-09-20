package com.playerweapons;

import com.spacegame.GameLogic;
import com.spacegame.MovableEntity;

/**
 * 
 * Base class for weapons. Player and enemy weapons should extend this class
 * 
 * @author Grupp9
 *
 */
public class Projectile extends MovableEntity{
	
	/**
	 * Constructor
	 * @param x x-led Spawn location
	 * @param y y-led Spawn location 
	 * @param width The width of the Projectile
	 * @param height The height of the Projectile
	 */
	public Projectile(float x, float y, float width, float height) {
		super(width, height);
		setPosition(x, y);
	}
	
	/**
	 * Trigger death animation and kill all enemies in a radius around it
	 * @return 
	 */
	public ExplosionDummy explode(GameLogic stage) {
		return null;
	}
	
	/**
	 * Override this method to make something linger (look at ExplosionDummy for example)
	 * @return 
	 */
	public boolean isDespawnReady() {	
		return false;
	}
}
