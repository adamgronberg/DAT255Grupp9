package weapons;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import spacegame.Sprite;

/**
 * 
 * @author Grupp9
 * 
 * Base class for weapons. Player and enemy weapons should extend this class
 */
public abstract class Projectile extends Sprite implements HasTargets{
	
	protected int damageOnHit; 
	
	/**
	 * COnstructor
	 * @param x	Spawn location x
	 * @param y spawn location y
	 * @param width	Width of projectile
	 * @param height	Height of projectile
	 * @param damageOnHit	Damage on hit
	 * @param affectedTargets	Affected targets
	 * @param despawnesOnCollision	If projectiles goes though its target
	 * @param texture	The texture of the projectile
	 */
	public Projectile(float x, float y, float width, float height, int damageOnHit,  
						 TextureRegion texture) {
		super(width, height, x, y, texture);
		this.damageOnHit = damageOnHit;
	}
	
	/**
	 * @return The damage of the projectile
	 */
	public int getOnHitDamage() {
		return damageOnHit;
	}
	
	
	/**
	 * Adds a on hit effect
	 */
	public abstract void addOnHitEffect();
	
}
