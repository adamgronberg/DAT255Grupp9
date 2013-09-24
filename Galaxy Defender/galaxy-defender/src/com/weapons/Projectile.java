package com.weapons;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.spacegame.Sprite;

/**
 * 
 * @author Grupp9
 * 
 * Base class for weapons. Player and enemy weapons should extend this class
 */
public abstract class Projectile extends Sprite{
	
	protected int damage;
	
	/**
	 * Constructor
	 * @param x x-led Spawn location
	 * @param y y-led Spawn location 
	 * @param width The width of the Projectile
	 * @param height The height of the Projectile
	 */
	public Projectile(float x, float y, float width, float height, int damage, TextureRegion texture) {
		super(width, height, x, y, texture);
		this.damage = damage;
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

	public abstract void addOnHitEffect();
	
}
