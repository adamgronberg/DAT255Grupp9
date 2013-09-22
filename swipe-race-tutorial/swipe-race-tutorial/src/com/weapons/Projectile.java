package com.weapons;

import com.effects.ExplosionEffect;
import com.spacegame.MovableEntity;

/**
 * 
 * @author Grupp9
 * 
 * Base class for weapons. Player and enemy weapons should extend this class
 */
public abstract class Projectile extends MovableEntity{
	
	protected int damage;
	
	/**
	 * Constructor
	 * @param x x-led Spawn location
	 * @param y y-led Spawn location 
	 * @param width The width of the Projectile
	 * @param height The height of the Projectile
	 */
	public Projectile(float x, float y, float width, float height, int damage) {
		super(width, height, x, y);
		this.damage = damage;
	}
	
	/**
	 * Triggers on death (adds a effect for now)
	 * @return 
	 */
	public ExplosionEffect addEffect() {
		return null;
	}
	
	/**
	 * Override this method if projectiles should not despawn on collision
	 * @return
	 */
	public boolean despawnesOnCollision(){
		return true;
	}
	
	/**
	 * @return The damage of the projectile
	 */
	public int getDamage() {
		return damage;
	}
}
